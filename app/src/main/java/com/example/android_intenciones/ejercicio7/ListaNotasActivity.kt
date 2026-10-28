package com.example.android_intenciones.ejercicio7

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

class ListaNotasActivity : AppCompatActivity() {
    private val listaNotas = mutableListOf(
        Nota(1, "Lista de compras", "Leche, pan, huevos, café"),
        Nota(2, "Reunión de proyecto", "Revisar avances de Android a las 4 PM")
    )
    private lateinit var adapter: NotaAdapter

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val nota = result.data?.getSerializableExtra("nota") as? Nota
            if (nota != null) {
                val index = listaNotas.indexOfFirst { it.id == nota.id }
                if (index >= 0) {
                    listaNotas[index] = nota
                } else {
                    listaNotas.add(nota)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEj7)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NotaAdapter(listaNotas) { nota ->
            val intent = Intent(this, VistaNotaActivity::class.java).apply {
                putExtra("nota", nota)
            }
            launcher.launch(intent)
        }
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_notas, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_nueva_nota) {
            val intent = Intent(this, EdicionNotaActivity::class.java)
            launcher.launch(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class NotaAdapter(
        private val lista: List<Nota>,
        private val onClick: (Nota) -> Unit
    ) : RecyclerView.Adapter<NotaAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv: TextView = view.findViewById(R.id.tvNombreItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nombre, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val nota = lista[position]
            holder.tv.text = nota.titulo
            holder.itemView.setOnClickListener { onClick(nota) }
        }

        override fun getItemCount() = lista.size
    }
}
