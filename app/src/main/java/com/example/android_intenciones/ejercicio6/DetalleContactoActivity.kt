package com.example.android_intenciones.ejercicio6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class DetalleContactoActivity : AppCompatActivity() {
    private lateinit var contacto: Contacto
    private lateinit var tvNombre: TextView
    private lateinit var tvTelefono: TextView

    private val editarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val actualizado = result.data?.getSerializableExtra("contacto") as? Contacto
            if (actualizado != null) {
                contacto = actualizado
                tvNombre.text = contacto.nombre
                tvTelefono.text = contacto.telefono
                val intent = Intent().apply {
                    putExtra("contacto", contacto)
                    putExtra("accion", "editar")
                }
                setResult(RESULT_OK, intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contacto)

        tvNombre = findViewById(R.id.tvDetalleNombreContacto)
        tvTelefono = findViewById(R.id.tvDetalleTelefonoContacto)

        contacto = intent.getSerializableExtra("contacto") as Contacto
        tvNombre.text = contacto.nombre
        tvTelefono.text = contacto.telefono
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_contacto, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.accion_editar_contacto -> {
                val intent = Intent(this, EditarContactoActivity::class.java).apply {
                    putExtra("contacto", contacto)
                }
                editarLauncher.launch(intent)
                true
            }
            R.id.accion_eliminar_contacto -> {
                val intent = Intent().apply {
                    putExtra("contacto", contacto)
                    putExtra("accion", "eliminar")
                }
                setResult(RESULT_OK, intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
