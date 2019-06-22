package com.ibrahemid.axrailmobile.Models

import android.os.Parcelable
import com.ibrahemid.axrailmobile.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val orderId: String,
    val store: String,
    val storeLogo: Int = when (store) {
        "OnePlus" -> R.drawable.ic_oneplus_icon
        "Xiaomi" -> R.drawable.ic_xiaomi_logo
        "Huawei" -> R.drawable.ic_huawei_logo
        else -> R.drawable.ic_xiaomi_logo
    }
    ,
    val itemsInOrder: ArrayList<OrderItem>,
    val status: OrderStatus,
    val shippingPrice: Int,
    val priceSubtotal: Int = itemsInOrder.sumBy { it.totalPrice }
) : Parcelable