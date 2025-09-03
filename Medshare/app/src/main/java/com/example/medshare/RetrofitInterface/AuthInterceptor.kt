package com.example.medshare.RetrofitInterface

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null)

        val originalRequest = chain.request()

        if(originalRequest.url.encodedPath.contains("auth/login") || originalRequest.url.encodedPath.contains("auth/register")){
            return chain.proceed(originalRequest)
        }
        println(token)

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer "+ token)
            .build()

        return chain.proceed(newRequest)
    }
}