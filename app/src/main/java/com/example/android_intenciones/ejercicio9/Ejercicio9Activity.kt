package com.example.android_intenciones.ejercicio9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class Ejercicio9Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio9)

        val etTexto = findViewById<EditText>(R.id.etTextoCompartir)
        val btnCompartir = findViewById<Button>(R.id.btnCompartirTexto)

        btnCompartir.setOnClickListener {
            val texto = etTexto.text.toString()
            if (texto.isNotEmpty()) {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, texto)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}
