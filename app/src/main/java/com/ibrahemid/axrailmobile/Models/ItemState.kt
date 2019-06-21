package com.ibrahemid.axrailmobile.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ItemState(val value: String) : Parcelable {
    ALL("All"),
    PAID("Paid"),
    SHIPPED("Shipped"),
    REFUNDED("Refunded"),
    DELIVERED("Delivered")
}
