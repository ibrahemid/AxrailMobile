package com.ibrahemid.axrailmobile.Models

data class Order (
    val orderId:      String,
    val itemsInOrder: ArrayList<OrderItem>,
    val status:       String
)