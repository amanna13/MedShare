package com.medshare.app_backend.repository;

import com.medshare.app_backend.entity.Medicine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface MedicineRepository extends MongoRepository<Medicine, ObjectId> {
    List<Medicine> findByTagsIn(List<String> tags);
}
