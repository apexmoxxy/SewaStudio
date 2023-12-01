package com.example.sewastudio.view.pemilik

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sewastudio.GoTo
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.controller.AuthController
import com.example.sewastudio.controller.StudioController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateStudioPage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studioname by remember { mutableStateOf(TextFieldValue("")) }
    var userID = sharedPreferences.getString("userID","")
    val prevPage = preferencesManager.getData("previousPage")

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Button(
                    onClick = {
                        GoTo(prevPage, navController, preferencesManager)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp)
                ) {
                    Text(text = "Back")
                }},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
                .padding(30.dp)
                .size(10.dp, 10.dp)

        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .size(10.dp, 10.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Text(text = "", modifier = Modifier
                    .padding(25.dp)
                    ,fontSize = 40.sp
                    , fontFamily = FontFamily.SansSerif
                    , fontStyle = FontStyle.Normal

                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    OutlinedTextField(
                        value = studioname,
                        onValueChange = { newText -> studioname = newText },
                        label = {
                            Text(
                                text = "Studio Name",
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    Button(
                        onClick = {
                            StudioController.insertStudio(studioname.text, userID!!.toInt()){
                                studio ->  if (studio != null) {
                                    GoTo(prevPage, navController, preferencesManager)
                                    println(studio.id)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Add Studio")
                    }
                }
            }
        }
    }
}