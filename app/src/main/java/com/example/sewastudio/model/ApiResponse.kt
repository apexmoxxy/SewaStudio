package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T> {
    @SerializedName("data")
    val data: T? = null
}