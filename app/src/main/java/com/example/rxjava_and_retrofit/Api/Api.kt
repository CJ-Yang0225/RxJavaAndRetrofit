package com.example.rxjava_and_retrofit.Api

import com.example.rxjava_and_retrofit.Bean.MyUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("users")
    fun getData(
        @Query("id ") name: String,
        @Query("first_name") fName: String,
        @Query("last_name") lName: String
    ): Call<MyUsers>
}