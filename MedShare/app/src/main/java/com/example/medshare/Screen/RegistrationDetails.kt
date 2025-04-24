package com.example.medshare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun Register(){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD8F8F8))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.medshare_logo), contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(100.dp)
                    .offset(y = 30.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.welcome), contentDescription = "Welcome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .size(50.dp)
                    .offset(y = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.width(300.dp)) {
                Text(text = "Hostel Name", fontWeight = FontWeight.Bold, color = Color(0xFF0166B6))
                dropDownMenu()
            }
            Spacer(modifier = Modifier.height(30.dp))

            Column(modifier = Modifier.width(300.dp)) {
                Text(text = "Hostel Block", fontWeight = FontWeight.Bold, color = Color(0xFF0166B6))
                dropDownMenu()
            }
            Spacer(modifier = Modifier.height(30.dp))

            Column(modifier = Modifier.width(300.dp)) {
                Text(text = "Hostel Floor", fontWeight = FontWeight.Bold, color = Color(0xFF0166B6))
                dropDownMenu()
            }
            Spacer(modifier = Modifier.height(30.dp))

            Column(modifier = Modifier.width(300.dp)) {
                Text(text = "Room Number", fontWeight = FontWeight.Bold, color = Color(0xFF0166B6))
                dropDownMenu()
            }
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF123999)),
                modifier = Modifier.padding(16.dp)
                    .width(320.dp)
                    .height(60.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(50),
            ) { Text(text = "UPDATE DETAILS") }


        }
    }
}

@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("TAGORE", "SUKHNA", "GOVIND", "LE CORBUSIER", "SHIVALIK", "ZAKIR", "NC")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .background(color = Color(0xFFFFFFFF))
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Select an Option") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedText = label
                        expanded = false
                    }
                )
            }
        }
    }
}