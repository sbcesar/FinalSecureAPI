package com.example.finalsecure.models

data class UsuarioDTO(
    val username: String,
    val roles: Role?,
    val direccion: Direccion
)