package com.example.examenandroid2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenandroid2.ui.theme.ExamenAndroid2Theme

class MainActivity : ComponentActivity() {
    private lateinit var textoDatos: TextView
    private lateinit var lista: ListView
    private lateinit var txtProducto: EditText
    private lateinit var txtCantidad: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var btnAñadir: Button
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var productosLista: MutableList<Producto>

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

        actualizarLista()
    }

    private fun actualizarLista() {
        adapter.clear()
        var total = 0

        for (producto in productosLista) {
            productosLista.add(producto)
            adapter.notifyDataSetChanged()
            total += producto.precio * producto.cantidad
        }

        textoDatos.text = "Lista de la compra: ${productosLista.size} = $total €"
    }
}
