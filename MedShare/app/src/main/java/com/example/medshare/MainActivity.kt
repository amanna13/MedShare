package com.example.medshare

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationImportant
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NotificationImportant
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medshare.ui.theme.MedShareTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        setContent{
            MedShareTheme {

                var selectedItem by remember { mutableIntStateOf(0) }
                navHostController = rememberNavController()



                val items = listOf("Home", "Request","History", "Profile")
                val routes = listOf("home", "RequestScreen", "HistoryScreen", "ProfileScreen")

                val excludednavbar = listOf("Login", "SignUp", "RegistrationScreen")

                val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.NotificationImportant, Icons.Filled.History, Icons.Filled.Person)
                val unselectedIcons =
                    listOf(Icons.Outlined.Home, Icons.Outlined.NotificationImportant, Icons.Outlined.History, Icons.Outlined.Person)

                val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route



                Scaffold(bottomBar = { if(currentRoute !in excludednavbar) {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                        contentDescription = item
                                    )
                                },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    navHostController.navigate(routes[index])
                                }
                            )
                        }
                    }
                }
                }, containerColor = Color.White){
                    Navigation(navHostController)
                }
            }
        }
    }
}

