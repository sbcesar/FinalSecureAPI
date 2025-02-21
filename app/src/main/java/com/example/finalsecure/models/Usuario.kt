package com.example.finalsecure.models

data class Usuario(
    val _id: String?,
    val username: String,
    val password: String,
    val roles: Role? = Role.USER,
    val direccion: Direccion
)