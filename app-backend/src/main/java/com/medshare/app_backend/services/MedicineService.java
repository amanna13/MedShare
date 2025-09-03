package com.medshare.app_backend.services;


import com.medshare.app_backend.entity.Medicine;
import com.medshare.app_backend.entity.User;
import com.medshare.app_backend.repository.MedicineRepository;
import com.medshare.app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MedicineService {

    @Autowired
    public MedicineRepository medicineRepository;

    @Autowired
    public UserRepository userRepository;



    public void addMedicine(Medicine medicine){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("User is not authenticated!");
            throw new RuntimeException("User is not authenticated!");
        }
        String user = authentication.getName();
        Optional<User> u = userRepository.findUserByUserName(user);
        medicine.setOwnerID(u.get().getUserName());
        medicine.setOwnerName(u.get().getName());
        medicine.setOwnerPhoneNumber(u.get().getPhoneNumber());
        medicine.setOwnerLocation(u.get().getHostelDetails());
        List<String> tagList = Arrays.asList(medicine.getTags().get(0).split(","));
        medicine.setTags(tagList);
        medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

    public List<Medicine> searchByTags(List<String> tags){
        return medicineRepository.findByTagsInAndQuantityGreaterThanZero(tags);
    }


}
