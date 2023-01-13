package com.example.truptiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truptiassignment.API.RetrofitHelper
import com.example.truptiassignment.API.RocketService
import com.example.truptiassignment.Adapters.CustomAdater
import com.example.truptiassignment.databinding.ActivityMainBinding
import com.example.truptiassignment.models.Rocket
import com.example.truptiassignment.models.RocketItem
import com.example.truptiassignment.repository.MainViewModelFactory
import com.example.truptiassignment.repository.RocketRepository
import com.example.truptiassignment.viewmodels.MainViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    internal var activityMainBinding: ActivityMainBinding?= null
    private var customAdater: CustomAdater? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.recyclerview
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        customAdater = CustomAdater()
        //set the CustomAdapter
        recyclerView.setAdapter(customAdater)

        val rocketService=RetrofitHelper.getInstance().create(RocketService::class.java)
        val respository=RocketRepository(rocketService)
        mainViewModel=ViewModelProvider(this, MainViewModelFactory(respository)).get(MainViewModel::class.java)
            mainViewModel.rockets.observe(this,{ mDeveloperModel ->
                Log.e("Trupti","it.toString()")
                customAdater?.setDeveloperList(mDeveloperModel as ArrayList<RocketItem>)

            })

    }

}