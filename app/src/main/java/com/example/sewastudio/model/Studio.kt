package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName


class Studio {
    @SerializedName("id")
    var id: Int = 0
}

data class StudioResponse<T>(
    @SerializedName("data")
    val data: T?
)
