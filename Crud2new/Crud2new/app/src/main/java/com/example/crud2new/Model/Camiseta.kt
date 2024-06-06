package com.example.crud2new.Model


data class Camiseta(
    val nombre: String,
    val descripcion: String,
    val precio: Number,
    val imagen: String,
    ) {
    val _id: String = ""

    constructor() : this("", "", 0,"")

}
