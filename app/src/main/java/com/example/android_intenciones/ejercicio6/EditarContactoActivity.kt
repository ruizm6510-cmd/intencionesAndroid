package com.example.android_intenciones.ejercicio6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class EditarContactoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)

        val etNombre = findViewById<EditText>(R.id.etNombreContacto)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoContacto)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarContacto)

        val contacto = intent.getSerializableExtra("contacto") as? Contacto
        if (contacto != null) {
            etNombre.setText(contacto.nombre)
            etTelefono.setText(contacto.telefono)
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val telefono = etTelefono.text.toString()
            val id = contacto?.id ?: (0..1000).random()

            val resultado = Contacto(id, nombre, telefono)
            val intent = Intent().apply {
                putExtra("contacto", resultado)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
