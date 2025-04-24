package com.medshare.app_backend.repository;

import com.medshare.app_backend.entity.MedicineRequest;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicineRequestRepository extends MongoRepository<MedicineRequest, String> {
    List<MedicineRequest> findByRequestUsername(String requestUsername);

    List<MedicineRequest> findByOwnerUsername(String ownerUsername);

    MedicineRequest findByRequestId(String requestId);

    List<MedicineRequest> findByRequestTimeBeforeAndStatus(LocalDateTime requestExpiryTime, String status);

    List<MedicineRequest> findByRequestUsernameAndRequestTimeAfter(String requestUsername, LocalDateTime requestExpiryTime);
}
