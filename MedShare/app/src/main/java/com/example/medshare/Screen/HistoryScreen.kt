package com.example.medshare.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.medshare.R


@Composable
fun HistoryCard(modifier: Modifier){
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD8F8FB)
        )
    ){
        Row( modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(R.drawable.history_profile_logo),
                contentDescription = null,
                    modifier = Modifier
                        .padding(top = 13.dp, start = 10.dp)
            )
            Column(modifier = Modifier
                .padding(start = 40.dp, top = 13.dp)){
                Text(
                    text = "RUCHI SHARMA requested 2 ",
                    color = Color(0xFF123899),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "DOLO 500",
                    color = Color(0xFF123899),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Request was declined",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF367AF2),

                )

            }

        }

    }
}


@Composable
fun HistoryScreen(){

        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .background(Color.Transparent)
        ){
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.back_icon),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.TopStart)

                        )
                    Text(
                        text = "HISTORY",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.TopCenter)
                        ,

                        color = Color(0xFF123899)


                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            HistoryCard(modifier = Modifier)
            HistoryCard(modifier = Modifier)
            HistoryCard(modifier = Modifier)
            HistoryCard(modifier = Modifier)


        }



}

