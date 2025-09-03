package com.example.medshare.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medshare.Dto.MedicineUpload
import com.example.medshare.R
import com.example.medshare.ViewModels.UploadMedicine
import com.example.medshare.ui.theme.lightBlue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareMedicine(navHostController: NavHostController) {

    val uploadMedicineViewModel: UploadMedicine = hiltViewModel()

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf<Int?>(null) }

    var quantityText by remember { mutableStateOf("") }

//    var labels by remember { mutableStateOf<List<String>?>(null) }

    var labelsText by remember { mutableStateOf("") }
    var labels by remember { mutableStateOf<List<String>>(emptyList()) }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf<Date?>(null) }
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopStart).clickable { navHostController.navigate("home") }

            )
            Text(
                text = "SHARE MEDICINE",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.TopCenter),

                color = Color(0xFF123899)


            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Card(
            modifier = Modifier, colors = CardDefaults.cardColors(
                containerColor = lightBlue
            ), shape = RoundedCornerShape(20.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text("Medicine Name", color = Color(0xFF123899), fontSize = 14.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(0xFF123899),
                        focusedContainerColor = Color.White,   // Transparent background when focused
                        unfocusedContainerColor = Color.White, // Transparent background when not focused
                        focusedIndicatorColor = Color.Transparent,   // No underline when focused
                        unfocusedIndicatorColor = Color.Transparent  // No underline when not focused
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                TextField (
                    value = quantityText,
                    onValueChange = { it ->
                        quantityText = it
                        quantity = it.toIntOrNull()!!
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text("Add Quantity", color = Color(0xFF123899), fontSize = 14.sp ) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(0xFF123899),
                        focusedContainerColor = Color.White,   // Transparent background when focused
                        unfocusedContainerColor = Color.White, // Transparent background when not focused
                        focusedIndicatorColor = Color.Transparent,   // No underline when focused
                        unfocusedIndicatorColor = Color.Transparent  // No underline when not focused
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )


                TextField(
                    value = selectedDate?.let { formatter.format(it) } ?: "",
                    onValueChange = { },
                    label = { Text("Enter the Expiry Date", color = Color(0xFF123899), fontSize = 14.sp ) },
                    readOnly = true, // Makes the field non-editable
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "xpiry date")
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(0xFF123899),
                        focusedContainerColor = Color.White,   // Transparent background when focused
                        unfocusedContainerColor = Color.White, // Transparent background when not focused
                        focusedIndicatorColor = Color.Transparent,   // No underline when focused
                        unfocusedIndicatorColor = Color.Transparent  // No underline when not focused
                    ),
                    modifier = Modifier.padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                if (showDatePicker) {
                    Popup(
                        onDismissRequest = { showDatePicker = false },
                        alignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth().shadow(shape = RoundedCornerShape(20.dp), elevation = 8.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.surface)

                        ) {
                            Column {
                                DatePicker(
                                    state = datePickerState,
                                    showModeToggle = false,
                                    modifier = Modifier.height(470.dp)
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Button(onClick = {
                                        showDatePicker = false
                                    }) {
                                        Text("Cancel")
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(onClick = {
                                        val selectedMillis = datePickerState.selectedDateMillis
                                        if (selectedMillis != null) {
                                            selectedDate = Date(selectedMillis)
                                        }
                                        showDatePicker = false
                                    }) {
                                        Text("Confirm")
                                    }
                                }
                            }
                        }
                    }
                }

                TextField(
                    value = labelsText,
                    onValueChange = {
                        labelsText = it
                        labels = it.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text("Add Labels", color = Color(0xFF123899), fontSize = 14.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(0xFF123899),
                        focusedContainerColor = Color.White,   // Transparent background when focused
                        unfocusedContainerColor = Color.White, // Transparent background when not focused
                        focusedIndicatorColor = Color.Transparent,   // No underline when focused
                        unfocusedIndicatorColor = Color.Transparent  // No underline when not focused
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )


            }

        }
        Spacer(modifier = Modifier.height(19.dp))

        val finalDate = selectedDate?.let { formatter.format(it) }
        ShareButton(name, quantity, finalDate, labels, uploadMedicineViewModel, navHostController)
    }
}


@Composable
fun ShareButton(
    name: String,
    quantity: Int?,
    expiry: String?,
    labels: List<String>?,
    uploadMedicineViewModel: UploadMedicine,
    navHostController: NavHostController
) {
    Button(
        onClick = {
            println(expiry)
            if(quantity != null && expiry != null && labels != null) {
                val medicineDetails = MedicineUpload(name, quantity, expiry, listOf(labels.joinToString(",")))
                uploadMedicineViewModel.uploadMedicine(medicineDetails)
                navHostController.navigate("SuccessScreen")
                println("Uploaded")
            }
        },
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0xFF123899)
        ),
    ) {
        Text("ADD MEDICINE", color = Color.White)
    }
}