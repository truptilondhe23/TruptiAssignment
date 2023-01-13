package com.example.truptiassignment.API

import com.example.truptiassignment.models.Rocket
import retrofit2.Response
import retrofit2.http.GET

interface RocketService {

    @GET("/v4/rockets")
    suspend fun getRocket() : Response<Rocket>
}