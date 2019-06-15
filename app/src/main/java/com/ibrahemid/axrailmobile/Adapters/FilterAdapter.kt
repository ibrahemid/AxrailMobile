package com.ibrahemid.axrailmobile.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.HomeActivity
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.filter_item.view.*



class FilterAdapter(val orderStatusLive: MutableLiveData<List<OrderStatusBtn>>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    val orderStatus:List<OrderStatusBtn> = orderStatusLive.value.orEmpty()

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
                holder.itemView.filterItem.setBackgroundResource(com.ibrahemid.axrailmobile.R.drawable.filter_btn_shape)
                holder.itemView.filterItem.setTextColor(Color.WHITE)
                orderStatus[position].status = true // live data

            } else {
                holder.itemView.filterItem.setBackgroundResource(com.ibrahemid.axrailmobile.R.drawable.filter_btn_shape_clear)
                holder.itemView.filterItem.setTextColor(Color.parseColor("#CED3E5"))
                orderStatus[position].status = false


            }

       //     Toast.makeText(holder.itemView.context,"change at $position", Toast.LENGTH_SHORT).show()

          // (holder.itemView.context.applicationContext as? HomeActivity)?.viewModel?.changeData(itemState = orderStatus[position].itemState)
            // (    (holder.itemView.context.applicationContext as? HomeActivity)?.showToast())
            orderStatusLive.postValue(orderStatus)
            //Toast.makeText(holder.itemView.context,"change at ${orderStatus.map { it.status }}", Toast.LENGTH_SHORT).show()
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}


