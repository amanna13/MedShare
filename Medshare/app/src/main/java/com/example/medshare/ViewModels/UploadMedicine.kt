package com.example.medshare.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medshare.Dto.MedicineUpload
import com.example.medshare.RetrofitInterface.MedicineService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadMedicine @Inject constructor(private val medicineService: MedicineService) : ViewModel(){


    fun uploadMedicine(medicineDetails: MedicineUpload) {
        viewModelScope.launch {
            try {
//                val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
//                val token = sharedPreferences.getString("jwt_token", null)
//                val authHeader = "Bearer $token"
//                println(authHeader)
                val response = medicineService.uploadMedicine(medicineDetails)
                if ( response.isSuccessful) {
                    println(response.body())
                } else {
                    println(response.code())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}

