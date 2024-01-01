package com.example.sewastudio.view.pelanggan

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.model.Studio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailStudioPage(navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current){
    val preferencesManager = remember { PreferencesManager(context = context) }
    var studios by remember { mutableStateOf<List<Studio>?>(null) }
}