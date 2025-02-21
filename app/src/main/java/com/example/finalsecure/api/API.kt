package com.example.finalsecure.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val BASE_URL = "https://finalsecuremongodb.onrender.com"

    // Solo se crea cuando lo llamo
    val retrofitService: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

    // Returna la instancia de retrofit necesaria para inicializar nuestras peticiones
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}