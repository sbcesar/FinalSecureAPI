package com.example.finalsecure.models

data class UsuarioRegisterDTO(
    val username: String,
    val password: String,
    val passwordRepeat: String,
    val roles: Role?,
    val direccion: Direccion
)