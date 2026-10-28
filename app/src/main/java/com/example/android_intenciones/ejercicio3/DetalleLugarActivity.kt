package com.example.android_intenciones.ejercicio3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class DetalleLugarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_lugar)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val descripcion = intent.getStringExtra("descripcion") ?: ""
        val imagen = intent.getIntExtra("imagen", R.drawable.ic_launcher_foreground)

        findViewById<TextView>(R.id.tvDetalleNombre).text = nombre
        findViewById<TextView>(R.id.tvDetalleDesc).text = descripcion
        findViewById<ImageView>(R.id.imgDetalleLugar).setImageResource(imagen)
    }
}
