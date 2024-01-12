package com.example.sewastudio

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sewastudio.controller.AuthController
import com.example.sewastudio.ui.theme.SewaStudioTheme
import com.example.sewastudio.view.AuthUI
import com.example.sewastudio.view.SplashPage
import com.example.sewastudio.view.pelanggan.BookingPage
import com.example.sewastudio.view.pelanggan.DetailStudioPage
import com.example.sewastudio.view.pelanggan.HalamanPembayaran
import com.example.sewastudio.view.pelanggan.HomeUI
import com.example.sewastudio.view.pelanggan.PelangganOrderListPage
import com.example.sewastudio.view.pelanggan.Setting
import com.example.sewastudio.view.pelanggan.Setting2
import com.example.sewastudio.view.pemilik.CreateStudioPage
import com.example.sewastudio.view.pemilik.PemilikHomeUI
import com.example.sewastudio.view.pemilik.PemilikOrderListPage

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
//            cek login secara otomatis apabila terdapat username dan password dari session sebelumnya.

            if (!loginComplete.value) {
                if (username != null && password != null){
                    AuthController.login(username, password, navController, preferencesManager) { success ->
                        if (success != null) {
                            loginComplete.value = true
                        }
                    }
                } else {
                    navController.navigate("auth-page")
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
                    AuthUI(navController)
                }
                composable(route = "pelangganhomepage") {
                    HomeUI(navController)
                }
                composable(route = "bookingpage/{studio_id}/{name}/{price}/{date}") {
                    val studio_id = it.arguments?.getString("studio_id")!!
                    val name = it.arguments?.getString("name")!!
                    val price = it.arguments?.getString("price")!!
                    val date = it.arguments?.getString("date")!!
                    val time = date.split("::")
                    if (studio_id != null && name != null && price != null && date != null){
                        if (price.toInt() != 0) {
                            BookingPage(navController, studio_id, name, price.toInt(), time[0], time[1])
                        }
                    }
//                    BookingPage(navController, it.arguments?.getString("studio_id")!!)
                }
                composable(route = "pelangganorderlist") {
                    PelangganOrderListPage(navController)
                }
                composable(route = "pemilikorderlist") {
                    PemilikOrderListPage(navController)
                }
                composable(route = "pemilikhomepage") {
                    PemilikHomeUI(navController)
                }
                composable(route = "createstudiopage") {
                    CreateStudioPage(navController)
                }
                composable(route = "createorderpage") {
                    CreateStudioPage(navController)
                }
                composable(route = "setting") {
                    Setting(navController)
                }
                composable(route = "setting2") {
                    Setting2(navController)
                }
                composable(route = "detailstudiopage/{studio_id}/{name}/{price}") {
                    val studio_id = it.arguments?.getString("studio_id")!!
                    val name = it.arguments?.getString("name")!!
                    val price = it.arguments?.getString("price")!!
                    if (studio_id != null && name != null && price != null){
                        if (price.toInt() != 0) {
                            DetailStudioPage(navController, studio_id, name, price.toInt())
                        }
                    }
                }
                composable(route = "pembayaran") {
                    HalamanPembayaran(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Beranda", iconResId = Icons.Default.Menu, destination = "pelangganhomepage"
            ),
            BottomNavItem(
                label = "Orderan Kamu", iconResId = Icons.Default.DateRange, destination = "pelangganorderlist"
            ),
            BottomNavItem(
                label = "Setting", iconResId = Icons.Default.Settings, destination = "setting"
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
                },
                icon = {
                    Icon(it.iconResId, contentDescription = it.label, modifier = Modifier.width(20.dp), tint = Color(0xFF1F41BB))
                },
                label = { Text(text = it.label, color = Color(0xFF1F41BB)) },
            )
        }
    }
}
@Composable
fun BottomNavigationPemilik(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Studio", iconResId = Icons.Default.Home, destination = "pemilikhomepage"
            ),
            BottomNavItem(
                label = "Orders", iconResId = Icons.Default.Face, destination = "pemilikorderlist"
            ),
            BottomNavItem(
                label = "Setting", iconResId = Icons.Default.Settings, destination = "setting2"
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
                },
                icon = {
                    Icon(it.iconResId, contentDescription = it.label, modifier = Modifier.width(20.dp), tint = Color(0xFF1F41BB))
                },
                label = { Text(text = it.label, color = Color(0xFF1F41BB)) },
            )
        }
    }
}

data class BottomNavItem(val label: String, val iconResId: ImageVector, val destination: String)
