package com.example.android_intenciones.ejercicio12

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intenciones.R

class Ejercicio12Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        findViewById<Button>(R.id.btnEj12Llamar).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:3112545028"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnEj12Correo).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ragomez@sena.edu.co")).apply {
                putExtra(Intent.EXTRA_SUBJECT, "Asunto de prueba")
                putExtra(Intent.EXTRA_TEXT, "Cuerpo del mensaje")
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnEj12Mapa).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313,-0.877351"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnEj12Compartir).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Texto compartido desde la app de ejercicios")
            }
            startActivity(Intent.createChooser(intent, null))
        }

        findViewById<Button>(R.id.btnEj12Web).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"))
            startActivity(intent)
        }
    }
}
