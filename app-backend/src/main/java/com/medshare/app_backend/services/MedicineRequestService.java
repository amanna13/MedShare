package com.medshare.app_backend.services;

import com.medshare.app_backend.entity.Medicine;
import com.medshare.app_backend.entity.MedicineRequest;
import com.medshare.app_backend.entity.User;
import com.medshare.app_backend.repository.MedicineRepository;
import com.medshare.app_backend.repository.MedicineRequestRepository;
import com.medshare.app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MedicineRequestService {

    @Autowired
    public MedicineRequestRepository medicineRequestRepository;

    @Autowired
    public MedicineRepository medicineRepository;

    @Autowired
    public UserRepository userRepository;

    MedicineRequest medicineRequest = new MedicineRequest();


    public String RequestMedicine(String MedicineId, String ownerUserName){
    String requestUsername = SecurityContextHolder.getContext().getAuthentication().getName();

//    String OTP = String.format("%06d",new SecureRandom().nextInt(999999));
//    System.out.println(OTP);
    Optional<User> requesterUser = userRepository.findUserByUserName(requestUsername);

    Optional<User> ownerUser = userRepository.findUserByUserName(ownerUserName);

    Medicine medicine = medicineRepository.findById(MedicineId);

    medicineRequest.setRequestUsername(requestUsername);
    medicineRequest.setOwnerUsername(ownerUserName);
    medicineRequest.setMedicineID(MedicineId);
    medicineRequest.setMedicineName(medicine.getMedicine_name());
    medicineRequest.setRequestTime(LocalDateTime.now());
    medicineRequest.setStatus("PENDING");
    medicineRequest.setOTP(null);
    medicineRequest.setRequestPhoneNumber(requesterUser.get().getPhoneNumber());
    medicineRequest.setRequestLocation(requesterUser.get().getHostelDetails());
    medicineRequest.setOwnerPhoneNumber(ownerUser.get().getPhoneNumber());
    medicineRequest.setOwnerLocation(ownerUser.get().getHostelDetails());


    medicineRequestRepository.save(medicineRequest);
    return "Medicine request sent successfully!" ;
    }

    //Requests I made
    public List<MedicineRequest> getAllMyPrevRequests(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return medicineRequestRepository.findByRequestUsername(username);
    }

    public List<MedicineRequest> getMyRecentRequest() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime expiryTime = LocalDateTime.now().minusHours(2);
        return medicineRequestRepository.findByRequestUsernameAndRequestTimeAfter(username, expiryTime);

    }

    //Incoming Requests
    public List<MedicineRequest> getRequestsForMe(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return medicineRequestRepository.findByOwnerUsername(username);
    }

//    Accept OR deny
    public String denyRequest(String RequestId){
        MedicineRequest request = medicineRequestRepository.findByRequestId(RequestId);
        request.setStatus("DECLINED");
        return "REQUEST DECLINED";
    }

    public String acceptRequest(String RequestId){
        MedicineRequest request = medicineRequestRepository.findByRequestId(RequestId);
        request.setStatus("ACCEPTED");
        request.setOTP(OTPGenerate());
        medicineRequestRepository.save(request);
        return request.getOTP();
    }


    //Verify OTP
    public String confirmFinalSharing(String requestID, String enteredOTP){
        MedicineRequest request = medicineRequestRepository.findByRequestId(requestID);
        Medicine medicine = medicineRepository.findById(request.getMedicineID());

        if(!request.getOTP().equals(enteredOTP)){
            return "Invalid OTP";
        }else {
            request.setStatus("COMPLETED");
            medicine.setQuantity(medicine.getQuantity()-1);
            medicineRequestRepository.save(request);
            medicineRepository.save(medicine);
            return "OTP VERIFIED . Medicine Delivered !";
        }
    }

    @Scheduled(fixedRate = 300000)
    public void expireOldRequest(){
        LocalDateTime expiryTime = LocalDateTime.now().minusHours(2);

        List<MedicineRequest> expiredMedicineRequests = medicineRequestRepository.findByRequestTimeBeforeAndStatus(expiryTime, "PENDING");
        for (MedicineRequest request : expiredMedicineRequests){
            request.setStatus("EXPIRED");
            medicineRequestRepository.save(request);
        }
    }

    public String OTPGenerate(){

        String OTP = String.format("%06d",new SecureRandom().nextInt(999999));

        return OTP;
    }

}
