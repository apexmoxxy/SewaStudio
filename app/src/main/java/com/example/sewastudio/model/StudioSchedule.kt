package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName
class StudioSchedule {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: ScheduleAttributes = ScheduleAttributes()
}

class ScheduleAttributes {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("studioID")
    var studioID: ApiResponse<Studio>? = ApiResponse<Studio>()
    @SerializedName("studiodate")
    var studioDate: String = ""
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
    @SerializedName("publishedAt")
    var publishedAt: String = ""
    @SerializedName("available")
    var available: Any? = null
}
