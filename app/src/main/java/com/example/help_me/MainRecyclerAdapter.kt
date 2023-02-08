package com.example.help_me

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URL

data class MainItem(val image: Uri?, val title: String, val detail: String, val item: String)

class MainRecyclerAdapter(val itemList: ArrayList<MainItem>) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemimage: ImageView = itemView.findViewById(R.id.item_image)
        var itemtitle: TextView = itemView.findViewById(R.id.item_title)
        var itemdetail: TextView = itemView.findViewById(R.id.item_detail)
        var item: Button = itemView.findViewById<Button>(R.id.button)
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MainRecyclerAdapter.MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_card_layout, parent, false)

        return MyViewHolder(cardView)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemimage.setImageURI(itemList[position].image)
        holder.itemtitle.text = itemList[position].title
        holder.itemdetail.text = itemList[position].detail
        holder.item.text = itemList[position].item
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return itemList.count()
    }
}