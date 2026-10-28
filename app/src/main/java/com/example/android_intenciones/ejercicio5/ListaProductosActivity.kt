package com.example.android_intenciones.ejercicio5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intenciones.R
import java.io.Serializable

data class ProductoComercial(val nombre: String, val precio: Double) : Serializable

class ListaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj5)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productos = listOf(
            ProductoComercial("Camisa", 25.0),
            ProductoComercial("Pantalón", 40.0),
            ProductoComercial("Zapatos", 60.0),
            ProductoComercial("Gorra", 15.0)
        )

        recyclerView.adapter = ProductoComercialAdapter(productos) { prod ->
            val resultIntent = Intent().apply {
                putExtra("producto", prod)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    class ProductoComercialAdapter(
        private val lista: List<ProductoComercial>,
        private val onClick: (ProductoComercial) -> Unit
    ) : RecyclerView.Adapter<ProductoComercialAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv: TextView = view.findViewById(R.id.tvNombreItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nombre, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val prod = lista[position]
            holder.tv.text = "${prod.nombre} - $${prod.precio}"
            holder.itemView.setOnClickListener { onClick(prod) }
        }

        override fun getItemCount() = lista.size
    }
}
