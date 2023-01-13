package com.example.truptiassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truptiassignment.API.RocketService
import com.example.truptiassignment.models.Rocket

class  RocketRepository(private val rocketService: RocketService) {

    private val rocketLiveData = MutableLiveData<Rocket>()

    val rockets :LiveData<Rocket>
    get() =rocketLiveData

    suspend fun getRocket()
    {
        var rocket = rocketService.getRocket()
        if(rocket?.body() !=null)
        {
            rocketLiveData.postValue(rocket.body())
        }
    }
}