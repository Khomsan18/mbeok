package com.example.beok

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    @Expose
    @SerializedName("user_id") val user_id: String,

    @Expose
    @SerializedName("username") val username: String,

    @Expose
    @SerializedName("password") val password: String,

    @Expose
    @SerializedName("email") val email: String,

    @Expose
    @SerializedName("tel") val tel: String,

    @Expose
    @SerializedName("fname") val fname: String,

    @Expose
    @SerializedName("lname") val lname: String,

    )