package com.ibrahemid.axrailmobile

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Adapters.FilterAdapter
import com.ibrahemid.axrailmobile.Adapters.OrdersAdapter
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_home.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ibrahemid.axrailmobile.ViewModel.OrderViewModel


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        viewModel.getOrders_().observe(this,
            Observer {

            })
        Fakeit.init()//todo take to another class viewModel
        val ordersStatusList: ArrayList<OrderStatusBtn> = ArrayList()
        val allOrdersList = ArrayList<Order>()
        val itemsInsideOrderList = ArrayList<OrderItem>()

        for (i in ItemState.values()) {
            ordersStatusList.add(OrderStatusBtn(i)) //toDo Faker Here
        }

//        for (i in 1..5){  /// BAAAD tODO CHANGE that
//            itemsInsideOrderList.add(
//                OrderItem(item = Item(Fakeit.app().name(),Fakeit.artist().name(),213,"asd"),quantity = 2,totalPrice = 123,state = ItemState.values()[i.rem(ItemState.values().size)])
//            )
//            allOrdersList.add(
//                Order(status = ItemState.values()[i%5].value,orderId ="Order ID #86032",itemsInOrder = itemsInsideOrderList
//                )
//            )
//        }

     filterRcView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
     filterRcView.setHasFixedSize(true)
     filterRcView.adapter = FilterAdapter(ordersStatusList)
        observeViewModel(viewModel)
        initAdapter()
        navConf()
    }



    private fun observeViewModel(viewModel:OrderViewModel) {
        // Update the list when the data changes
        viewModel.getOrders_().observe(this,
            Observer<List<Order>> { orders ->
                if (orders != null) {
                    ordersRcView.adapter?.notifyDataSetChanged()
                }
            })
    }
    private fun initAdapter() {

        ordersRcView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false).apply {
            this.isSmoothScrollbarEnabled =true
        }
        ordersRcView.setHasFixedSize(true)
        ordersRcView.adapter=OrdersAdapter(viewModel.getOrders_().value.orEmpty())
    }




    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun navConf(){
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }
}
