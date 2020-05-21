package com.example.rxjava_and_retrofit

import android.util.Log
import com.example.rxjava_and_retrofit.Bean.MyUsers
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// 單例模式
open class HttpMethods {
    private val retrofit: Retrofit
    private val api: ApiService
    private val baseUrl = "http://demo7261611.mockable.io/"

    init {
        val httpclient = OkHttpClient.Builder()
        httpclient.connectTimeout(10, TimeUnit.SECONDS)
        retrofit = Retrofit.Builder().client(httpclient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        api = retrofit.create(ApiService::class.java)
    }

    fun getUser(subscriber: Observer<List<MyUsers>>) {
        api.getData()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .safeSubscribe(subscriber)
    }
}

//open class HttpMethods private constructor() {
//    private val retrofit: Retrofit
//    private val apiService: ApiService
//
//    init {
//        /**
//         * 構造函數私有化
//         * 並在構造函數中進行retrofit的初始化
//         */
//        val client = OkHttpClient()
//        client.newBuilder().connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
//
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//        apiService = retrofit.create(ApiService::class.java)
//    }
//
//
////    private object sigleInstance {
////        val instance = HttpMethods()
////    }
//
//    open fun getUser(observer: Observer<List<MyUsers>>) {
//        apiService.getData()
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer)
//    }
//
//    companion object {
//
//        private val BASE_URL = "http://demo7261611.mockable.io/"
//        private val TIME_OUT = 4
//
//        private var INSTANCE: HttpMethods? = null
//        fun getInstance(): HttpMethods{
//            if (null == INSTANCE){
//                INSTANCE = HttpMethods()
//            }
//            return INSTANCE as HttpMethods
//        }

//        val instance: HttpMethods
//            get() = sigleInstance.instance
//    }
//}


//open class HttpMethods {
//    private val BASE_URL = "http://demo7261611.mockable.io/"
//    private val TIME_OUT = 4
//    private var retrofit: Retrofit? = null
//    private var apiService: ApiService? = null
//
//    fun HttpMethods() {
//        /**
//         * 構造函數私有化
//         * 並在構造函數中進行retrofit的初始化
//         */
//        val client = OkHttpClient()
//        client.newBuilder().connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
//
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//        apiService = retrofit!!.create<ApiService>(ApiService::class.java!!)
//    }
//
//   open fun getInstance(): HttpMethods {
//        return instance
//   }
//
//    open fun getUser(observer: Observer<List<MyUsers>>) {
//        apiService!!.getData()
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer)
//
//        Log.d("getUser", apiService.toString())
//    }
//}
//
//private object sinalInstance {
//    final val instance = HttpMethods()
//}
//
//fun getInstance() : HttpMethods {
//    return sinalInstance.instance
//}