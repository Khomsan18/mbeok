package com.example.beok

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ebook(
    @Expose
    @SerializedName("ebook_name") val ebook_name: String,

    @Expose
    @SerializedName("publisher") val ebook_publisher: String,

    @Expose
    @SerializedName("price")val ebook_price: Int,

    @Expose
    @SerializedName("ebook_type") val ebook_type: String){}