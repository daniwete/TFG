package com.example.crud2new.Model

class RespuestaApi<T> {
    val exito: Boolean? = null

    val datos: T? = null

    override fun toString(): String {
        return "DataResponse(code=$exito, data=$datos)"
    }
}