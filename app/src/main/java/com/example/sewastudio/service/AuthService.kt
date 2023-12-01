package com.example.sewastudio.service

import com.example.sewastudio.model.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

enum class UserRole {
    Pelanggan,
    Pemilik,
    Karyawan
}
data class LoginData (val identifier:String, val password: String)
data class RegisterData (val email:String, val username:String, val password: String, val status: UserRole)

interface AuthService {
    @POST("auth/local")
    fun login(@Body body: LoginData) : Call<Auth>
    @POST("auth/local/register")
    fun register(@Body body: RegisterData) : Call<Auth>
}