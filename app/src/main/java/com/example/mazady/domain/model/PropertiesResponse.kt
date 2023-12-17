package com.example.mazady.domain.model
import com.google.gson.annotations.SerializedName


data class Properties(
    @SerializedName("description")
    var description: String?,
     @SerializedName("id")
    var id: Int?,
     @SerializedName("list")
    var list: Boolean?,
     @SerializedName("name")
    var name: String?,
     @SerializedName("options")
    var options: List<Option?>?,
     @SerializedName("other_value")
    var otherValue: Any?,
     @SerializedName("parent")
    var parent: Any?,
     @SerializedName("slug")
    var slug: String?,
     @SerializedName("type")
    var type: String?,
     @SerializedName("value")
    var value: String?
)

 
data class Option(
     @SerializedName("child")
    var child: Boolean?,
     @SerializedName("id")
    var id: Int?,
     @SerializedName("name")
    var name: String?,
     @SerializedName("parent")
    var parent: Int?,
     @SerializedName("slug")
    var slug: String?
)