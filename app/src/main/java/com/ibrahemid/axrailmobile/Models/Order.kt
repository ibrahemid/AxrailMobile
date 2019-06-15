package com.ibrahemid.axrailmobile.Models

data class Order (
    val orderId:      String,
    val store : String,
    val itemsInOrder: ArrayList<OrderItem>,
    val status:       ItemState
)


