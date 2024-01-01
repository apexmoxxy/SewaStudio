package com.example.sewastudio.view.pelanggan

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sewastudio.BottomNavigation
import com.example.sewastudio.BottomNavigationPemilik
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.R
import com.example.sewastudio.controller.AuthController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

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
                .background(Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 20.dp)
            ) {
                Text(
                    text = "Pengaturan",
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF262626),
                        textAlign = TextAlign.Center,
                    )
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Logout",
                        style = TextStyle(
                            fontSize = 26.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF262626),
                            textAlign = TextAlign.Center,
                        ),
                    )
                    IconButton(onClick = { AuthController.logout(navController, preferencesManager) }) {
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Logout", modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting2(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

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
                BottomNavigationPemilik(navController)
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 20.dp)
            ) {
                Text(
                    text = "Pengaturan",
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF262626),
                        textAlign = TextAlign.Center,
                    )
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Logout",
                        style = TextStyle(
                            fontSize = 26.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF262626),
                            textAlign = TextAlign.Center,
                        ),
                    )
                    IconButton(onClick = { AuthController.logout(navController, preferencesManager) }) {
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Logout", modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }

}