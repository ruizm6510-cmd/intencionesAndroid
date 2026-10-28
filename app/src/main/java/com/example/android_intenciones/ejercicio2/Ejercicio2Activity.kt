package com.example.android_intenciones.ejercicio2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intenciones.R

data class Producto(val nombre: String, val imagen: Int)

class Ejercicio2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj2)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productos = listOf(
            Producto("Laptop", R.drawable.ic_launcher_foreground),
            Producto("Smartphone", R.drawable.ic_launcher_foreground),
            Producto("Auriculares", R.drawable.ic_launcher_foreground),
            Producto("Smartwatch", R.drawable.ic_launcher_foreground)
        )

        recyclerView.adapter = ProductoAdapter(productos)
    }

    class ProductoAdapter(private val lista: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val img: ImageView = view.findViewById(R.id.imgProducto)
            val nombre: TextView = view.findViewById(R.id.tvNombreProducto)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.elemento_producto, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val prod = lista[position]
            holder.nombre.text = prod.nombre
            holder.img.setImageResource(prod.imagen)
        }

        override fun getItemCount() = lista.size
    }
}
