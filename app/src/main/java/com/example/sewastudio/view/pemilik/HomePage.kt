package com.example.sewastudio.view.pemilik

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sewastudio.GoTo
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.controller.AuthController
import com.example.sewastudio.controller.StudioController
import com.example.sewastudio.controller.UserController
import com.example.sewastudio.model.Studio
import com.example.sewastudio.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PemilikHomePage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studios by remember { mutableStateOf<List<Studio>?>(null) }
    //    println(studioList)
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Sewa Studio", modifier = Modifier, color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                GoTo("createstudiopage", navController, preferencesManager)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LazyColumn{
                StudioController.getStudios { response ->
                    studios = response?.data
                }

                studios?.forEach { studio ->
                    item {
                        Row (modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = studio.attributes.name)
                            ElevatedButton(onClick = {
                            }) {
                                Text("Edit")
                            }
                            ElevatedButton(onClick = {

                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {
                    AuthController.logout(navController, preferencesManager)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            ) {
                Text(text = "Logout")
            }
        }
    }
}