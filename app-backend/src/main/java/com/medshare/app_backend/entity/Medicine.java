package com.medshare.app_backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "medicines")
@NoArgsConstructor
public class Medicine {

    @Id
    private String id;

    @NonNull
    private String medicine_name;
    private int quantity;
    private List<String> tags;
    private Date expiry_date;
    private String ownerID;
    private String ownerName;
    private String ownerLocation;
    private String ownerPhoneNumber;

}
