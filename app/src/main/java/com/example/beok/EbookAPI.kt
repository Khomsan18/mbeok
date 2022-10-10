package com.example.beok

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EbookAPI {
    @GET("allebook")
    fun retrieveEbook(): Call<List<Ebook>>

    @FormUrlEncoded
    @POST("ebk")
    fun insertEbk(
        @Field("ebook_name") ebook_name: String,
        @Field("publisher") ebook_publisher: String,
        @Field("price") ebook_price: Int,
        @Field("ebook_type") ebook_type: String): Call<Ebook>
}