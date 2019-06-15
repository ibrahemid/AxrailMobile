package com.ibrahemid.axrailmobile.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.databinding.OrderItemCardBinding

class MainAdapter (val orders: List<Order>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(orders[position])
    override fun getItemCount()=orders.size
    inner class ViewHolder(val binding: OrderItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            with(binding) {
                orderIdTv.text=item.orderId
                Toast.makeText(itemView.context,"All-> ${orders.size} Po-> $position",Toast.LENGTH_SHORT).show()
                itemListRc.layoutManager = LinearLayoutManager(binding.root.context,RecyclerView.VERTICAL,false)
                itemListRc.setHasFixedSize(true)
                itemListRc.adapter=ItemListAdapter(arrayListOf(item.itemsInOrder[0],item.itemsInOrder[1]))
            }
        }
    }

}
