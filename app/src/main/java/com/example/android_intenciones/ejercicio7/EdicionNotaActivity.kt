package com.example.android_intenciones.ejercicio7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class EdicionNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_nota)

        val etTitulo = findViewById<EditText>(R.id.etTituloNota)
        val etContenido = findViewById<EditText>(R.id.etContenidoNota)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarNota)

        val nota = intent.getSerializableExtra("nota") as? Nota
        if (nota != null) {
            etTitulo.setText(nota.titulo)
            etContenido.setText(nota.contenido)
        }

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val contenido = etContenido.text.toString()
            val id = nota?.id ?: (0..1000).random()

            val resultado = Nota(id, titulo, contenido)
            val intent = Intent().apply {
                putExtra("nota", resultado)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
