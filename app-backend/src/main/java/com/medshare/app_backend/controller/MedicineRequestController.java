package com.medshare.app_backend.controller;


import com.medshare.app_backend.entity.MedicineRequest;
import com.medshare.app_backend.services.MedicineRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class MedicineRequestController {

    @Autowired
    public MedicineRequestService medicineRequestService;


    @PostMapping("/{medicineID}/{ownerUsername}")
    public ResponseEntity<String> requestMedicine(@PathVariable String medicineID, @PathVariable String ownerUsername){
        return new ResponseEntity<>(medicineRequestService.RequestMedicine(medicineID, ownerUsername), HttpStatus.OK);
    }

    @GetMapping("/myhistory")
    public ResponseEntity<List<MedicineRequest>> myRequestHistory(){
        return new ResponseEntity<>(medicineRequestService.getAllMyPrevRequests(), HttpStatus.OK);
    }

    @GetMapping("/activerequest")
    public ResponseEntity<List<MedicineRequest>> myActiveRequests(){
        return new ResponseEntity<>(medicineRequestService.getMyRecentRequest(), HttpStatus.OK);
    }

    @GetMapping("/pendingRequest")
    public ResponseEntity<List<MedicineRequest>> myPendingRequest(){
        return new ResponseEntity<>(medicineRequestService.getRequestsForMe(), HttpStatus.OK);
    }

    @PostMapping("/pendingRequest/{RequestId}/accept")
    public ResponseEntity<String > acceptRequest(@PathVariable String RequestId){
        return new ResponseEntity<>(medicineRequestService.acceptRequest(RequestId), HttpStatus.OK);
    }

    @PostMapping("/pendingRequest/{RequestId}/reject")
    public ResponseEntity<String > declineRequest(@PathVariable String RequestId){
        return new ResponseEntity<>(medicineRequestService.denyRequest(RequestId), HttpStatus.OK);
    }

    @PostMapping("/finalConfirm/{RequestId}/verification")
    public ResponseEntity<String > confirmFinalSharing(@PathVariable String RequestId, @RequestParam String OTP){
        return new ResponseEntity<>(medicineRequestService.confirmFinalSharing(RequestId, OTP), HttpStatus.OK);
    }

}