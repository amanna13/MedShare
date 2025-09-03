package com.example.medshare.Dto

data class RequestResponseItem(
    val medicineID: String,
    val medicineName: String,
    val otp: Any,
    val ownerLocation: String,
    val ownerPhoneNumber: String,
    val ownerUsername: String,
    val requestId: String,
    val requestLocation: String,
    val requestPhoneNumber: String,
    val requestTime: String,
    val requestUsername: String,
    val status: String
)