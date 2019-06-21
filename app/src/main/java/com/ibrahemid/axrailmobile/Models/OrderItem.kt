package com.ibrahemid.axrailmobile.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class OrderItem(val item:Item, val quantity:Int, val totalPrice:Int ,val state:ItemState )  : Parcelable