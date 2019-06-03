package com.ibrahemid.axrailmobile.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.OrderItem
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.single_item_card.view.*

class ItemListAdapter  (val itemsInOrder: ArrayList<OrderItem>) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.single_item_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=itemsInOrder.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.item_name_tv.text=itemsInOrder[position].item.name
        holder.itemView.item_quantity_tv.text= String.format("x "+itemsInOrder[position].quantity)
        holder.itemView.item_price_tv.text= String.format("USD "+itemsInOrder[position].totalPrice)
        holder.itemView.item_color_tv.text=itemsInOrder[position].item.color
        holder.itemView.item_state_tv.text=itemsInOrder[position].state.value
        holder.itemView.item_img.setImageResource(R.drawable.img)

    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


}