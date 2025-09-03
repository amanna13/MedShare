package com.medshare.app_backend.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "medicine_requests")
public class MedicineRequest {
    @Id
    private String requestId;

    @NonNull
    private String requestUsername;
    private String requestLocation;
    private String requestPhoneNumber;
    private String ownerUsername;
    private String ownerLocation;
    private String ownerPhoneNumber;
    private String medicineID;
    private String medicineName;
    private LocalDateTime requestTime;
    private String status;
    private String OTP;

}
