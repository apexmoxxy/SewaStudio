package com.example.sewastudio.service

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.Order
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class OrderData(
    @SerializedName("data")
    val data: OrderBody
)

data class UpdateOrderData(
    @SerializedName("data")
    val data: UpdateOrderBody
)

data class OrderBody(
    val name: String,
    @SerializedName("ownerID")
    val ownerId: Int
)

data class UpdateOrderBody(
    val name: String,
)

interface OrderService {
    @POST("orders")
    fun insert(@Body body: OrderData): Call<Order>
    @GET("orders")
    fun getall(@Query("populate") populate: String?) : Call<ApiResponse<List<Order>>>
    @PUT("orders/{id}")
    fun edit(@Path("id") id: Int, @Body body: UpdateOrderData) : Call<ApiResponse<Order>>
    @DELETE("orders/{id}")
    fun delete(@Path("id") id: Int) : Call<ApiResponse<Order>>
}

