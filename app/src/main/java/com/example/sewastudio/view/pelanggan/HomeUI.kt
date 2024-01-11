package com.example.sewastudio.view.pelanggan

import android.content.Context
import android.widget.CalendarView
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sewastudio.BottomNavigation
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.R
import com.example.sewastudio.controller.StudioController
import com.example.sewastudio.model.Studio
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studios by remember { mutableStateOf<List<Studio>?>(null) }
    val jwt = preferencesManager.getData("jwt")

    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val formattedTime = dateFormat.format(Calendar.getInstance().time)
    var date by remember { mutableStateOf(formattedTime.toString()) }
    val times = listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00")
    var start by remember { mutableStateOf(times[0]) }
    var end by remember { mutableStateOf(times[1]) }
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember {
        mutableStateOf(false)
    }
    var openingCallender by remember { mutableStateOf(false) }
    StudioController.getStudios(
        jwt = jwt,
        filters = mapOf(
            "filters[studio_schedules][bookdate][$"+"neq]" to date.split("-").reversed().joinToString("-"),
            "filters[$"+"or][0][studio_schedules][start_time][$"+"lt]" to "$start:00.000",
            "filters[$"+"or][1][studio_schedules][end_time][$"+"gt]" to "$end:00.000",
            "filters[available]" to "true"
        ),
        userID = null
    ) {
        response ->
        studios = response!!.data
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
                }
                val primaryColor = Color(0xFF1F41BB)
                Row (modifier){
                    Text(text = "$date",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .clickable{
                                openingCallender = !openingCallender
                            })
                    Text(text = " : ", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ))
                    Column {
                        Text(text = "$start",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier
                                .clickable{
                                    expanded = !expanded
                                })
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
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
                                        expanded = false
                                    },
                                )
                            }
                        }
                    }
                    Text(text = " - ", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ))
                    Column {
                        Text(text = "$end",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier
                                .clickable{
                                    expanded2 = !expanded2
                                })
                        DropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false }
                        ) {
                            times.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    modifier = Modifier.semantics{contentDescription = "StartTime $selectionOption"},
                                    onClick = {
                                        end = selectionOption
                                        if (start.toFloat() > selectionOption.toFloat()) {
                                            end = start
                                            start = selectionOption
                                        }
                                        expanded2 = false
                                    },
                                )
                            }
                        }
                    }
                }
                LazyColumn {
                    studios?.forEach { studio ->
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
                                    text = studio.attributes.name,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                                )
                                val imgUrl = if (studio.attributes.studioImg.data != null) {
                                   "https://strapi.romiteam.my.id" + studio.attributes.studioImg.data!!.attributes.url
                                }else{
                                    R.drawable.ruangstudiosatu
                                }
                                Image(
                                    painter = rememberAsyncImagePainter(model = imgUrl),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(168.dp)
                                        .padding(18.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text(
                                    text = "Harga per jam: Rp. ${studio.attributes.price}",
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
                                        containerColor = if (studio.attributes.available == true) Color(0xFF1F41BB) else Color(0xFFC4C4C4),
                                    ), onClick = {
                                        navController.navigate("detailstudiopage/${studio.id}/${studio.attributes.name}/${studio.attributes.price}")
                                    }, enabled = studio.attributes.available == true) {
                                    Text(
                                        text = if (studio.attributes.available == true) "Book Now" else "Closed",
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