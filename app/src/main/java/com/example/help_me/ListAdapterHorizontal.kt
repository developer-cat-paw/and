package com.example.help_me

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors.getColor

data class ListItem(val cate: String)

class ListAdapter(val cateList: ArrayList<ListItem>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        var cateitem: TextView = listView.findViewById(R.id.category_android)
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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cateitem.text = cateList[position].cate
        if(position%6 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(177,255,246))
        }
        else if(position%5 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(183,255,177))
        }
        else if(position%4 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,177,177))
        }
        else if(position%3 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(177,180,255))
        }
        else if(position%2 == 0){
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(249,255,177))
        }
        else{
            holder.cateitem.backgroundTintList = ColorStateList.valueOf(Color.rgb(244,177,255))
        }
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return cateList.count()
    }
}