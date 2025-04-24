package com.medshare.app_backend.controller;

import com.medshare.app_backend.entity.Medicine;
import com.medshare.app_backend.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    public MedicineService medicineService;


    @PostMapping("/add_medicine")
    public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine){
        medicineService.addMedicine(medicine);
        return new ResponseEntity<>(medicine, HttpStatus.CREATED);
    }

    @GetMapping("/list_all_medicine")
    public ResponseEntity<List<Medicine>> getMedicine(){
        try {
            List<Medicine> m1 = medicineService.getAllMedicine();
            return new ResponseEntity<>(m1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> searchbyTags(@RequestParam List<String> tags) {
        try {
            List<Medicine> response = medicineService.searchByTags(tags);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
