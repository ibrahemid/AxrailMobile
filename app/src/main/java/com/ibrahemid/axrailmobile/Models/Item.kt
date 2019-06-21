package com.ibrahemid.axrailmobile.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val name: String, val color: String, val price: Int, val ProductPhoto: Int) : Parcelable
