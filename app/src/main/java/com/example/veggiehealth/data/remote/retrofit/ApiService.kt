package com.example.veggiehealth.data.remote.retrofit

import com.example.veggiehealth.data.remote.response.DetailVegetableResponse
import com.example.veggiehealth.data.remote.response.ListSayuranResponse
import com.example.veggiehealth.data.remote.response.LoginResponse
import com.example.veggiehealth.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("vegetable")
    suspend fun getVegetable(
        @Query("page") page : Int = 1,
        @Query("size") size : Int = 3
    ): ListSayuranResponse

    @GET("vegetable/{id}")
    suspend fun getDetailVegetables(
        @Path("id") id : String
    ): DetailVegetableResponse
}