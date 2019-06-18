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

    init {
        loadOrders_()
    }

    val filterBtnsList: MutableLiveData<List<OrderStatusBtn>>by lazy {
        MutableLiveData<List<OrderStatusBtn>>().also {
            OrderRepository.initFilter()
        }
    }


    // FIXME: 6/18/2019  use the getters and setter from kotlin - delete redundant  code
    init {
        OrderRepository.initFilter()
        filterBtnsList.value = OrderRepository.getFilterItems().value
    }

    fun getOrders_(): LiveData<List<Order>> {
        //print("Size is MainViewModel ------ ${orders.value?.size} --------")
        return orders
    }

    fun getFillteritems(): LiveData<List<OrderStatusBtn>> {
        return filterBtnsList
    }

    private fun loadOrders_() {
        orders.value = OrderRepository.getOrders().value
    }

    fun changeData(itemState: List<OrderStatusBtn>) {
        OrderRepository.changeData(itemState).value.let {
            orders.value = it
        }
    }

}
