package com.example.examenandroid2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var textoDatos: TextView
    private lateinit var lista: ListView
    private lateinit var txtProducto: EditText
    private lateinit var txtCantidad: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var btnAñadir: Button
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var productosLista: MutableList<Producto>
    private lateinit var productoConPrecio: MutableList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textoDatos = findViewById(R.id.textoDatos)
        lista = findViewById(R.id.lista)
        txtProducto = findViewById(R.id.txtProducto)
        txtCantidad = findViewById(R.id.txtCantidad)
        txtPrecio = findViewById(R.id.txtPrecio)
        btnAñadir = findViewById(R.id.btnAñadir)
        productosLista = mutableListOf()
        productoConPrecio = mutableListOf()

        textoDatos.text = "Lista de la compra vacia"

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        lista.adapter = adapter

        btnAñadir.setOnClickListener {
            agregarProducto()
        }

    }

    private fun agregarProducto() {
        val producto = txtProducto.text.toString()
        val cantidad = txtCantidad.text.toString().toIntOrNull() ?: 1
        val precio = txtPrecio.text.toString().toIntOrNull() ?:0

        if (producto.isEmpty()){
            Toast.makeText(this, "Debe ingresar un nombre de producto", Toast.LENGTH_SHORT).show()
        }

        var productoNuevo = Producto(producto, cantidad, precio)
        productosLista.add(productoNuevo)

        if (productoNuevo.precio != 0){
            productoConPrecio.add(productoNuevo)
        }

        txtProducto.text.clear()
        txtCantidad.text.clear()
        txtPrecio.text.clear()

        actualizarLista()
    }

    private fun actualizarLista() {
        adapter.clear()
        var total = 0

        for (producto in productosLista) {
            val itemString = "${producto.nombre} (${producto.cantidad}) = ${producto.precio}€"
            adapter.add(itemString)
            adapter.notifyDataSetChanged()
            total += producto.precio * producto.cantidad
        }

        textoDatos.text = "Lista de la compra: ${productoConPrecio.size} = $total €"
    }
}
