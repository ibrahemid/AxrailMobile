package com.ibrahemid.axrailmobile.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.HomeActivity
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.R
import com.ibrahemid.axrailmobile.Repositories.OrderRepository
import com.ibrahemid.axrailmobile.ViewModel.OrderViewModel
import kotlinx.android.synthetic.main.filter_item.view.*

class FilterAdapter(val orderStatus: ArrayList<OrderStatusBtn>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = orderStatus.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        print("item is bind at $position")
        holder.itemView.filterItem.text = orderStatus.get(position).itemState.value
        holder.itemView.filterItem.setOnClickListener {
            if (!orderStatus[position].status) {
                holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape)
                holder.itemView.filterItem.setTextColor(Color.WHITE)
                orderStatus[position].status = true // live data
                orderStatus

            } else {
                holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape_clear)
                holder.itemView.filterItem.setTextColor(Color.parseColor("#CED3E5"))
                orderStatus[position].status = false
            }
            (holder.itemView.context.applicationContext as? HomeActivity)?.viewModel?.changeData(itemState = orderStatus[position].itemState)
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


