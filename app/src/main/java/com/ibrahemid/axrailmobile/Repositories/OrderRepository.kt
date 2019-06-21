package com.ibrahemid.axrailmobile.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit

object OrderRepository {
    private val TAG: String?="OrderRepository"
    val dataSet = ArrayList<Order>()
    val data = MutableLiveData<List<Order>>()
    val dataFilter = MutableLiveData<List<OrderStatusBtn>>()
    const val numberOFOrdersRandom: Int = 15

    init {
        Fakeit.init()
        setOrders(numberOFOrdersRandom)
        // data.value = dataSet
    }

    fun changeData(itemState: List<OrderStatusBtn>): MutableLiveData<List<Order>> {
        val temp = ArrayList<Order>()
        for (item in itemState) {
            if (item.isActive) { // gor thro the actives only
                if (item.orderStatus == OrderStatus.ALL) {
                    data.value = dataSet
//                    setOrders(numberOFOrdersRandom) // return all data // FIXME: 6/18/2019  setiing the order to get all . nosens
                    return data
                }
                else {
                    for (order in dataSet) {
                        if (item.orderStatus == order.status) { // this may happen many times
                            temp.add(order)
                            itemState[0].isActive = false   //setting all to InActive

                        }
                    }

                }
            }
        }
        data.value = temp
        return data
    }
    fun initFilter(): MutableLiveData<List<OrderStatusBtn>> {

        val ordersStatusList: ArrayList<OrderStatusBtn> = ArrayList()
        for (i in OrderStatus.values()) {
            ordersStatusList.add(OrderStatusBtn(i))
        }
        ordersStatusList[0].isActive=true
        dataFilter.value = ordersStatusList
        return dataFilter

    }

    fun getFilterItems(): MutableLiveData<List<OrderStatusBtn>> {
        return dataFilter
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return data // not changeData // testing first
    }

    private fun setOrders(numberOfItems: Int) {
        for (i in 1..numberOfItems) { // 15 total orders
            dataSet.add(
                Order(
                    store = "OnePlus",
                    status = OrderStatus.values()[(1 until OrderStatus.values().size).random()],
                    orderId = "Order ID #0982${(1..100).random()}",
                    itemsInOrder = getItemsInsideOrder((1..4).random())
                )
            )
            Log.d(
                TAG,
                "setOrders: OrderStatus Added is ${OrderStatus.values()[(1 until OrderStatus.values().size).random()]} "
            )

        }
        data.value = dataSet
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
                    state = DeliveryStatus.values()[(0..3).random()]
                )
            )
        }
        return temp
    }

}
