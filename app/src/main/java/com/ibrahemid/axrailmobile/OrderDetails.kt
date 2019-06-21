package com.ibrahemid.axrailmobile

import android.content.ContentValues.TAG
import android.graphics.Color
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
import kotlinx.android.synthetic.main.order_details_fragment.*
import kotlinx.android.synthetic.main.status_bar.*


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
            setStausStyle(item.itemsInOrder[0].state.ordinal)
            item_list_recycler.adapter = ItemListAdapter(item.itemsInOrder)
        }
        back_btn.setOnClickListener {
            activity?.onBackPressed()
        }
        // test area
        val colors = intArrayOf(
            Color.RED,
            Color.BLUE
        )
        val gd = GradientDrawable()
        gd.gradientType = GradientDrawable.LINEAR_GRADIENT
        gd.colors = colors
        gd.gradientRadius = 180F

        back_btn.background = gd


    }

    private fun setStausStyle(position: Int) {
        when (position) {
            0 -> {
                //position1
                setStausStyle_(step_indicator1, null, R.color.step_state1, null)
            }
            1 -> {
                //position2
                setStausStyle_(step_indicator1, null, R.color.step_state1, null)
                setStausStyle_(step_indicator2, step_line1, R.color.step_state2, R.color.step_state1)
            }
            2 -> {
                //        position3
                setStausStyle_(step_indicator1, null, R.color.step_state1, null)
                setStausStyle_(
                    step_indicator2,
                    step_line1,
                    color = R.color.step_state2,
                    colorStart = R.color.step_state1
                )
                setStausStyle_(
                    step_indicator3,
                    step_line2,
                    color = R.color.step_state3,
                    colorStart = R.color.step_state2
                )

            }
            3 -> {
                //        position4
                setStausStyle_(step_indicator1, null, R.color.step_state1, null)
                setStausStyle_(
                    step_indicator2,
                    step_line1,
                    color = R.color.step_state2,
                    colorStart = R.color.step_state1
                )
                setStausStyle_(
                    step_indicator3,
                    step_line2,
                    color = R.color.step_state3,
                    colorStart = R.color.step_state2
                )
                setStausStyle_(
                    step_indicator4,
                    step_line3,
                    color = R.color.step_state4,
                    colorStart = R.color.step_state3
                )

            }
        }
    }

    private fun setStausStyle_(
        stepIndicator: View,
        stepLine: View?,
        color: Int,
        colorStart: Int?
    ) {

        stepIndicator.foreground = resources.getDrawable(R.drawable.ic_check_true)
        stepIndicator.foreground.setColorFilter(resources.getColor(color), PorterDuff.Mode.SRC_IN)
        if (stepLine != null && colorStart != null) {
            val colors = intArrayOf(
                Color.RED,
                Color.BLUE
            )

            val gd = GradientDrawable()
            gd.gradientType = GradientDrawable.LINEAR_GRADIENT
            gd.colors = colors
            gd.gradientRadius = 180F

            stepLine.background = gd

//                gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        }

    }


}

//
//CIRCLE		 step_indicator$number
//EPMTY
//android:foreground="@drawable/step_background_empty"
//android:foregroundTint="@color/empty_step_gray"
//
//
//COLOR
//android:foreground="@drawable/ic_check_true"
//android:foregroundTint="@color/step_state$number"
//
//
//
//LINE		 step_line$number
//EPMTY
//android:background="@color/empty_step_gray"
//
//
//COLOR
//android:background="@drawable/step_one_gradient"
//
//
//




















