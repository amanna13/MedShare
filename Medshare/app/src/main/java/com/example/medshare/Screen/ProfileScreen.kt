package com.example.medshare.Screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medshare.R


@Composable
fun ProfileScreen(navHostController: NavHostController){
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFFD8F8FB)).padding(top = 60.dp),

        ) {
            Column(modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.back_icon),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 10.dp)
                            .clip(RoundedCornerShape(50.dp)).background(Color(0xA9C3DFE3)).clickable { navHostController.popBackStack() }.padding(10.dp).align(Alignment.TopStart)
                    )
                    Text(
                        text = "Profile",
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_logo),
                    contentDescription = null,
                    modifier = Modifier
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "Your Profile",
                    modifier = Modifier,
                    fontSize = 25.sp,
                    color = Color(0xFF123899)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp).padding(bottom = 100.dp)

        ) {

            Column {

                ProfileInformationField(
                    profileText = "USERNAME",
                    profileDetail = "USERNAME",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "EMAIL ADDRESS",
                    profileDetail = "EMAIL ADDRESS",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "PASSWORD",
                    profileDetail = "PASSWORD",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "HOSTEL NAME",
                    profileDetail = "HOSTEL NAME",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "HOSTEL BLOCK",
                    profileDetail = "HOSTEL BLOCK",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "HOSTEL FLOOR",
                    profileDetail = "HOSTEL FLOOR",
                    modifier = Modifier
                )
                ProfileInformationField(
                    profileText = "ROOM NUMBER",
                    profileDetail = "ROOM NUMBER",
                    modifier = Modifier
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInformationField(profileText : String, profileDetail: String, modifier: Modifier){

    var profileText by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding()) {
        Text(profileDetail, color = Color(0xFF123899))
        OutlinedTextField(
            value = profileText,
            onValueChange = { profileText = it },
            singleLine = true,
            label = { Text(profileDetail, style = MaterialTheme.typography.bodyMedium) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(unfocusedTextColor = Color.Black, focusedTextColor = Color.Black,unfocusedContainerColor = Color(0x35C1EAFE), focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedContainerColor = Color(0x35C1EAFE), cursorColor = Color.Black, focusedLabelColor = Color.Black, unfocusedLabelColor = Color.Black),
            modifier = modifier
                .fillMaxWidth()
                .padding()

        )
    }

}



