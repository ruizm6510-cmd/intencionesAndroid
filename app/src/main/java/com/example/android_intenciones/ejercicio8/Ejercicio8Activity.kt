package com.example.android_intenciones.ejercicio8

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class Ejercicio8Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio8)

        val btn = findViewById<Button>(R.id.btnMostrarUbicacion)
        btn.setOnClickListener {
            val uri = Uri.parse("geo:41.656313,-0.877351")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
