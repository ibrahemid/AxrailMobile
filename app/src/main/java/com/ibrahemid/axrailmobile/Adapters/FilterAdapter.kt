package com.ibrahemid.axrailmobile.Adapters

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.FilterBtn
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.filter_item.view.*

class FilterAdapter(val fillterOptions: ArrayList<FilterBtn>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = fillterOptions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        print("item is bind at $position")
        holder.itemView.filterItem.text = fillterOptions.get(position).title
        holder.itemView.filterItem.setOnClickListener {
            if (!fillterOptions[position].status) {
                //activated
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    holder.itemView.filterItem.outlineSpotShadowColor = Color.BLUE
//                    holder.itemView.filterItem.outlineSpotShadowColor = Color.RED
//                }
                holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape)
                holder.itemView.filterItem.setTextColor(Color.WHITE)
                fillterOptions[position].status = true // live data


            } else {
                holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape_clear)
                holder.itemView.filterItem.setTextColor(Color.parseColor("#CED3E5"))
                fillterOptions[position].status = false

            }
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


