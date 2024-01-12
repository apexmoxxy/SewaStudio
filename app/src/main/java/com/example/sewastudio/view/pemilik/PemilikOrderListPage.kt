package com.example.sewastudio.view.pemilik

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sewastudio.BottomNavigationPemilik
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.R
import com.example.sewastudio.controller.StudioScheduleController
import com.example.sewastudio.model.StudioSchedule
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PemilikOrderListPage(navController: NavController,context : Context = LocalContext.current){
    val preferencesManager = remember { PreferencesManager(context = context) }
    var schedules by remember { mutableStateOf<List<StudioSchedule>?>(null) }
    val jwt = preferencesManager.getData("jwt")
    val userID = preferencesManager.getData("userID")
    StudioScheduleController.getStudioSchedule(
        jwt = jwt,
        userID = userID.toInt())
    { response ->
        schedules = response?.data
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FortuneSpace",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF)
                        )
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF1F41BB),
                ),
            )
        },floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate("createstudiopage")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Studio")
            }
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigationPemilik(navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LazyColumn {

                    schedules?.forEach { schedule ->
                        android.os.Handler().postDelayed({
                            val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                            var currentDate = dateFormat.format(Calendar.getInstance().time)
                            var bookDateReversed = schedule.attributes.bookdate!!.split("-").reversed().joinToString("-")
                            var startTime = schedule.attributes.start_time!!.split(":")
                            var endTime = schedule.attributes.end_time!!.split(":")
                            var currentTime = LocalTime.now()
                            var isTime = LocalTime.of(startTime[0].toInt(),startTime[1].toInt()).isAfter(currentTime)
                            var isFinished = currentTime.isAfter(LocalTime.of(endTime[0].toInt(),endTime[1].toInt()))
                            if (schedule.attributes.status == "accepted") {
                                if (bookDateReversed!! == currentDate) {
                                    if (isTime) {
                                        StudioScheduleController.editStudioSchedule(jwt, schedule.id, "ongoing")
                                    }
                                }
                            }else if (schedule.attributes.status == "ongoing") {
                                if (isFinished) {
                                    StudioScheduleController.editStudioSchedule(jwt, schedule.id, "finished")
                                }
                            }
                        },1000)
                        item {
                            Spacer(modifier = Modifier.padding(vertical = 10.dp))
                            Column(
                                verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .width(342.dp)
                                    .background(
                                        color = Color(0xFFF4F4F4),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                            ) {
                                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                                Text(
                                    text = schedule.attributes.studio!!.attributes.name!!,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                                )
                                Text(
                                    text = schedule.attributes.bookdate!!,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                                )
                                Text(
                                    text = "Deskripsi Studio:",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(
                                        start = 18.dp,
                                        end = 18.dp,
                                        top = 16.dp,
                                        bottom = 4.dp
                                    )
                                )
                                Text(
                                    text = "Studio foto estetik buat pasangan muda",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(
                                        start = 18.dp,
                                        end = 18.dp,
                                        top = 4.dp,
                                        bottom = 18.dp
                                    )
                                )
                                Row {

                                    Button(
                                        modifier = Modifier
                                            .shadow(
                                                elevation = 20.dp,
                                                spotColor = Color(0xFFCBD6FF),
                                                ambientColor = Color(0xFFCBD6FF)
                                            )
                                            .padding(
                                                start = 18.dp,
                                                end = 18.dp,
                                                top = 4.dp,
                                                bottom = 18.dp
                                            ),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFB80808)
                                        ), onClick = {}) {
                                        Text(
                                            text = schedule.attributes.status!!,
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                lineHeight = 20.sp,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFFFFFFF),
                                            ),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}