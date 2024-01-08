package com.example.sewastudio.view.pelanggan

//import com.example.sewastudio.MyWebView
import android.content.Context
import android.content.Intent
import android.net.Uri
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColor = Color(0xFF1F41BB)
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://app.sandbox.midtrans.com/snap/v3/redirection/a911ac47-6d01-417b-946e-95845e00cf53#/409")) }

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
                            text = "3 hours",
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
                        Text(
                            text = "11 Januari 2024",
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
                            text = "09.00 a.m - 12.00 p.m",
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
                        Text(
                            text = "Rp. 388.000",
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
                        Text(
                            text = "Rp. 388.000",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
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
                    Intent(context, MidtransActivity::class.java).also {
                        ContextCompat.startActivity(context, it, null)
                    }
                    //GoTo("midtranspage", navController, preferencesManager)
//                          GoTo("pembayaran", navController, preferencesManager)
                }, colors = ButtonDefaults.buttonColors(primaryColor)
            ) {
                Text(text = "Selanjutnya", color = Color.White)
            }
        }
    }
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ) {
//            Box(
//                modifier = Modifier
//                    .shadow(
//                        elevation = 4.dp,
//                        spotColor = Color(0x40000000),
//                        ambientColor = Color(0x40000000)
//                    )
//                    .width(330.dp)
//                    .height(228.dp)
//                    .background(color = Color(0xFFFFFFFF))
//            ) {
//                Text(
//                    text = "Booking Details\n4 Hour\n4 Desember 2023\n09.00 a.m - 13.00 p.m\n\nDetail Pembayaran\n\nSubtotal 388K\nTax -\n---------------------\nTotal 388K",
//                    style = TextStyle(
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight(400),
//                        color = Color(0xFF000000),
//                    )
//                )
//            }
//
//            Box(
//                modifier = Modifier
//                    .shadow(
//                        elevation = 4.dp,
//                        spotColor = Color(0x40000000),
//                        ambientColor = Color(0x40000000)
//                    )
//                    .width(328.dp)
//                    .height(273.dp)
//                    .background(color = Color(0xFFFFFFFF))
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    Text(
//                        text = "Your Text Above Columns",
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        ),
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Column(
//                            modifier = Modifier.weight(1f)
//                        ) {
//                            Text(
//                                text = "Nama",
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF000000),
//                                ),
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//                        }
//
//                        Column(
//                            modifier = Modifier.weight(1f)
//                        ) {
//                            Text(
//                                text = "Email",
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF000000),
//                                ),
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//                        }
//
//                        Column(
//                            modifier = Modifier.weight(1f)
//                        ) {
//                            Text(
//                                text = "No. WhatsApp",
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF000000),
//                                ),
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//                        }
//                    }
//                }
//            }
//        }
}
