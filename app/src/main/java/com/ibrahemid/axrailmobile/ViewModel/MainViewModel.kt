package com.ibrahemid.axrailmobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahemid.axrailmobile.Models.ItemState
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.Repositories.OrderRepository


class MainViewModel : ViewModel(){
     val orders: MutableLiveData<List<Order>>by lazy {
        MutableLiveData<List<Order>>()
    }
    val filtters: MutableLiveData<List<OrderStatusBtn>>by lazy {
        MutableLiveData<List<OrderStatusBtn>>().also {
            OrderRepository.initFilter()
        }
    }


    init {
        OrderRepository.initFilter()
        filtters.value= OrderRepository.getFilterItems().value

    }
    fun getOrders_(): LiveData<List<Order>> {
        //print("Size is MainViewModel ------ ${orders.value?.size} --------")
        return  OrderRepository.getOrders()

    }
    fun getFillteritems(): LiveData<List<OrderStatusBtn>> {
        return filtters
    }
    private fun loadOrders_() {
        orders.value =OrderRepository.getOrders().value
    }

     fun changeData(itemState: ItemState){
        orders.postValue( OrderRepository.filter(itemState).value )

    }

}
