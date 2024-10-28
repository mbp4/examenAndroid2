package com.example.examenandroid2

data class Producto(val nombre: String, val cantidad: Int, val precio: Int){
    constructor(nombre: String, cantidad: Int) : this(nombre, cantidad, 0)

}
