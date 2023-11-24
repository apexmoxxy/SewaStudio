package com.example.sewastudio.controller

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.model.Auth
import com.example.sewastudio.model.User
import com.example.sewastudio.service.AuthService
import com.example.sewastudio.service.LoginData
import com.example.sewastudio.service.RegisterData
import com.example.sewastudio.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthController {
    companion object {
        private var authService : AuthService = ClientController.getService(AuthService::class.java)

        fun login(username : String, password: String, navController: NavController, prefMan: PreferencesManager,  callback: (Auth?) -> Unit) {
            authService.login(LoginData(username, password)).enqueue(object : Callback<Auth> {
                override fun onResponse(call: Call<Auth>, response: Response<Auth>): Unit =
                    if (response.isSuccessful) {
                        val respBody = response.body()!!
                        val jwt = respBody.jwt
                        val userID = respBody.user?.id.toString()
                        prefMan.saveData("jwt", jwt)
                        prefMan.saveData("userID", userID)
                        prefMan.saveData("username", username)
                        prefMan.saveData("password", password)
                        navController.navigate("homepage")
                    } else {
                        println("Unsuccesful login")
                        callback(null)
                    }

                override fun onFailure(call: Call<Auth>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun register(email : String, username : String, password: String, navController: NavController, prefMan: PreferencesManager,  callback: (Auth?) -> Unit) {
            authService.register(RegisterData(email, username, password)).enqueue(object : Callback<Auth> {
                override fun onResponse(call: Call<Auth>, response: Response<Auth>): Unit =
                    if (response.isSuccessful) {
                        val respBody = response.body()!!
                        val jwt = respBody.jwt
                        val userID = respBody.user?.id.toString()
                        prefMan.saveData("jwt", jwt)
                        prefMan.saveData("userID", userID)
                        prefMan.saveData("username", username)
                        prefMan.saveData("password", password)
                        navController.navigate("homepage")
                    } else {
                        println("Unsuccesful register")
                        callback(null)
                    }

                override fun onFailure(call: Call<Auth>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun logout (navController: NavController, prefMan: PreferencesManager) {
            prefMan.saveData("jwt","")
            prefMan.saveData("userID","")
            prefMan.saveData("username","")
            prefMan.saveData("password","")
            navController.navigate("auth-page")
        }
    }
}