package com.edwin.cobos.productlistdemo.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("target")
    @Expose
    var target: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("elementType")
    @Expose
    var elementType: String? = null

)