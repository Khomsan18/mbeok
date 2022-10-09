package com.example.beok

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserAPI {
    @GET("alluser")
    fun retrieveUser(): Call<List<User>>

    @GET("user/{user_id}")
    fun retrieveUserID(
        @Path("user_id") user_id: String
    ): Call<User>

    @FormUrlEncoded
    @PUT("user/{user_id}")
    fun updateUser(
        @Path("user_id") user_id: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("tel") tel: String,
        @Field("fname") fname: String,
        @Field("lname") lname: String
    ): Call<User>

    @DELETE("user/{user_id}")
    fun deleteUser(
        @Path("user_id") user_id: String
    ): Call<User>

    companion object {
        fun create(): UserAPI {
            val UserClient: UserAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI::class.java)
            return UserClient
        }
    }
}