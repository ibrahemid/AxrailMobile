package com.ibrahemid.axrailmobile.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Activities.HomeActivity
import com.ibrahemid.axrailmobile.Fragments.OrderDetails
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.R
import com.ibrahemid.axrailmobile.databinding.OrderItemCardBinding


class MainAdapter(var orders: List<Order>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    val TAG = "MainAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(orders[position])
    override fun getItemCount() = orders.size

    fun setNewData(order: List<Order>) {
        orders = order
    }

    inner class ViewHolder(private val binding: OrderItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            with(binding) {
                orderIdTv.text = item.orderId
                Log.d(TAG, "RC orders size : ${orders.size}")
                Log.d(TAG, "RC orders Count : $itemCount")
                itemListRc.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
                itemListRc.setHasFixedSize(true)
                itemListRc.adapter = ItemListAdapter(item.itemsInOrder)
                orderDetailsBtn.setOnClickListener {
                    Log.d(TAG, "Fragment Starting ${item.itemsInOrder}")
                    val fragmentManager = (itemView.context as HomeActivity).supportFragmentManager
                    fragmentManager.beginTransaction().setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                        .replace(R.id.coordinatorLayout, OrderDetails.newInstance(item)).addToBackStack(null)
                        .commit()
                }
            }
        }

    }
}
