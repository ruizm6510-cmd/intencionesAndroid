package com.example.android_intenciones.ejercicio1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intenciones.R

class Ejercicio1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val nombres = listOf("Ana Pérez", "Carlos Gómez", "María Rodríguez", "José Martínez", "Lucía López", "David Torres")
        recyclerView.adapter = NombresAdapter(nombres)
    }

    class NombresAdapter(private val lista: List<String>) : RecyclerView.Adapter<NombresAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvNombre: TextView = view.findViewById(R.id.tvNombreItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nombre, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvNombre.text = lista[position]
        }

        override fun getItemCount() = lista.size
    }
}
