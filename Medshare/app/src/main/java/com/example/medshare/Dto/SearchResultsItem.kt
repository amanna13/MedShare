package com.example.medshare.Dto

data class SearchResultsItem(
    val expiry_date: String,
    val id: String,
    val medicine_name: String,
    val ownerID: String,
    val ownerLocation: String,
    val ownerName: String,
    val ownerPhoneNumber: String,
    val quantity: Int,
    val tags: List<String>
)