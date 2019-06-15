package com.ibrahemid.axrailmobile.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.HomeActivity
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.filter_item.view.*



class FilterAdapter(val orderStatusLive: MutableLiveData<OrderStatusBtn>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    val orderStatusList:List<OrderStatusBtn?> = listOf(orderStatusLive.value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        return ViewHolder(view)

    }
    override fun getItemCount() = orderStatusList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        print("item is bind at $position")
        holder.itemView.filterItem.text = orderStatusList.get(position).itemState.value
        holder.itemView.filterItem.setOnClickListener {
            if (!orderStatusList[position]?.status) {
                holder.itemView.filterItem.setBackgroundResource(com.ibrahemid.axrailmobile.R.drawable.filter_btn_shape)
                holder.itemView.filterItem.setTextColor(Color.WHITE)
                orderStatusList[position].status = true // live data


            } else {
                holder.itemView.filterItem.setBackgroundResource(com.ibrahemid.axrailmobile.R.drawable.filter_btn_shape_clear)
                holder.itemView.filterItem.setTextColor(Color.parseColor("#CED3E5"))
                orderStatusList[position].status = false
            }

       //     Toast.makeText(holder.itemView.context,"change at $position", Toast.LENGTH_SHORT).show()

           (holder.itemView.context.applicationContext as? HomeActivity)?.viewModel?.changeData(itemState = orderStatusList[position].itemState)
            // (    (holder.itemView.context.applicationContext as? HomeActivity)?.showToast())
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

}


