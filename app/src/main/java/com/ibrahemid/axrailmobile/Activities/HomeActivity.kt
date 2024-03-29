package com.ibrahemid.axrailmobile.Activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.ibrahemid.axrailmobile.Adapters.FilterAdapter
import com.ibrahemid.axrailmobile.Adapters.MainAdapter
import com.ibrahemid.axrailmobile.Models.Order
import com.ibrahemid.axrailmobile.Models.OrderStatusBtn
import com.ibrahemid.axrailmobile.R
import com.ibrahemid.axrailmobile.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_activity_content.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var viewModel: MainViewModel
    lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        observeViewModel(viewModel)

        initAdapter()

        navConf()

    }

    private fun observeViewModel(viewModel: MainViewModel) {
        // Update the list when the data changes
        viewModel.orders.observe(this,
            Observer<List<Order>> {
                if (it != null) {
                    Log.d(" Order Debug ", "${viewModel.orders.value?.size}")
                    Log.d(" Order GetOrder ", "${viewModel.getOrders().value?.size}")
                    mAdapter.setNewData(viewModel.getOrders().value.orEmpty()).let {

                        ordersRcView.adapter?.notifyDataSetChanged()
                        ordersRcView.scheduleLayoutAnimation()
                    }
                }
            }
        )
        viewModel.filterBtnsList.observe(this, Observer {
            if (it != null) {
                viewModel.changeData(it).let {
                    if (viewModel.orders.value?.size == 0) {
                        no_items_indicator.visibility = View.VISIBLE
                    } else {
                        no_items_indicator.visibility = View.GONE
                    }
                }

                Log.d(" Filter Debug ", "${viewModel.orders.value?.size}")

            }
        })
    }

    private fun initAdapter() {

        filterRcView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mAdapter = MainAdapter(viewModel.orders.value.orEmpty())
        ordersRcView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false).apply {
            this.isSmoothScrollbarEnabled = true
        }

        filterRcView.setHasFixedSize(true)
        ordersRcView.setHasFixedSize(true)
        filterRcView.adapter =
            FilterAdapter(viewModel.getFilterItems() as MutableLiveData<List<OrderStatusBtn>>)
        ordersRcView.adapter = mAdapter
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

    private fun navConf() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }
}
