package com.example.medshare

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medshare.Screen.HistoryScreen
import com.example.medshare.Screen.HomeScreen
import com.example.medshare.Screen.ProfileScreen
import com.example.medshare.Screen.RequestScreen
import com.example.medshare.Screen.SearchMedicine
import com.example.medshare.Screen.ShareMedicine
import com.example.medshare.Screen.SuccessScreen
import com.example.myapplication.Login

@Composable
fun Navigation(navHostController: NavHostController) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("jwt_token", null)

    val startDestination = if (token != null) "home" else "Login"

    NavHost(navController = navHostController, startDestination = startDestination, enterTransition = { EnterTransition.None }, exitTransition = { ExitTransition.None}, popExitTransition = { ExitTransition.None}, popEnterTransition = { EnterTransition.None}) {

        composable("Login") {
            Login(navHostController)
        }

        composable("SignUp") {
            SignUp()
        }

        composable("RegistrationScreen") {
            Register()
        }

        composable("home") {
            HomeScreen(navHostController)
        }

        composable("RequestScreen") {
            RequestScreen(navHostController)
        }

        composable("ShareMedicine") {
            ShareMedicine(navHostController)
        }

        composable("SearchMedicine") {
            SearchMedicine(navHostController)
        }

        composable("ProfileScreen"){
            ProfileScreen(navHostController)
        }

        composable("HistoryScreen") {
            HistoryScreen()
        }

        composable("SuccessScreen"){
            SuccessScreen(navHostController)
        }
    }
}