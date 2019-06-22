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
        with(itemsInOrder[position]) {
            holder.itemView.item_name_tv.text = item.name
            holder.itemView.item_quantity_tv.text = String.format("x " + quantity)
            holder.itemView.item_price_tv.text = String.format("USD " + item.price)
            holder.itemView.item_color_tv.text = item.color
            holder.itemView.item_state_tv.text = state.value
            holder.itemView.item_img.setImageResource(item.ProductPhoto)
        }
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}