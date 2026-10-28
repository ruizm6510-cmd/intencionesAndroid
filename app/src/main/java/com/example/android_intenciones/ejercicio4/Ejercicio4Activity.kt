package com.example.android_intenciones.ejercicio4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intenciones.R

class Ejercicio4Activity : AppCompatActivity() {
    private val listaItems = mutableListOf("Elemento inicial 1", "Elemento inicial 2")
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        val etNuevo = findViewById<EditText>(R.id.etNuevoItem)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarItem)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj4)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(listaItems) { itemText ->
            Toast.makeText(this, "Seleccionado: $itemText", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        btnAgregar.setOnClickListener {
            val text = etNuevo.text.toString().trim()
            if (text.isNotEmpty()) {
                listaItems.add(text)
                adapter.notifyItemInserted(listaItems.size - 1)
                etNuevo.setText("")
            }
        }
    }

    class ItemAdapter(
        private val lista: List<String>,
        private val onClick: (String) -> Unit
    ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvText: TextView = view.findViewById(R.id.tvNombreItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nombre, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = lista[position]
            holder.tvText.text = item
            holder.itemView.setOnClickListener { onClick(item) }
        }

        override fun getItemCount() = lista.size
    }
}
