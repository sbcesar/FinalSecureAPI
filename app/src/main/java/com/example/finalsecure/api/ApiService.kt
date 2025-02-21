package com.example.finalsecure.api

import com.example.finalsecure.models.Usuario
import com.example.finalsecure.models.UsuarioLoginDTO
import com.example.finalsecure.models.UsuarioRegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/usuario/login")
    suspend fun login(@Body usuarioLoginDTO: UsuarioLoginDTO): Response<Usuario>

    @POST("/usuario/register")
    suspend fun register(@Body usuarioRegisterDTO: UsuarioRegisterDTO): Response<Usuario>

}