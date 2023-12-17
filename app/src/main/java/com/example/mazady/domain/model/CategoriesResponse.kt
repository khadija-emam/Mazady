package com.example.mazady.domain.model
import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: T?,
    @SerializedName("msg")
    var msg: String?
)

 
data class CategoryResponse(
    @SerializedName("ads_banners")
    var adsBanners: List<AdsBanner?>?,
    @SerializedName("categories")
    var categories: List<Category> = emptyList(),
    @SerializedName("google_version")
    var googleVersion: String?,
    @SerializedName("huawei_version")
    var huaweiVersion: String?,
    @SerializedName("ios_latest_version")
    var iosLatestVersion: String?,
    @SerializedName("ios_version")
    var iosVersion: String?,
    @SerializedName("statistics")
    var statistics: Statistics?
)

 
data class AdsBanner(
    @SerializedName("duration")
    var duration: Int?,
    @SerializedName("img")
    var img: String?,
    @SerializedName("media_type")
    var mediaType: String?
)

 
data class Category(
    @SerializedName("children")
    var children: List<Children>?,
    @SerializedName("circle_icon")
    var circleIcon: String?,
    @SerializedName("description")
    var description: Any?,
    @SerializedName("disable_shipping")
    var disableShipping: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("slug")
    var slug: String?
)

 
data class Statistics(
    @SerializedName("auctions")
    var auctions: Int?,
    @SerializedName("products")
    var products: Int?,
    @SerializedName("users")
    var users: Int?
)

 
data class Children(
    @SerializedName("children")
    var children: Any?,
    @SerializedName("circle_icon")
    var circleIcon: String?,
    @SerializedName("description")
    var description: Any?,
    @SerializedName("disable_shipping")
    var disableShipping: Int?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("slug")
    var slug: String?
)