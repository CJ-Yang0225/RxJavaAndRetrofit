package com.example.rxjava_and_retrofit

import android.content.Context
import android.os.Handler
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjj.MaterialRefreshLayout
import com.cjj.MaterialRefreshListener

import com.example.rxjava_and_retrofit.Bean.MyUsers

import java.util.ArrayList


import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.internal.http.HttpMethod

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var users: List<MyUsers>
//    private var adapter: MyAdapter? = null
    private var adapter = MyAdapter()
    private var materialRefreshLayout: MaterialRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_View)
        materialRefreshLayout = findViewById<MaterialRefreshLayout>(R.id.refresh)
        getData(this)

        materialRefreshLayout!!.setMaterialRefreshListener(object : MaterialRefreshListener() {
            override fun onRefresh(materialRefreshLayout: MaterialRefreshLayout) {
                Handler().postDelayed({
                    getData(this@MainActivity)
                    adapter.refreshData(users)
                    materialRefreshLayout.finishRefresh()
                }, 2000)
            }
        })
    }

    fun getData(context: Context) {
        //
        val httpMethods = HttpMethods()

        httpMethods.getUser(object: Observer<List<MyUsers>> {
            lateinit var d: Disposable

            override fun onSubscribe(d: Disposable) {
                this.d = d
                Log.d("onSubscribe","OK!")
            }

            // 以下未調用
            override fun onNext(myUsers: List<MyUsers>) {
                users = myUsers
                adapter.MyAdapter(myUsers)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                Log.d("onNext", "獲取數據完成，數據size - ${myUsers.size}")
            }

            override fun onError(e: Throwable) {
                Log.d("onError", "error$e")
                d.dispose()
            }

            override fun onComplete() {
                Log.d("onComplete", "onComplete")
                d.dispose()
            }
        })
    }
}
