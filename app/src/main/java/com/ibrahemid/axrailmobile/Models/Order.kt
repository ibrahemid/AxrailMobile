package com.ibrahemid.axrailmobile.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val orderId:      String,
    val store : String,
    val itemsInOrder: ArrayList<OrderItem>,
    val status:       OrderStatus
) : Parcelable

