package com.example.sewastudio.view

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sewastudio.controller.UserController
import com.example.sewastudio.model.User
import com.example.sewastudio.R
import com.example.sewastudio.model.Studio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, modifier: Modifier = Modifier) {
    val studioList = remember {
        listOf(
            Studio("deskripsi studio", "Studio 1"),
            Studio("deskripsi studio", "Studio 2"),
            Studio("deskripsi studio", "Studio 3"),
            Studio("deskripsi studio", "Studio 4")
        )
    }
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Login") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.Black,
                ),
            )
        },
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
        ) {
            items(studioList){studio ->
                StudioCard(studio = studio, navController = navController)
            }
        }
    }

    data class Studio(var deskripsi: String, var nomorRuang: String)

    @Composable
    fun StudioCard(studio: Studio, navController: NavController) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navController.navigate("")
                },
            elevation =
        ) {
            Column {
                Image(
                    painter = painterResource(id = studio.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = studio.nomorRuang,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = studio.deskripsi,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

}