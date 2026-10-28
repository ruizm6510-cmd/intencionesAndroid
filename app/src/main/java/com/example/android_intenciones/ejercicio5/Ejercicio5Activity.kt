package com.example.android_intenciones.ejercicio5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class Ejercicio5Activity : AppCompatActivity() {
    private lateinit var tvSeleccionado: TextView

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val prod = result.data?.getSerializableExtra("producto") as? ProductoComercial
            if (prod != null) {
                tvSeleccionado.text = "Seleccionado: ${prod.nombre} ($${prod.precio})"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        tvSeleccionado = findViewById(R.id.tvProductoSeleccionado)
        val btnSeleccionar = findViewById<Button>(R.id.btnSeleccionarProducto)

        btnSeleccionar.setOnClickListener {
            val intent = Intent(this, ListaProductosActivity::class.java)
            launcher.launch(intent)
        }
    }
}
