package com.example.medshare.Dto

import java.util.Date

data class MedicineUpload(
    val medicine_name : String,
    val quantity : Int,
    val expiry_date : String,
    val tags : List<String>
)
