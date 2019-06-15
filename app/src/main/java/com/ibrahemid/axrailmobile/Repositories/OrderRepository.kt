package com.ibrahemid.axrailmobile.Repositories

import android.widget.Toast
import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.MutableLiveData
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit

object OrderRepository {

    val dataSet = ArrayList<Order> ()
    val itemsInsideOrderList = ArrayList<OrderItem>()
    val data = MutableLiveData<List<Order>> ()
    //public MutableLiveData<List<NicePlace>> getNicePlaces(){
    init {
        Fakeit.init()
        setOrders(2)
        data.value= dataSet
    }
     fun getOrders(): MutableLiveData<List<Order>> {
        print("Size is OrderRepository ------ ${dataSet.size} --------")
        return data // not filter // testing first
    }

    fun filter(itemState: ItemState):MutableLiveData<List<Order>> {
        setOrders(4)
        data.postValue(dataSet)
        return data
    }

        private fun setOrders(y:Int) {
        for (i in 1..y){
            itemsInsideOrderList.add(
                OrderItem(item = Item(Fakeit.app().name(), Fakeit.artist().name(),213,"asd"),quantity = 2,totalPrice = 123,state = ItemState.values()[i.rem(
                    ItemState.values().size)])
            )
            dataSet.add(
                Order(status = ItemState.values()[i%5].value,orderId ="Order ID #$i",itemsInOrder = itemsInsideOrderList
                )
            )
        }
    }

}
