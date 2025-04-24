package com.example.medshare.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.medshare.R

@Composable
fun SuccessScreen(navHostController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        val searching = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successfullydone))
        Box(modifier = Modifier.size(300.dp).background(Color.White), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = searching.value,
                iterations = LottieConstants.IterateForever,
                isPlaying = true,
                modifier = Modifier.size(300.dp)
            )
        }
        Button(
            onClick = {
                navHostController.navigate("home")
            },
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF123899)), modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Back to Home", color = Color.White, fontSize = 16.sp, modifier = Modifier)
        }
    }
}