package com.edwin.cobos.productlistdemo.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("backgroundImage")
    @Expose
    val backgroundImage: String? = null,

    @SerializedName("content")
    @Expose
    val content: List<Content>? = null,

    @SerializedName("promoMessage")
    @Expose
    val promoMessage: String? = null,

    @SerializedName("topDescription")
    @Expose
    val topDescription: String? = null,

    @SerializedName("bottomDescription")
    @Expose
    val bottomDescription: String? = null
)