package com.example.medshare.Screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medshare.R
import com.example.medshare.Utils
import com.example.medshare.ViewModels.MedicineRequestService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {

    val requestMedicineViewModel: MedicineRequestService = hiltViewModel()
    requestMedicineViewModel.checkMyActiveRequest()
    val activeRequests = requestMedicineViewModel.activeRequests.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedRequestID by remember { mutableStateOf<String?>(null) }

    val otpSuccess by requestMedicineViewModel.otpSuccess.collectAsState()

    LaunchedEffect(otpSuccess) {
        if (otpSuccess) {
            navHostController.navigate("SuccessScreen") // ✅ Navigate on success
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(20.dp)
            .padding(bottom = 10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.medshare_logo),
            contentDescription = "App name",
            modifier = Modifier.size(120.dp)
        )

        WelcomeCard(modifier = Modifier)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.button_logo_first),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 0.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {

                    navHostController.navigate("ShareMedicine")

                }, colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF123899)
                ), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Share Medicines", color = Color.White
                )

            }

        }
        Row(
            modifier = modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.button_logo_second),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navHostController.navigate("SearchMedicine") },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF123899)
                ),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Request Medicines", color = Color.White
                )

            }

        }
        Spacer(modifier = Modifier.height(24.dp))

//        Text(
//            "Recent Interaction",
//            modifier = Modifier.align(alignment = Alignment.Start),
//            color = Color(0xFF123899),
//        )
//
//        InteractionCard(
//            modifier = Modifier, profText = "Shared Paracetamol Zakir-E;QTY:25 meters from you"
//        )

        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Active Request", fontWeight = FontWeight.SemiBold)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFA9A9A9),
                    modifier = Modifier.padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(activeRequests.value.size) { request ->
                        val requestId = activeRequests.value[request].requestId
                        ActiveRequest(
                            activeRequests.value[request].medicineName,
                            activeRequests.value[request].ownerUsername,
                            activeRequests.value[request].requestTime,
                            activeRequests.value[request].status,
                            activeRequests.value[request].requestId,
                            onShowBottomSheet = { requestId ->
                                selectedRequestID = requestId
                                showBottomSheet = true
                            }
                        )
                    }
                }

            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = { showBottomSheet = false }
        ) {
            selectedRequestID?.let { requestID ->
                OTPEntryBottomSheet(
                    requestID = requestID,
                    onConfirmOTP = { enteredOtp ->
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun OTPEntryBottomSheet(requestID: String, onConfirmOTP: (String) -> Unit) {
    var enteredOtp by remember { mutableStateOf("") }
    val requestViewModel: MedicineRequestService = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Icon(Icons.Default.Lock, "")
            Text(
                text = "Enter OTP",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF123899)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        OTPTextField(enteredOtp) { enteredOtp = it }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                requestViewModel.confirmOTP(requestID, enteredOtp)
                onConfirmOTP(enteredOtp)
                      }, // ✅ Calls ViewModel to verify OTP
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF689F38)), modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Confirm", color = Color.White, fontSize = 16.sp, modifier = Modifier)
        }
    }
}

//Stylized OTP TEXT FIELD !
@Composable
fun OTPTextField(otpValue: String, onOtpChange: (String) -> Unit) {
    TextField(
        value = otpValue,
        onValueChange = { newValue ->
            if (newValue.length <= 6 && newValue.all { it.isDigit() }) { // ✅ Allow only 6 digits
                onOtpChange(newValue)
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp)), // ✅ Rounded corners
        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, letterSpacing = 10.sp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}




@Composable
fun ActiveRequest(
    medicineName: String,
    ownerUsername: String,
    requestTime: String,
    requestStatus: String,
    requestId: String,
    onShowBottomSheet: (String) -> Unit
) {
    val formatTime = Utils.formatDateTime(requestTime)

    val isAccepted = requestStatus == "ACCEPTED"

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.StickyNote2, contentDescription = "")
                Column(modifier = Modifier.padding(start = 5.dp)) {
                    Text(
                        text = medicineName,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                    Text(text = "${ownerUsername} | ${formatTime}", fontSize = 12.sp)
                }
            }
            Button(
                onClick = { if (isAccepted) onShowBottomSheet(requestId) },
                enabled = isAccepted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = when(requestStatus){
                        "ACCEPTED" -> Color(0xFF689F38)
                        "DECLINED" -> Color(0xFFD81B60)
                        else -> Color.Gray // Pending
                    }
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(requestStatus, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun WelcomeCard(modifier: Modifier) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF123899))
    ) {

        Row(modifier = Modifier.padding(30.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.carousel_new),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    "Ready to Help your Peers !",
                    color = Color.White,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    "Share Medicine Now",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



