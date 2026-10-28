package com.example.android_intenciones.ejercicio6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intenciones.R

class Ejercicio6Activity : AppCompatActivity() {
    private val listaContactos = mutableListOf(
        Contacto(1, "Carlos Pérez", "3111234567"),
        Contacto(2, "Ana Gómez", "3209876543")
    )
    private lateinit var adapter: ContactoAdapter

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val contacto = result.data?.getSerializableExtra("contacto") as? Contacto
            val accion = result.data?.getStringExtra("accion")

            if (contacto != null) {
                if (accion == "eliminar") {
                    listaContactos.removeAll { it.id == contacto.id }
                } else {
                    val index = listaContactos.indexOfFirst { it.id == contacto.id }
                    if (index >= 0) {
                        listaContactos[index] = contacto
                    } else {
                        listaContactos.add(contacto)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj6)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ContactoAdapter(listaContactos) { contacto ->
            val intent = Intent(this, DetalleContactoActivity::class.java).apply {
                putExtra("contacto", contacto)
            }
            launcher.launch(intent)
        }
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ejercicio6, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_nuevo_contacto) {
            val intent = Intent(this, EditarContactoActivity::class.java)
            launcher.launch(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class ContactoAdapter(
        private val lista: List<Contacto>,
        private val onClick: (Contacto) -> Unit
    ) : RecyclerView.Adapter<ContactoAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv: TextView = view.findViewById(R.id.tvNombreItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nombre, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val contacto = lista[position]
            holder.tv.text = "${contacto.nombre} - ${contacto.telefono}"
            holder.itemView.setOnClickListener { onClick(contacto) }
        }

        override fun getItemCount() = lista.size
    }
}
