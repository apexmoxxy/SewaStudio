package com.example.sewastudio.service

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.StudioSchedule
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class StudioScheduleData(
    @SerializedName("data")
    val data: StudioScheduleBody
)

data class UpdateStudioScheduleData(
    @SerializedName("data")
    val data: UpdateStudioScheduleBody
)

data class StudioScheduleBody(
    val name: String,
    @SerializedName("userid")
    val ownerId: Int
)

data class UpdateStudioScheduleBody(
    val name: String,
)

interface StudioScheduleService {
    @POST("studio-schedules")
    fun insert(@Body body: StudioScheduleData): Call<ApiResponse<StudioSchedule>>
    @GET("studio-schedules")
    fun getall(@Query("filters[users_permissions_user]") user: String?, @Query("populate") populate: String?) : Call<ApiResponse<List<StudioSchedule>>>
    @GET("studio-schedules")
    fun getall(@Query("populate") populate: String?) : Call<ApiResponse<List<StudioSchedule>>>

    @PUT("studio-schedules/{id}")
    fun edit(@Path("id") id: Int, @Body body: UpdateStudioScheduleData) : Call<ApiResponse<StudioSchedule>>
    @DELETE("studio-schedules/{id}")
    fun delete(@Path("id") id: Int) : Call<ApiResponse<StudioSchedule>>
}

