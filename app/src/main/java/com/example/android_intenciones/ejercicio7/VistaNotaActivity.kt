package com.example.android_intenciones.ejercicio7

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class VistaNotaActivity : AppCompatActivity() {
    private lateinit var nota: Nota
    private lateinit var tvTitulo: TextView
    private lateinit var tvContenido: TextView

    private val editarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val actualizada = result.data?.getSerializableExtra("nota") as? Nota
            if (actualizada != null) {
                nota = actualizada
                tvTitulo.text = nota.titulo
                tvContenido.text = nota.contenido
                val intent = Intent().apply {
                    putExtra("nota", nota)
                }
                setResult(RESULT_OK, intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_nota)

        tvTitulo = findViewById(R.id.tvVistaTituloNota)
        tvContenido = findViewById(R.id.tvVistaContenidoNota)

        nota = intent.getSerializableExtra("nota") as Nota
        tvTitulo.text = nota.titulo
        tvContenido.text = nota.contenido
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_vista_nota, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.accion_editar_nota -> {
                val intent = Intent(this, EdicionNotaActivity::class.java).apply {
                    putExtra("nota", nota)
                }
                editarLauncher.launch(intent)
                true
            }
            R.id.accion_compartir_nota -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${nota.titulo}\n\n${nota.contenido}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
