package com.example.android_intenciones.ejercicio3

import android.content.Intent
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

data class Lugar(val nombre: String, val descripcion: String, val imagen: Int)

class Ejercicio3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj3)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val lugares = listOf(
            Lugar("Parque Central", "Hermoso parque en el centro de la ciudad con fuentes y árboles.", R.drawable.ic_launcher_foreground),
            Lugar("Museo de Arte", "Exhibiciones permanentes de arte moderno y clásico.", R.drawable.ic_launcher_foreground),
            Lugar("Mirador del Valle", "Vista panorámica espectacular de todo el valle.", R.drawable.ic_launcher_foreground)
        )

        recyclerView.adapter = LugarAdapter(lugares) { lugar ->
            val intent = Intent(this, DetalleLugarActivity::class.java).apply {
                putExtra("nombre", lugar.nombre)
                putExtra("descripcion", lugar.descripcion)
                putExtra("imagen", lugar.imagen)
            }
            startActivity(intent)
        }
    }

    class LugarAdapter(
        private val lista: List<Lugar>,
        private val onClick: (Lugar) -> Unit
    ) : RecyclerView.Adapter<LugarAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val img: ImageView = view.findViewById(R.id.imgLugar)
            val nombre: TextView = view.findViewById(R.id.tvNombreLugar)
            val desc: TextView = view.findViewById(R.id.tvDescLugar)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lugar, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val lugar = lista[position]
            holder.nombre.text = lugar.nombre
            holder.desc.text = lugar.descripcion
            holder.img.setImageResource(lugar.imagen)
            holder.itemView.setOnClickListener { onClick(lugar) }
        }

        override fun getItemCount() = lista.size
    }
}
