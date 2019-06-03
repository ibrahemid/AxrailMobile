package com.ibrahemid.axrailmobile

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.resources.TextAppearance
import com.ibrahemid.axrailmobile.Adapters.FilterAdapter
import com.ibrahemid.axrailmobile.Adapters.OrdersAdapter
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import java.time.format.TextStyle


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
       // supportActionBar?.setDisplayShowTitleEnabled(true)


        Fakeit.init()
        val FillterOptions: ArrayList<FilterBtn> = ArrayList()
        for (i in ItemState.values()) {
            FillterOptions.add(FilterBtn(i.value)) //toDo Faker Here
        }
        filterRcView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        filterRcView.setHasFixedSize(true)
        filterRcView.adapter = FilterAdapter(FillterOptions)


        //this line was commented to test the header
       ordersRcView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false).apply { isSmoothScrollbarEnabled =true }
        val ordersList = ArrayList<Order>()

        val orderItemList = ArrayList<OrderItem>()


//  for test only
        for (i in 1..5){
            val tes =  OrderItem(item = Item(Fakeit.app().name(),Fakeit.artist().name(),213,"asd"),quantity = 2,totalPrice = 123,state = ItemState.values()[i.rem(ItemState.values().size)])
            orderItemList.add(tes)
            ordersList.add(Order(status = ItemState.values()[i%5].value,orderId ="Order ID #86032",itemsInOrder = orderItemList)
            )
        }
        ordersRcView.setHasFixedSize(true)
        //ordersRcView.isNestedScrollingEnabled=false
        ordersRcView.adapter=OrdersAdapter(ordersList)






//        ordersRcView.setHasFixedSize(true)
        //  recyclerViewVertical.isNestedScrollingEnabled=false
//        ordersRcView.adapter = FilterAdapter(list)








        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
//            R.id.nav_camera -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
