package com.example.medshare.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.medshare.R
import com.example.medshare.ViewModels.MedicineRequestService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(navHostController: NavHostController) {

    val requestMedicineViewModel: MedicineRequestService = hiltViewModel()
    requestMedicineViewModel.getAllPendingRequest()
    val pendingRequest by requestMedicineViewModel.pendingRequests.collectAsState()

    val requestStatus by requestMedicineViewModel.requestStatus.collectAsState()
    val otpResponse by requestMedicineViewModel.otpResponse.collectAsState()

    var selectedOtp by remember { mutableStateOf<String?>(null) }

    var selectedExpiryTime by remember { mutableStateOf<Long?>(null) }


    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
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
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(50.dp))
                        .clickable { navHostController.navigate("home") })
                Text(
                    text = "Incoming Medicine Request",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = Color(0xFF123899)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(pendingRequest.size) { item ->
                    RequestedUserCards(
                        pendingRequest[item].medicineName,
                        pendingRequest[item].requestUsername,
                        pendingRequest[item].requestLocation,
                        pendingRequest[item].requestId,
                        onAccept = { requestMedicineViewModel.acceptRequest(pendingRequest[item].requestId) },
                        onDecline = { requestMedicineViewModel.declineRequest(pendingRequest[item].requestId) },
                        requestStatus,
                        otpResponse,
                        onShowBottomSheet = { otp, expiryTime ->
                        selectedOtp = otp
                        selectedExpiryTime = expiryTime
                        showBottomSheet = true
                    }
                    )
                }
            }

        }
    }



    if (showBottomSheet) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ModalBottomSheet(modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false}) {
                selectedOtp?.let { otp ->
                    selectedExpiryTime?.let { expiryTime ->
                        OTPBottomSheet(otp = otp, expiryTime = expiryTime)
                    }
                }
            }


        }
    }
}

@Composable
fun OTPBottomSheet(otp: String, expiryTime: Long) {
    val remainingTime by remember {
        mutableStateOf(expiryTime - System.currentTimeMillis()) // ✅ Calculate time remaining
    }

    val minutes = (remainingTime / 1000) / 60

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "SHARING CONFIRMED ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF123899)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Box(
                    modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFF343434))
                        .padding(10.dp).padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PIN for this Share - $otp",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White, modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                Box(
                    modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFF343434))
                        .padding(10.dp).padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "PIN Expires in: $minutes min", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Red, modifier = Modifier.padding(horizontal = 10.dp))
                }
            }

            Image(painter = painterResource(R.drawable.undraw_authentication_tbfc), contentDescription = "", modifier = Modifier.fillMaxWidth().size(200.dp))
        }
    }
}



@Composable
fun RequestedUserCards(
    medicine_name: String,
    ownerName: String,
    ownerLocation: String,
    requestID: String,
    onAccept: (String) -> Unit,
    onDecline: (String) -> Unit,
    requestStatus: Map<String, String>,
    otpResponse: Map<String, String>,
    onShowBottomSheet: (String, Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val status = requestStatus[requestID] // ✅ Get request status (Accepted/Declined)
    val otp = otpResponse[requestID] // ✅ Get OTP if available

    val expiryTime = remember { System.currentTimeMillis() + (2 * 60 * 60 * 1000) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x82D8F8FB)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Text(
                medicine_name.uppercase(),
                color = Color(0xFF123899),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_logo),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Column(
                        modifier = modifier.padding(start = 20.dp)
                    ) {
                        Text(ownerName, color = Color(0xFF123899))
                        Text(ownerLocation, color = Color(0xFF123899))

                    }

                }


            }
            if (status == "Accepted" || status == "Declined") {
                Button(
                    onClick = {otp?.let { onShowBottomSheet(it, expiryTime) }},
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = if (status == "Accepted") Color(0xFF689F38) else Color(0xFFD81B60)),
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Text(
                        text = if (status == "Accepted") "Sharing Confirmed! Tap to Learn More" else "OOPS! Sharing Declined",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            } else {

                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = { onDecline(requestID) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFD81B60)
                        ),
                        modifier = modifier
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("DECLINE", color = Color.White)
                            Icon(
                                Icons.Default.Cancel,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(22.dp)
                                    .padding(start = 5.dp)
                            )
                        }
                    }

                    Button(
                        onClick = { onAccept(requestID) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF689F38)
                        ),
                        modifier = modifier
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("CONFIRM", color = Color.White)
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(22.dp)
                                    .padding(start = 5.dp)
                            )
                        }
                    }
                }
            }


        }

    }

}