package com.example.sewastudio.view.pelanggan

//import com.example.sewastudio.MyWebView
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.sewastudio.MidtransActivity
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.controller.MidtransController
import com.example.sewastudio.service.ItemData
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingPage(
    navController: NavController,
    studio_id : String?,
    name : String?,
    price : Int?,
    date : String?,
    time : String?,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    if (studio_id == null || date == null || time == null) {
        navController.navigate("pelangganhomepage")
    }
    val convertedTime = time?.split("-")
    val startTime = convertedTime?.get(0)!!.split(".")
    val endTime = convertedTime?.get(1)!!.split(".")
    val convertedStartTime = LocalTime.of(startTime[0].toInt(),startTime[1].toInt())
    val convertedEndTime = LocalTime.of(endTime[0].toInt(),endTime[1].toInt())
    val duration = convertedEndTime.hour - convertedStartTime.hour
    if (duration == 0) {
        navController.navigate("detailstudiopage/$studio_id/$name/$price")
    }
    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColor = Color(0xFF1F41BB)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier
                                .background(
                                    color = primaryColor,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .width(36.dp)
                                .height(36.dp),
                            onClick = { navController.navigate("detailstudiopage/$studio_id/$name/$price") }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Kembali",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Booking Details",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF1F41BB)
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                ),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Lama Sewa",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF423C3C),
                            )
                        )
                        Text(
                            text = "$duration hours",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Tanggal Sewa",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF423C3C),
                            )
                        )
//                        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//                        var convertedDate = LocalDate.parse(date, formatter)
                        Text(
                            text = "$date",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Jam Sewa",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF423C3C),
                            )
                        )
                        Text(
                            text = "${convertedStartTime} ${
                                if (convertedStartTime.hour in 12..23) {
                                    "PM"
                                } else {
                                    "AM"
                                }
                            } - ${convertedEndTime} ${
                                if (convertedEndTime.hour in 12..23) {
                                    "PM"
                                } else {
                                    "AM"
                                }
                            }",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Sub Total",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF423C3C),
                            )
                        )
                        if (price != null) {
                            Text(
                                text = "Rp. ${price*duration}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                )
                            )
                        }
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Tax",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF423C3C),
                            )
                        )
                        Text(
                            text = "-",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Grand Total",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
                        if (price != null) {
                            Text(
                                text = "Rp. ${price*duration}",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                )
                            )
                        }
                    }
                    Divider(modifier = Modifier
                        .padding(0.dp)
                        .width(357.0014.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFE9E6E6)))


                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
                    .size(48.dp),
                onClick = {
                    val jwt = preferencesManager.getData("jwt")
                    val userID = preferencesManager.getData("userID")
                    val username = preferencesManager.getData("username")

                    MidtransController.getSnapToken(jwt, userID, username, ItemData(id = studio_id!!, startTime = convertedStartTime.toString()+":00.000", endTime = convertedEndTime.toString()+":00.000", date = date!!, price = price!!.toInt()),preferencesManager, callback = { snapToken ->
                        var studioschedule = preferencesManager.getData("studioschedule")
                        if (snapToken!!.data != null || snapToken.data!! != "") {
                            Intent(context, MidtransActivity::class.java).also {
                                it.putExtra("jwt",jwt)
                                it.putExtra("studioschedule", studioschedule)
                                it.putExtra("snapToken", snapToken.data)
                                ContextCompat.startActivity(context, it, null)
                            }
                        } })

                }, colors = ButtonDefaults.buttonColors(primaryColor)
            ) {
                Text(text = "Selanjutnya", color = Color.White)
            }
        }
    }
}
