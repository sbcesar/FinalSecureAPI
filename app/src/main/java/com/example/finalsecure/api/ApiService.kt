package com.example.finalsecure.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    fun getAllUsers() : Response<String>

}