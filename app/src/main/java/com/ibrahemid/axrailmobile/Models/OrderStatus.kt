package com.ibrahemid.axrailmobile.Models

enum class OrderStatus(val value: String) {
    ALL("All"),
    PAID("Paid"),
    SHIPPED("Shipped"),
    REFUNDED("Refunded"),
    DELIVERED("Delivered")
}
