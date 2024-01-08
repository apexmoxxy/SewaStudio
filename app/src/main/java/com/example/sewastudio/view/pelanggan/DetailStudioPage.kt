package com.example.sewastudio.view.pelanggan

import android.content.Context
import android.widget.CalendarView
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.sewastudio.BottomNavigation
import com.example.sewastudio.GoTo
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.model.Studio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailStudioPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studios by remember { mutableStateOf<List<Studio>?>(null) }
    var date by remember { mutableStateOf("") }
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
                                .width(36.dp).height(36.dp),
                            onClick = { navController.navigateUp() }
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
                        text = "Choose Your Date",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF1F41BB)),
                        modifier = Modifier.padding(top = 24.dp)
                    )
                    AndroidView(
                        modifier = Modifier.padding(top = 8.dp),
                        factory = { CalendarView(it) },
                        update = {
                            it.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                                date = "$dayOfMonth-${month + 1}-$year"
                            }
                        })
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            val options = listOf("10.00", "12.00")
                            var expanded by remember { mutableStateOf(false) }
                            var selectedOptionText by remember { mutableStateOf(options[0]) }

                            Text(text = "Start Time", style = TextStyle(fontSize = 16.sp, color = primaryColor),
                                modifier = Modifier.padding(vertical = 16.dp))
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded },
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .width(160.dp),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = {},
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                            },
                                        )
                                    }
                                }
                            }
                        }

                        Column {
                            val options2 = listOf("12.00", "14.00")
                            var expanded2 by remember { mutableStateOf(false) }
                            var selectedOptionText2 by remember { mutableStateOf(options2[0]) }

                            Text(text = "Finish Time", style = TextStyle(fontSize = 16.sp, color = primaryColor),
                                modifier = Modifier.padding(vertical = 16.dp))
                            ExposedDropdownMenuBox(
                                expanded = expanded2,
                                onExpandedChange = { expanded2 = !expanded2 },
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .width(160.dp),
                                    readOnly = true,
                                    value = selectedOptionText2,
                                    onValueChange = {},
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded2,
                                    onDismissRequest = { expanded2 = false },
                                ) {
                                    options2.forEach { selectionOption2 ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption2) },
                                            onClick = {
                                                selectedOptionText2 = selectionOption2
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
                              GoTo("bookingpage", navController, preferencesManager)
                }, colors = ButtonDefaults.buttonColors(primaryColor)
                ) {
                    Text(text = "Selanjutnya", color = Color.White)
                }
            }
        }
    }
}