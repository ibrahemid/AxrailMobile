package com.ibrahemid.axrailmobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.Repositories.OrderRepository


class MainViewModel : ViewModel() {
    val orders: MutableLiveData<List<Order>>by lazy {
        MutableLiveData<List<Order>>()
    }
    val filterBtnsList: MutableLiveData<List<OrderStatusBtn>>by lazy {
        MutableLiveData<List<OrderStatusBtn>>().also {
            OrderRepository.initFilter()
        }
    }

    init {
        loadOrders_()
    }

    fun getOrders(): LiveData<List<Order>> {
        return orders
    }

    fun getFilterItems(): LiveData<List<OrderStatusBtn>> {
        return filterBtnsList
    }

    private fun loadOrders_() {
        orders.value = OrderRepository.getOrders().value
        OrderRepository.initFilter().let {
            filterBtnsList.value = it.value
        }

    }

    fun changeData(itemState: List<OrderStatusBtn>) {
        OrderRepository.changeData(itemState).value.let {
            orders.value = it
        }
    }

}