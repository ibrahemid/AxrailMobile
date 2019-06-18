package com.ibrahemid.axrailmobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.ibrahemid.axrailmobile.Models.Item

class OrderDetailsViewModel : ViewModel() {
    private val Items: MutableLiveData<List<Item>> by lazy {
        MutableLiveData<List<Item>>().also {
            loadUsers()
        }
    }

    fun getItems(): LiveData<List<Item>> {
        return Items
    }

    private fun loadUsers() {

    }
}