package com.example.sewastudio.service

import com.example.sewastudio.model.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class StudioData (val name:String, val username:String, val password: String)
interface StudioService {
    @POST("auth/local")
    fun login(@Body body: LoginData) : Call<Auth>
    @POST("auth/local/register")
    fun register(@Body body: RegisterData) : Call<Auth>
}