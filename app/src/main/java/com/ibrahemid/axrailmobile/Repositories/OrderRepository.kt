package com.ibrahemid.axrailmobile.Repositories

import android.util.Range
import androidx.lifecycle.MutableLiveData
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.RandomAccess

object OrderRepository {

    val dataSet = ArrayList<Order>()
    val itemsInsideOrderList = ArrayList<OrderItem>()
    val data = MutableLiveData<List<Order>>()
    val dataFilter = MutableLiveData<List<OrderStatusBtn>>()

    //public MutableLiveData<List<NicePlace>> getNicePlaces(){
    init {
        Fakeit.init()
        setOrders(15)
        data.value = dataSet
    }

    fun filter(itemState: ItemState): MutableLiveData<List<Order>> {
        val temp = ArrayList<Order>()
        for (order in dataSet){
            if (order.status.value==itemState.value){
                temp.add(order)
            }
        }
        data.postValue(temp)
        return data // filtered data based on the orderStatus In each Entry
    }

    fun initFilter(): MutableLiveData<List<OrderStatusBtn>> {
        val ordersStatusList: ArrayList<OrderStatusBtn> = ArrayList()
        for (i in ItemState.values()) {
            ordersStatusList.add(OrderStatusBtn(i))
        }
        dataFilter.value = ordersStatusList
        return dataFilter
    }

    fun getFilterItems(): MutableLiveData<List<OrderStatusBtn>> {
        return dataFilter
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return data // not filter // testing first
    }

    private fun setOrders(i_: Int) {
        for (i in 1..i_) { // 15 total orders
            dataSet.add(
                Order(
                    store = "OnePlus",
                    status = ItemState.values()[i % 5],
                    orderId = "Order ID #0982${(1..100).random()}",
                    itemsInOrder = getItemsInsideOrder((1..4).random())
                )
            )
        }
    }
    private fun getItemsInsideOrder(itemNumbers: Int): ArrayList<OrderItem> {
        val temp = ArrayList<OrderItem>()
        for (i in 1..itemNumbers) {
            temp.add(
                OrderItem(
                    item = Item(
                        name = Fakeit.app().name(),
                        color = Fakeit.artist().name(),
                        price = 213,
                        ProductPhoto = Fakeit.bank().name()
                    ),
                    quantity = (0..4).random(),//
                    totalPrice = (160..900).random(),
                    state = ItemState.values()[(0..4).random()]
                )
            )
        }
        return temp
    }

}
