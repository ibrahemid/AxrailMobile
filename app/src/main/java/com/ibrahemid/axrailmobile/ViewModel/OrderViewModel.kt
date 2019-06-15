package com.ibrahemid.axrailmobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahemid.axrailmobile.Models.ItemState
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Repositories.OrderRepository


class OrderViewModel : ViewModel(){
     val orders: MutableLiveData<List<Order>>by lazy {
        MutableLiveData<List<Order>>()
            .also {
                    //      loadOrders_()
        }
    }
    init {
     //   loadOrders_()
    }
    fun getOrders_(): LiveData<List<Order>> {
        //print("Size is OrderViewModel ------ ${orders.value?.size} --------")
        return  OrderRepository.getOrders()

    }
    private fun loadOrders_() {
        orders.value =OrderRepository.getOrders().value
    }

     fun changeData(itemState: ItemState){
        orders.postValue(
            OrderRepository.filter(itemState).value
        )

    }

}
