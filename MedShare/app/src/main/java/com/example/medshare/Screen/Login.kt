package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medshare.Dto.LoginRequest
import com.example.medshare.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medshare.ViewModels.Login

@Composable
fun Login(navHostController: NavHostController) {

    val loginViewModel: Login = hiltViewModel()

    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),

    ) {
        Image(
            painter = painterResource(id = R.drawable.blob), contentDescription = "Blob",
            modifier = Modifier.offset(y= (-60).dp)
                .align(Alignment.TopCenter)

        )
        Column(
            modifier = Modifier
                .padding(20.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 60.dp)) {

                Text(
                    text = "WELCOME BACK!", modifier = Modifier.padding(0.dp),
                    fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF123899)
                )
                //                        Image(painter = painterResource(id = R.drawable.img_1), contentDescription = "blob")
                Image(
                    painter = painterResource(id = R.drawable.medshare_logo2),
                    contentDescription = "MEDSHARE",
                    modifier = Modifier.padding(vertical = 30.dp).size(150.dp)
                )
            }



                OutlinedTextField(value = username,
                    onValueChange = { it ->
                        username = it
                    }, shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                        .padding(vertical = 4.dp),
                    label = { Text(text = "USERNAME") },
                    colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
                    )


                    OutlinedTextField(value = email,
                        onValueChange = { it ->
                            email = it
                        },shape = RoundedCornerShape(10.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                            .padding(vertical = 4.dp),
                        label = { Text(text = "EMAIL ADDRESS") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
                    )


                    OutlinedTextField(
                        value = password,
                        onValueChange = { it ->
                            password = it
                        }, shape = RoundedCornerShape(10.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                            .padding(vertical = 4.dp),
                        label = { Text(text = "PASSWORD") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black),
                        visualTransformation = PasswordVisualTransformation()
                    )



                Button(
                    onClick = {
                        try {

                            val userDetails = LoginRequest(username, password)
                            loginViewModel.login(userDetails, context, navHostController)

                        }catch (e: Exception){
                            println(e.message)
                        }
                    }, modifier = Modifier
                        .padding(16.dp)
                        .width(300.dp)
                        .height(60.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "LOGIN NOW")
                }


                Text(
                    text = "FORGOT PASSWORD?",
                    modifier = Modifier.clickable {},
                    color = Color(0xFF123899),
                    fontStyle = FontStyle.Italic
                )
                Row {
                    Text(
                        text = "Don't have an account? ",
                        color = Color(0xFF123899),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Sign Up now!",
                        modifier = Modifier.clickable {navHostController.navigate("SignUp")},
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF123899)
                    )
                }

        }
    }
}

