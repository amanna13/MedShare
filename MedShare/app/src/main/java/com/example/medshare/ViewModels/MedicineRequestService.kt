package com.example.medshare.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medshare.Dto.RequestResponseItem
import com.example.medshare.RetrofitInterface.RequestService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineRequestService @Inject constructor(private val requestService: RequestService) :
    ViewModel() {

    private val _requestMedicineStatus = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val requestMedicineStatus: StateFlow<Map<String, Boolean>> = _requestMedicineStatus

    private val _activeRequests = MutableStateFlow<List<RequestResponseItem>>(emptyList())
    val activeRequests: StateFlow<List<RequestResponseItem>> = _activeRequests.asStateFlow()

    private val _pendingRequests = MutableStateFlow<List<RequestResponseItem>>(emptyList())
    val pendingRequests: StateFlow<List<RequestResponseItem>> = _pendingRequests.asStateFlow()

    private val _otpResponse = MutableStateFlow<Map<String, String>>(emptyMap())
    val otpResponse: StateFlow<Map<String, String>> = _otpResponse.asStateFlow()

    private val _requestStatus = MutableStateFlow<Map<String, String>>(emptyMap()) // ✅ Track status per request whether accepted or declined
    val requestStatus: StateFlow<Map<String, String>> = _requestStatus.asStateFlow()

    private val _otpSuccess = MutableStateFlow(false) // ✅ Track OTP verification status
    val otpSuccess: StateFlow<Boolean> = _otpSuccess.asStateFlow()


    fun requestMedicine(medicineID: String, ownerUsername: String) {
        viewModelScope.launch {
            try {
                val response = requestService.requestMedicine(medicineID, ownerUsername)
                if (response.isSuccessful) {
                    _requestMedicineStatus.update { currentStatus ->
                        currentStatus + (medicineID to true)
                    }
                    println(requestMedicineStatus.value)
                    println("Medicine Requested Successfully")

                } else {
                    println("Medicine Request Failed" + response.code())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun checkMyActiveRequest() {
        viewModelScope.launch {
            try {
                val response = requestService.getActiveRequests()
                if (response.isSuccessful) {
                    response.body()?.let { activeRequestsList ->
                        _activeRequests.value = activeRequestsList
                    }
                } else {
                    println("Error fetching Active Request - " + response.code())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }


    fun acceptRequest(requestID: String) {
        viewModelScope.launch {
            try {
                val response = requestService.acceptRequest(requestID)
                if (response.isSuccessful) {
                    println("Success !")
                    val otp = response.body() ?: "No OTP"
                    _otpResponse.update { it + (requestID to otp) } // ✅ Store OTP
                    _requestStatus.update { it + (requestID to "Accepted") }
                } else {
                    println("Request is unsuccessful - ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error Accepting Request - ${e.message}")
            }
        }

    }

    fun declineRequest(requestID: String) {
        viewModelScope.launch {
            try {
                val response = requestService.declineRequest(requestID)
                if (response.isSuccessful) {
                    _requestStatus.update { it + (requestID to "Declined") }
                } else {
                    println("Request is unsuccessful - ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error declining request - ${e.message}")
            }
        }
    }

    fun getAllPendingRequest() {
        viewModelScope.launch {
            try {
                val response = requestService.getAllPendingRequest()
                if (response.isSuccessful) {
                    _pendingRequests.value = response.body() ?: emptyList()
                } else {
                    println("Pending Request Shows Error - ${response.code()}")
                }
            } catch (e: Exception) {
                println()
            }
        }
    }

    fun confirmOTP(requestID: String, OTP: String) {
        viewModelScope.launch {
            try {
                val response = requestService.confirmFinalSharing(requestID, OTP)
                if (response.isSuccessful) {
                    _otpSuccess.value = true
                    println("OTP Verified Successfully")
                } else {
                    _otpSuccess.value = false
                }
            } catch (e: Exception) {
                _otpSuccess.value = false
                println(e.message)
            }
        }
    }
}
