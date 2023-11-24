package com.example.sewastudio

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sewastudio.controller.AuthController
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sewastudio.ui.theme.SewaStudioTheme
import com.example.sewastudio.view.AuthPage
import com.example.sewastudio.view.HomePage
import com.example.sewastudio.view.SplashPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val preferencesManager = PreferencesManager(context = LocalContext.current)
            val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val navController = rememberNavController()
            var startDestination = "splash"
            var userID = sharedPreferences.getString("userID","")
            var username = sharedPreferences.getString("username", "")
            var password = sharedPreferences.getString("password", "")

            val loginComplete = remember { mutableStateOf(false) }

            if (!loginComplete.value) {
                if (username != null && password != null){
                    AuthController.login(username, password, navController, preferencesManager) { success ->
                        if (success != null) {
                            loginComplete.value = true
                        }
                    }
                }
            }

            SewaStudioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
            NavHost(navController, startDestination = startDestination) {
                composable(route = "splash") {
                    SplashPage(navController)
                }
                composable(route = "auth-page") {
                    AuthPage(navController)
                }
                composable(route = "homepage") {
                    HomePage(navController)
                }
            }
        }
    }
}
