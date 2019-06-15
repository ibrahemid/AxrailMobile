package com.ibrahemid.axrailmobile.Repositories

import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.MutableLiveData
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit

object OrderRepository {

    val dataSet = ArrayList<Order> ()
    val itemsInsideOrderList = ArrayList<OrderItem>()
    val data = MutableLiveData<List<Order>> ()
    //public MutableLiveData<List<NicePlace>> getNicePlaces(){
     fun getOrders (): MutableLiveData<List<Order>> {
    Fakeit.init()
        setOrders(2)

    data.value= dataSet
    return data // not filter // testing first
}

    fun filter(itemState: ItemState):MutableLiveData<List<Order>> {

        data.value= dataSet + dataSet

        return data // not filter // testing first
    }

        private fun setOrders(cons:Int) {
        for (i in 1..2){
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
