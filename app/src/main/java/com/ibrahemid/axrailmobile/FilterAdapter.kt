package com.ibrahemid.axrailmobile

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import org.jetbrains.anko.toast
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Models.FilterBtn

class FilterAdapter (val btns : ArrayList<FilterBtn>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.filter_item,parent,false)
      //  print("item is onCreateViewHolder ")
        return ViewHolder(view)

    }

    override fun getItemCount()= btns.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        print("item is bind at $position")
       // Toast.makeText(holder.itemView.context,"item Toast at  $position",Toast.LENGTH_LONG).show()
        holder.filterItem.text=btns.get(position).title
        holder.filterItem.setOnClickListener {
            if(!btns[position].status){
                holder.filterItem.setBackgroundResource (R.drawable.filter_btn_shape)
                holder.filterItem.setTextColor(Color.WHITE)
                btns[position].status=true

            }
            else{
                holder.filterItem.setBackgroundResource (R.drawable.filter_btn_shape_clear)
                holder.filterItem.setTextColor(Color.parseColor("#CED3E5"))
                btns[position].status=false


            }
        }

    }



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val filterItem:Button = itemView.findViewById(R.id.filterItem)

    }

}


