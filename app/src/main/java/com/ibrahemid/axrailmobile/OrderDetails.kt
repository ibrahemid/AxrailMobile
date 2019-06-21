package com.ibrahemid.axrailmobile

import android.content.ContentValues.TAG
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
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
import kotlinx.android.synthetic.main.item_store_card.*
import kotlinx.android.synthetic.main.order_details_fragment.*
import kotlinx.android.synthetic.main.status_bar.*
import kotlinx.android.synthetic.main.toolbar_fragment.*


class OrderDetails : Fragment() {
    // FIXME: 6/19/2019 replace android text with tools
    companion object {
        const val ITEMS = "items"
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
            initView(item)
        }
        view_product_btn.setOnClickListener {
            setStyleToStatusBar(3)
        }
        back_btn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initView(item: Order) {
        setStyleToStatusBar(item.itemsInOrder[0].state.ordinal)
        item_list_recycler.adapter = ItemListAdapter(item.itemsInOrder)
        store_name_card.text = item.store
        order_total_price.text = String.format("USD ${item.priceSubtotal + item.shippingPrice}")
        shipping_price.text = String.format("USD ${item.shippingPrice}")
        merchandise_price.text = String.format("USD ${item.priceSubtotal}")
        store_logo.setImageResource(item.storeLogo)
    }

    private fun setStyleToStatusBar(position: Int) {
        setStyleForUi(
            position,
            listOf(step_indicator1, step_indicator2, step_indicator3, step_indicator4),
            listOf(null, step_line1, step_line2, step_line3),
            listOf(R.color.step_state1, R.color.step_state2, R.color.step_state3, R.color.step_state4),
            listOf(null, R.color.step_state1, R.color.step_state2, R.color.step_state3)
        )
    }

    private fun setStyleForUi(
        mode: Int,
        stepIndicators: List<View>,
        stepLines: List<View?>,
        colors: List<Int>,
        colorStarts: List<Int?>
    ) {
        (0..mode).forEach { i ->
            stepIndicators[i].foreground = resources.getDrawable(R.drawable.ic_check_true)
            stepIndicators[i].foreground.setColorFilter(resources.getColor(colors[i]), PorterDuff.Mode.SRC_IN)
            if (stepLines[i] != null && colorStarts[i] != null) {
                GradientDrawable().let {
                    it.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                    it.colors = intArrayOf(
                        resources.getColor(colorStarts[i]!!),
                        resources.getColor(colors[i])
                    )
                    stepLines[i]!!.background = it
                }
            }
        }
    }
}

















