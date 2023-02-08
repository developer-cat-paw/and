package com.example.help_me

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class ListItem(val cate: String)


class ListAdapter(val cateList: ArrayList<ListItem>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        var cateitem: TextView = listView.findViewById(R.id.category_android)
        val recycler: RecyclerView = listView.findViewById(R.id.recyclerHorizon)
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListAdapter.ListViewHolder {
        // create a new view
        val cateView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item_hori, parent, false)

        return ListViewHolder(cateView)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.cateitem.text = cateList[position].cate
        if(position%6 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(177,255,246)) //안드로이드
        }
        else if(position%5 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(183,255,177)) //웹
        }
        else if(position%4 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,177,177)) //iOS
        }
        else if(position%3 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(177,180,255)) //Sever
        }
        else if(position%2 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(249,255,177)) //ai
        }
        else{
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(244,177,255)) //Design
        }
        val itemList = ArrayList<MainItem>()
        val mainAdapter = MainRecyclerAdapter(itemList)
        holder.recycler.layoutManager = LinearLayoutManager(MainActivity(), LinearLayoutManager.VERTICAL, false)
        mainAdapter.notifyDataSetChanged()
        holder.cateitem.setOnClickListener {
            Log.d("상태","클릭")
            if(holder.cateitem.text == "And"){ //안드로이드
                if(position%6 == 0){
                    Log.d("상태","안드로이드")
                    holder.recycler.adapter = mainAdapter
                }
            } else if(holder.cateitem.text == "iOS"){ //iOS
                if(position%5 == 0){
                    Log.d("상태","iOS")
                    holder.recycler.adapter = mainAdapter
                }
            } else if(holder.cateitem.text == "Web"){ //Web
                if(position%4 == 0){
                    Log.d("상태","Web")
                    holder.recycler.adapter = mainAdapter
                }
            } else if(holder.cateitem.text == "Sever"){ //서버
                if(position%3 == 0){
                    Log.d("상태","Sever")
                    holder.recycler.adapter = mainAdapter
                }
            } else if(holder.cateitem.text == "Ai"){ //ai
                if(position%2 == 0){
                    Log.d("상태","Ai")
                    holder.recycler.adapter = mainAdapter
                }
            } else{ //디자인
                if(position%1==0){
                    Log.d("상태","디자인")
                    holder.recycler.adapter = mainAdapter
                }
            }
        }
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return cateList.count()
    }
}