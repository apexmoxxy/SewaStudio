package com.example.sewastudio

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
fun GoTo(destination: String, navController: NavController, preferencesManager: PreferencesManager){
    val currentPage = preferencesManager.getData("currentPage")
    navController.navigate(destination)
    preferencesManager.saveData("previousPage", currentPage)
    preferencesManager.saveData("currentPage", destination)
}