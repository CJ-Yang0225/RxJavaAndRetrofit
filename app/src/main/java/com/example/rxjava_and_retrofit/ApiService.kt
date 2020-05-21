package com.example.rxjava_and_retrofit

import com.example.rxjava_and_retrofit.Bean.MyUsers
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getData(): Observable<List<MyUsers>>
}