package com.example.medshare.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medshare.R
import com.example.medshare.ViewModels.MedicineRequestService
import com.example.medshare.ViewModels.SearchResults
import java.util.Locale

@Composable
fun SearchMedicine(navHostController: NavHostController) {

    val searchResultsViewModel: SearchResults = hiltViewModel()
    val requestMedicineViewModel: MedicineRequestService = hiltViewModel()

    val requestStatus by requestMedicineViewModel.requestMedicineStatus.collectAsState()

    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .padding(10.dp),
            ) {
                Image(painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart).clip(RoundedCornerShape(50.dp))
                        .clickable { navHostController.navigate("home") }
                )
                Text(
                    text = "Search for Medicine",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = Color(0xFF123899)

                )
            }

            TextField(value = name,
                onValueChange = { name = it },
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, lineHeight = 14.sp),
                placeholder = {
                    Text(
                        "Medicine Name",
                        color = Color(0xFF123899),
                        fontSize = 14.sp
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(KeyboardActions.Default.onSearch),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(0xFF123899),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(80.dp)
                    .padding(vertical = 10.dp)
                    .shadow(shape = RoundedCornerShape(50.dp), elevation = 8.dp)
                    .clip(RoundedCornerShape(50.dp)),

                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.padding(20.dp).clip(RoundedCornerShape(50.dp)).clickable {
                            println("Clicked Search")
                            searchResultsViewModel.searchResults(listOf(name))

                        })
                })

            val searchResults by searchResultsViewModel.searchResult.collectAsState()
            println(searchResults)

            LazyColumn(contentPadding = PaddingValues(bottom = 80.dp)) {
                items(searchResults.size) { index ->
                    SearchResulsCards(
                        searchResults[index].medicine_name,
                        searchResults[index].id,
                        searchResults[index].ownerID,
                        searchResults[index].ownerLocation,
                        searchResults[index].quantity,
                        requestMedicineViewModel,
                        requestStatus
                    )
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}


@Composable
fun SearchResulsCards(
    medicine_name: String,
    medicine_id: String,
    ownerName: String,
    ownerLocation: String,
    quantity: Int,
    requestMedicineViewModel: MedicineRequestService,
    requestStatus: Map<String, Boolean>,
    modifier: Modifier = Modifier
) {
    val isRequested = requestStatus[medicine_id] ?: false

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x82D8F8FB)), shape = RoundedCornerShape(10.dp)
        ) {
        Column(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Text(medicine_name.uppercase(), color = Color(0xFF123899), fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth().padding(10.dp)
            ) {
               Row(verticalAlignment = Alignment.CenterVertically, ) {
                   Image(
                       painter = painterResource(R.drawable.profile_logo),
                       contentDescription = null,
                       modifier = Modifier.size(50.dp)
                   )
                   Column(
                       modifier = modifier.padding(start = 20.dp)
                   ) {
                       Text(ownerName, color = Color(0xFF123899))
                       Text(ownerLocation, color = Color(0xFF123899))
                       Text(
                           "Available Qty: ${quantity}", color = Color(0xFF123899), fontSize = 12.sp
                       )
                   }
               }

                Button(
                    onClick = {
                        requestMedicineViewModel.requestMedicine(medicine_id, ownerName)
                    }, colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isRequested) Color.Gray else Color(0xFF123899)
                    ), modifier = modifier
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        println(requestStatus)
                        if(isRequested){
                            Text("Request Sent !", color = Color.White)
                        }else {
                            Text("Request", color = Color.White)
                            Icon(
                                Icons.AutoMirrored.Filled.Send,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp).padding(start = 5.dp)
                            )
                        }
                        }
                }
            }

        }

    }

}