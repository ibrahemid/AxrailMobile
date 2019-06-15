package com.ibrahemid.axrailmobile

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahemid.axrailmobile.Adapters.FilterAdapter
import com.ibrahemid.axrailmobile.Adapters.MainAdapter
import com.ibrahemid.axrailmobile.Models.*
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_home.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ibrahemid.axrailmobile.ViewModel.MainViewModel
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        Fakeit.init()//todo take to another class viewModel
        val ordersStatusList: ArrayList<OrderStatusBtn> = ArrayList()

        for (i in ItemState.values()) {
            ordersStatusList.add(OrderStatusBtn(i)) //toDo Faker Here
        }

        observeViewModel(viewModel)

     filterRcView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
     filterRcView.setHasFixedSize(true)



//       Toast.makeText(this," get  ${viewModel.getFilterItems().value?.size}", Toast.LENGTH_SHORT).show() // debug

     filterRcView.adapter = FilterAdapter(viewModel.getFillteritems() as MutableLiveData<List<OrderStatusBtn>>)


        initAdapter()
        navConf()

//        Timer().schedule(3000){
//        viewModel.changeData(itemState = ItemState.values().get(2))
//        }
    }

    private fun observeViewModel(viewModel:MainViewModel) {
        // Update the list when the data changes
        viewModel.orders.observe(this,
            Observer<List<Order>> {
                if (it != null) {
                    ordersRcView.adapter?.notifyDataSetChanged()
                    ordersRcView.scheduleLayoutAnimation()
                }
            })
        viewModel.filtters.observe(this, Observer {
          if(it!=null){
              for (item in it){
                  if (item.status){
                      viewModel.changeData(item.itemState)
                  }
              }
              filterRcView.adapter?.notifyDataSetChanged()
              ordersRcView.adapter?.notifyDataSetChanged()
              filterRcView.scheduleLayoutAnimation()
          }
        })
    }
    private fun initAdapter() {

        ordersRcView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false).apply {
            this.isSmoothScrollbarEnabled =true
        }
        ordersRcView.setHasFixedSize(true)
        ordersRcView.adapter=MainAdapter(viewModel.getOrders_().value.orEmpty())
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
    private fun navConf(){
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }
}
