package com.example.sewastudio.view.pemilik

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.controller.StudioController
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateStudioPage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studioname by remember { mutableStateOf(TextFieldValue("")) }
    var userID = sharedPreferences.getString("userID","")
    val jwt = preferencesManager.getData("jwt")

    var selectedImageFile by remember { mutableStateOf<File?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val resolver = context.contentResolver
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                resolver.openInputStream(selectedImageUri!!)?.let { inputStream ->
                    val originalFileName = context.contentResolver.query(
                        selectedImageUri!!, null, null, null, null
                    )?.use { cursor ->
                        if (cursor.moveToFirst()) {
                            val displayNameIndex =
                                cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                            if (displayNameIndex != -1) {
                                cursor.getString(displayNameIndex)
                            } else {
                                null
                            }
                        } else {
                            null
                        }
                    }
                    val file = File(context.cacheDir, originalFileName ?: "temp_img.jpg")
                    Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
                    selectedImageFile = file
                }
            }
        })

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Button(
                    onClick = {
                        navController.navigate("pemilikhomepage")
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
                    Row(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 8.dp)
                    ) {
                        val primaryColorOrg = MaterialTheme.colorScheme.primary
                        Box(modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .clickable { pickImageLauncher.launch("image/*") }
                            .border(
                                width = 1.5.dp, color = primaryColorOrg, shape = RoundedCornerShape(8.dp)
                            ), contentAlignment = Alignment.Center) {
                            if (selectedImageUri != null) {
                                Image(
                                    painter = rememberImagePainter(data = selectedImageUri, builder = {
                                        transformations(CircleCropTransformation())
                                    }),
                                    contentDescription = "Selected Image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = RoundedCornerShape(8.dp))
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Add Photo",
                                    tint = primaryColorOrg
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        if (selectedImageUri != null) {
                            IconButton(
                                onClick = { selectedImageUri = null }, modifier = Modifier.size(48.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Image")
                            }
                        }
                    }
                    Button(
                        onClick = {
                            StudioController.insertStudio(jwt, studioname.text,
                                selectedImageFile!!, userID!!.toInt()){
                                studio ->  if (studio != null) {
                                    navController.navigate("pemilikhomepage")
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