package com.example.medshare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUp() {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(id = R.drawable.medshare_logo),
            contentDescription = "Logo",
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Row(modifier = Modifier.padding(vertical = 10.dp).clip(RoundedCornerShape(50.dp)).background(Color(0xFFD8F8FB)).padding(horizontal = 10.dp)) {
            Text(text = "JOIN ", color = Color(0xFF123899), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = "the community and offer a helping hand", fontSize = 14.sp)
        }


        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(value = username,
            onValueChange = { text -> username = text },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(vertical = 4.dp),
            label = { Text(text = "USERNAME") },
            colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
        )
        OutlinedTextField(value = email,
            onValueChange = { text -> email = text },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            modifier = Modifier.padding(vertical = 4.dp),
            label = { Text(text = "EMAIL ADDRESS") },
            colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
        )
        OutlinedTextField(
            value = password,
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            onValueChange = { text -> password = text },
            modifier = Modifier.padding(vertical = 4.dp),
            label = { Text(text = "PASSWORD") },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
        )
        OutlinedTextField(value = confirmpassword,
            onValueChange = { text -> confirmpassword = text },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(vertical = 4.dp),
            label = { Text(text = "CONFIRM PASSWORD") },
            colors = OutlinedTextFieldDefaults.colors(cursorColor = Color.Black, focusedLabelColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
        )

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF123999)),
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp)
                .height(60.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(50),
        ) { Text(text = "REGISTER", color = Color.White) }

//        Image(painter = painterResource(id = R.drawable.img_2), contentDescription = "blob2",
//            modifier = Modifier
//                .offset(x= (-70).dp,y = (40).dp)
//                .size(800.dp)
//                )

    }
}