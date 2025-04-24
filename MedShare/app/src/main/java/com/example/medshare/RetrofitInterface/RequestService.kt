package com.example.medshare.RetrofitInterface

import com.example.medshare.Dto.RequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestService {

    @GET("request/activerequest")
    suspend fun getActiveRequests(): Response<RequestResponse>

    @GET("request/pendingRequest")
    suspend fun getAllPendingRequest(): Response<RequestResponse>

    @POST("request/{medicineID}/{ownerUsername}")
    suspend fun requestMedicine(
        @Path("medicineID") medicineID: String,
        @Path("ownerUsername") ownerUsername: String
    ): Response<String>

    @POST("request/pendingRequest/{requestID}/accept")
    suspend fun acceptRequest(
        @Path("requestID") requestID: String
    ): Response<String>

    @POST("request/pendingRequest/{requestID}/accept")
    suspend fun declineRequest(
        @Path("requestID") requestID: String
    ): Response<String>

    @GET("request/myhistory")
    suspend fun myHistory() : Response<RequestResponse>


    @POST("request/finalConfirm/{RequestId}/verification")
    suspend fun confirmFinalSharing(
        @Path("RequestId") requestId: String,  // ✅ Pass Request ID as a path variable
        @Query("OTP") OTP: String  // ✅ Pass OTP as a query parameter
    ): Response<String>

}