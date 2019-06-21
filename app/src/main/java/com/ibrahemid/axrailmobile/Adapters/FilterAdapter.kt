package com.ibrahemid.axrailmobile.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.R
import kotlinx.android.synthetic.main.filter_item.view.*


class FilterAdapter(val orderStatusLive: MutableLiveData<List<OrderStatusBtn>>) :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    val orderStatus: List<OrderStatusBtn> = orderStatusLive.value.orEmpty()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = orderStatus.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        print("item is bind at $position")
        holder.itemView.filterItem.text = orderStatus.get(position).itemState.value
        setActiveBtnStyle(holder, orderStatus[position].isActive)

        holder.itemView.filterItem.setOnClickListener {

            //             on Click listener
            if (orderStatus[position].isActive.not()) { // Clicked and was unActive
                setActiveBtnStyle(holder, true).also {
                    orderStatus[position].isActive = true

                }
            } else { // clicked and was active
                setActiveBtnStyle(holder, false).also {
                    orderStatus[position].isActive = false
                }

            }


            orderStatusLive.value = orderStatus // when this change the main observe work


        }
    }
    private fun setActiveBtnStyle(
        holder: ViewHolder,
        IsColored: Boolean
    ) {
        if (IsColored) {//colored
            holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape)
            holder.itemView.filterItem.setTextColor(Color.WHITE)
        } else { // not colored
            holder.itemView.filterItem.setBackgroundResource(R.drawable.filter_btn_shape_clear)
            holder.itemView.filterItem.setTextColor(holder.itemView.resources.getColor(R.color.grayThree))// FIXME: 6/17/2019 Color
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}


