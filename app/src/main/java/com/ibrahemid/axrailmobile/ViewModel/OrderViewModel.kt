package com.ibrahemid.axrailmobile.ViewModel

import android.util.EventLog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahemid.axrailmobile.Models.ItemState
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Repositories.OrderRepository


class OrderViewModel : ViewModel(){

    private val orders: MutableLiveData<List<Order>>by lazy {
        MutableLiveData<List<Order>>()
            .also {
                           loadOrders_()
        }
    }
    //end
    fun getOrders_(): LiveData<List<Order>> {
        return OrderRepository.getOrders()
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
