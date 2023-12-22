package com.example.sewastudio.view.pelanggan

import android.content.Context
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.controller.AuthController
import com.example.sewastudio.controller.UserController
import com.example.sewastudio.model.User
import com.example.sewastudio.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingPage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "FortuneSpace",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF)
                    )
                ) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ){ innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .width(330.dp)
                    .height(228.dp)
                    .background(color = Color(0xFFFFFFFF))
            ) {
                Text(
                    text = "Booking Details\n4 Hour\n4 Desember 2023\n09.00 a.m - 13.00 p.m\n\nDetail Pembayaran\n\nSubtotal 388K\nTax -\n---------------------\nTotal 388K",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }

            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .width(328.dp)
                    .height(273.dp)
                    .background(color = Color(0xFFFFFFFF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Text above the columns
                    Text(
                        text = "Your Text Above Columns",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Three columns with content
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Column 1
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Nama",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            // Add content for Column 1 if needed
                        }

                        // Column 2
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Email",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            // Add content for Column 2 if needed
                        }

                        // Column 3
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "No. WhatsApp",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            // Add content for Column 3 if needed
                        }
                    }
                }
            }
        }
    }