package com.example.crud2new.Model

data class Usuario(

    val nombre: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val contraseña: String = "",
    val camisetas: List<Camiseta>

){
    val _id: String = ""

    constructor( camisetas: List<Camiseta>) : this("", "", "", "", camisetas)

}