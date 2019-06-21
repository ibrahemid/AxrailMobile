package com.ibrahemid.axrailmobile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Adapters.ItemListAdapter
import com.ibrahemid.axrailmobile.Models.Order
import kotlinx.android.synthetic.main.order_details_fragment.*

class OrderDetails : Fragment() {
    // FIXME: 6/19/2019 replace android text with tools
    companion object {
        val ITEMS = "items"

        fun newInstance(data: Order): OrderDetails {
            val args = Bundle()
            args.putParcelable(ITEMS, data)
            val fragment = OrderDetails()
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_details_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val item = arguments?.getParcelable<Order>(ITEMS)
        Log.d(TAG, "onActivityCreated: ${item?.itemsInOrder}")
        item_list_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        item_list_recycler.setHasFixedSize(true)
        if (item != null) {
            item_list_recycler.adapter = ItemListAdapter(item.itemsInOrder)
        }
        back_btn.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}