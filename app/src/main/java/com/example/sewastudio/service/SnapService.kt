package com.example.sewastudio.service

import com.google.gson.annotations.SerializedName
import com.midtrans.sdk.uikit.api.model.CustomerDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class TransactionData(
    @SerializedName("item_details")
    val item_details: List<StudioScheduleData>,
    @SerializedName("customer_details")
    val customer_details: CustomerDetails,
)

data class SnapData(
    @SerializedName("data")
    val data: TransactionData,
)

interface SnapService {
    @POST("midtrans/")
    fun getSnapToken(@Body body: SnapData) : Call<SnapToken>
}

class SnapToken{
    @SerializedName("data")
    var data: String? = ""
}

