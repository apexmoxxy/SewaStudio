package com.example.sewastudio.view.pelanggan

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanPembayaran(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColor = Color(0xFF1F41BB)
    val number = remember { mutableStateOf("") }

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
                            text = "Payment Page",
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
                    Text(
                        text = "Bayar sebelum 11 Januari 2024", style = TextStyle(
                            fontSize = 18.sp,
                            color = primaryColor,
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(top = 48.dp)
                    )
                    Text(
                        text = "Rp. 388.000",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.gopay),
                        contentDescription = "gopay",
                        modifier = Modifier.size(240.dp)
                    )
                    Text(
                        text = "Masukkan nomor gopay kamu", style = TextStyle(
                            fontSize = 14.sp,
                            color = primaryColor,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 14.dp)
                    )
                    OutlinedTextField(
                        value = number.value,
                        onValueChange = {
                            number.value = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                            .padding(2.dp)
                            .border(
                                width = 1.5.dp,
                                color = primaryColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
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

                }, colors = ButtonDefaults.buttonColors(primaryColor)
            ) {
                Text(text = "Bayar", color = Color.White)
            }
        }
    }
}
