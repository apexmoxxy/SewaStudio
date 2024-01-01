package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName

class Order {
    @SerializedName("id")
    var id : Int = 0
    @SerializedName("attributes")
    var attributes: OrderAttributes = OrderAttributes()
}

class OrderAttributes {
    @SerializedName("userID")
    var userID : ApiResponse<User> = ApiResponse<User>()
    @SerializedName("studioScheduleID")
    var studioScheduleID : ApiResponse<List<StudioSchedule>> = ApiResponse<List<StudioSchedule>>()
}