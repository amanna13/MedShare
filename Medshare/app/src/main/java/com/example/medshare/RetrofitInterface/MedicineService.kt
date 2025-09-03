package com.example.medshare.RetrofitInterface

import com.example.medshare.Dto.MedicineUpload
import com.example.medshare.Dto.SearchResults
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MedicineService {
    @POST("medicine/add_medicine")
    suspend fun uploadMedicine(@Body medicineDetails: MedicineUpload): Response<ResponseBody>

    @GET("medicine/search")
    suspend fun searchMedicine(@Query("tags") tags: List<String>): Response<SearchResults>
}