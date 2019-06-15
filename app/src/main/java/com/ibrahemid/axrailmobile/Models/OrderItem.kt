package com.ibrahemid.axrailmobile.Models

data class OrderItem(val item:Item, val quantity:Int, val totalPrice:Int ,val state:ItemState ){
}