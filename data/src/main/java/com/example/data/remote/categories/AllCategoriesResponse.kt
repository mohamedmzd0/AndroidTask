package com.example.data.remote.categories


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllCategoriesResponse(
    val categories: ArrayList<Category>? = null
)

@Parcelize
data class Category(

    @SerializedName("parent") var parent: Int? = null,
    @SerializedName("child") var child: Boolean? = null,

    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val slug: String? = null,
    val children: ArrayList<Category>? = null,
    @SerializedName("circle_icon")
    val circleIcon: String? = null,
    @SerializedName("disable_shipping")
    val disableShipping: Long? = null,

) : Parcelable