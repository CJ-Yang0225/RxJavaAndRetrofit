package com.example.rxjava_and_retrofit

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjava_and_retrofit.Bean.MyUsers

open class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private lateinit var mData: List<MyUsers>

    fun MyAdapter(list: List<MyUsers>) {
        this.mData = list
        Log.d("MyAdapter", itemCount.toString())
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        val holder = ViewHolder(view)

        return holder
    }

    // 顯示獲得的資料
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData: MyUsers = mData[position]
        holder.user_id.text = userData.id
        holder.user_fName.text = userData.first_name
        holder.user_lName.text = userData.last_name
        Log.d("onBindViewHolder", mData[position].toString())
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user_id: TextView = itemView.findViewById<View>(R.id.text_view_user_id) as TextView
        val user_fName: TextView = itemView.findViewById<View>(R.id.text_view_user_first_name) as TextView
        val user_lName: TextView = itemView.findViewById<View>(R.id.text_view_user_last_name) as TextView
    }

    /**
     * 下拉刷新，清除原有數據，添加新數據
     *
     * @param newData
     */

    fun refreshData(newData: List<MyUsers>) {
        mData = emptyList()
        mData = newData
        notifyItemRangeChanged(0, mData.size)
    }
}