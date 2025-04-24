package com.example.medshare.ViewModels

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.medshare.Dto.LoginRequest
import com.example.medshare.RetrofitInterface.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Login @Inject constructor(private val authService: AuthService) : ViewModel(){

    val loginResponse = mutableStateOf<String>("")

    fun login(userDetails : LoginRequest, context: Context, navHostController: NavHostController){
        viewModelScope.launch {
            try {
                println(userDetails)
                val response = authService.login(userDetails)
                if (response.isSuccessful) {
                    println(response.body())
                    loginResponse.value = response.body().toString()
                    saveToken(context, response.body().toString(), userDetails.userName)
                    navHostController.navigate("home")
                } else {
                    loginResponse.value = "Invalid Credentials"
                    println("Login failed: ${response.code()}")
                }
            }catch (e: Exception){
                println(e.message)
            }
        }
    }
}

private fun saveToken(context: Context, token: String, username: String) {
    val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("jwt_token", token).putString("username", username).apply()
}

//class LoginViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(Login::class.java)) {
//            return Login(application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
