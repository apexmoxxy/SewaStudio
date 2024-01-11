package com.example.sewastudio.view.pelanggan

import android.content.Context
import android.widget.CalendarView
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.sewastudio.BottomNavigation
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailStudioPage(
    navController: NavController,
    studio_id : String?,
    name : String?,
    price : Int?,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    if (studio_id == null) {
        navController.navigate("pelangganhomepage")
    }
//    val preferencesManager = remember { PreferencesManager(context = context) }
//    var studios by remember { mutableStateOf<List<Studio>?>(null) }

    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val formattedTime = dateFormat.format(Calendar.getInstance().time)
    var date by remember { mutableStateOf(formattedTime.toString()) }
    val times = listOf("10.00", "12.00", "14.00", "16.00", "18.00", "20.00")
    var start by remember { mutableStateOf(times[0]) }
    var end by remember { mutableStateOf(times[1]) }
    var duration by remember { mutableStateOf(0) }
    val primaryColor = Color(0xFF1F41BB)
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var openingCallender by remember { mutableStateOf(false) }


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
                            onClick = { navController.navigate("pelangganhomepage") }
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
                            text = "Complete Your Photo Studio",
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
        bottomBar = {
            BottomAppBar {
                BottomNavigation(navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${SimpleDateFormat("dd MMMM yyyy").format(dateFormat.parse(date))}",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF1F41BB)),
                        modifier = Modifier.padding(top = 24.dp)
                    )
                    if (openingCallender) {
                        AndroidView(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .zIndex(-1.0f),
                            factory = { CalendarView(it) },
                            update = {
                                it.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                                    date = "$dayOfMonth-${month + 1}-$year"
                                }
                            })
                    } else {

                    }
                    Button(onClick = { openingCallender = !openingCallender }, colors = ButtonDefaults.buttonColors(primaryColor)) {
                        Text(text = if (!openingCallender) "Open Calendar" else "Close Calendar" , color = Color.White)
                    }
                    if (!openingCallender) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(text = "Start Time", style = TextStyle(fontSize = 16.sp, color = primaryColor),
                                    modifier = Modifier.padding(vertical = 16.dp))
                                OutlinedTextField(
                                    modifier = Modifier
                                        .semantics { text = AnnotatedString("StartTime") }
                                        .width(160.dp),
                                    readOnly = true,
                                    value = start,
                                    onValueChange = {},
                                    trailingIcon = {
                                        Icon(
                                            imageVector = if (expanded1) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                            contentDescription = "dropdownArrow",
                                            modifier = Modifier.clickable {
                                                expanded1 = !expanded1
                                            }
                                        )
                                    },
                                )
                                DropdownMenu(
                                    expanded = expanded1,
                                    onDismissRequest = { expanded1 = false }
                                ) {
                                    times.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            modifier = Modifier.semantics{contentDescription = "StartTime $selectionOption"},
                                            onClick = {
                                                start = selectionOption
                                                if (end.toFloat() < selectionOption.toFloat()) {
                                                    start = end
                                                    end = selectionOption
                                                }
                                                expanded1 = false
                                            },
                                        )
                                    }

                                }
                            }

                            Column {
                                Text(text = "Finish Time", style = TextStyle(fontSize = 16.sp, color = primaryColor),
                                    modifier = Modifier.padding(vertical = 16.dp))
                                OutlinedTextField(
                                    modifier = Modifier
                                        .semantics { text = AnnotatedString("FinishTime") }
                                        .width(160.dp),
                                    readOnly = true,
                                    value = end,
                                    onValueChange = {},
                                    trailingIcon = {
                                        Icon(
                                            imageVector = if (expanded2) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                            contentDescription = "dropdownArrow",
                                            modifier = Modifier.clickable {
                                                expanded2 = !expanded2
                                                println(expanded2)
                                            }
                                        )
                                    },                                )
                                DropdownMenu(
                                    expanded = expanded2,
                                    onDismissRequest = { expanded2 = false }
                                ) {
                                    times.forEach { selectionOption2 ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption2) },
                                            modifier = Modifier.semantics{contentDescription = "EndTime $selectionOption2"},
                                            onClick = {
                                                end = selectionOption2
                                                if (start.toFloat() > selectionOption2.toFloat()) {
                                                    end = start
                                                    start = selectionOption2
                                                }
                                                expanded2 = false
                                            },
                                        )
                                    }

                                }
                            }
                        }
                    }

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
                    .size(48.dp),
                    onClick = {
                        duration = (end.toFloat() - start.toFloat()).toInt()
                        if (duration > 0) {
                            navController.navigate("bookingpage/$studio_id/$name/$price/$date::$start-$end")
                        } else {
                            Toast.makeText(context, "Duration can't be 0", Toast.LENGTH_SHORT).show()
                        }
                }, colors = ButtonDefaults.buttonColors(primaryColor)
                ) {
                    Text(text = "Selanjutnya", color = Color.White)
                }
            }
        }
    }
}