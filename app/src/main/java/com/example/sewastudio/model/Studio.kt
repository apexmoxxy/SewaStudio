package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName


class Studio {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: StudioAttributes = StudioAttributes()
}

class StudioAttributes{
    @SerializedName("name")
    var name: String = ""
    @SerializedName("available")
    var available: Boolean? = false
    @SerializedName("studioImg")
    var studioImg: StudioImgData = StudioImgData()
    @SerializedName("users_permissions_user")
    var user: User = User()
    @SerializedName("price")
    var price: Int = 25000
}

class StudioImgData{
    @SerializedName("data")
    var data: StudioImg? = StudioImg()
}
class StudioImg{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes : StudioImgAttributes = StudioImgAttributes()
}

class StudioImgAttributes{
    @SerializedName("name")
    var name: String = ""
    @SerializedName("url")
    var url: String = ""
}
class StudioSchedule {
    @SerializedName("id")
    var id : Int = 0
    @SerializedName("attributes")
    var attributes: StudioScheduleAttributes = StudioScheduleAttributes()
}

class StudioScheduleAttributes {
    @SerializedName("price")
    val price: Int? = 0
    @SerializedName("bookdate")
    val bookdate: String? = null
    @SerializedName("start_time")
    val start_time: String? = null
    @SerializedName("end_time")
    val end_time: String? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("users_permissions_user")
    val user: User? = null
    @SerializedName("studio")
    val studio: Studio? = null
}
