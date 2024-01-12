package com.example.sewastudio.service

import com.google.gson.annotations.SerializedName
import com.midtrans.sdk.uikit.api.model.CustomerDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class TransactionData(
    @SerializedName("item_details")
    val item_details: List<ItemData>,
    @SerializedName("customer_details")
    val customer_details: CustomerDetails,
)

data class SnapData(
    @SerializedName("data")
    val data: TransactionData,
)

data class ItemData(
    @SerializedName("id")
    val id: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("price")
    val price : Int
)
interface SnapService {
    @POST("midtrans/")
    fun getSnapToken(@Body body: SnapData) : Call<SnapToken>
}

class SnapToken{
    @SerializedName("data")
    var data: String? = ""

}

