package com.ibrahemid.axrailmobile.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.FilterBtn
import com.ibrahemid.axrailmobile.Models.ItemState
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Models.OrderItem
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.content_home.view.*
import kotlinx.android.synthetic.main.order_item_card.view.*


class OrdersAdapter (val orders: ArrayList<Order>) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view:View= LayoutInflater.from(parent.context).inflate(com.ibrahemid.axrailmobile.R.layout.order_item_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.order_id_tv.text=orders[position].orderId
        holder.itemView.item_list_rc.layoutManager = LinearLayoutManager(holder.itemView.context,RecyclerView.VERTICAL,false)
        holder.itemView.item_list_rc.setHasFixedSize(true)
        holder.itemView.redDot.bringToFront()
   //     holder.itemView.item_list_rc.isNestedScrollingEnabled =false

        holder.itemView.item_list_rc.adapter=ItemListAdapter(arrayListOf(orders[position].itemsInOrder[0],orders[position].itemsInOrder[1]))
//        holder.itemView.item_list_rc.adapter=ItemListAdapter(orders[position].itemsInOrder)


        }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)



    override fun getItemCount()=orders.size



}