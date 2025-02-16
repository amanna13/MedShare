package com.medshare.app_backend.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NonNull
    private String name ;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String hostelDetails;

}
