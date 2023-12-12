package com.example.sewastudio.service

import androidx.datastore.preferences.protobuf.Api
import com.example.sewastudio.model.Auth
import com.example.sewastudio.model.Studio
import com.example.sewastudio.model.ApiResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
data class StudioData(
    @SerializedName("data")
    val data: StudioBody
)

data class StudioBody(
    val name: String,
    @SerializedName("ownerID")
    val ownerId: Int
)

interface StudioService {
    @POST("studios")
    fun insert(@Body body: StudioData): Call<Studio>

    @GET("studios")
    fun getall() : Call<ApiResponse<List<Studio>>>
}