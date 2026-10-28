package com.example.android_intenciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_intenciones.ejercicio1.Ejercicio1Activity
import com.example.android_intenciones.ejercicio2.Ejercicio2Activity
import com.example.android_intenciones.ejercicio3.Ejercicio3Activity
import com.example.android_intenciones.ejercicio4.Ejercicio4Activity
import com.example.android_intenciones.ejercicio5.Ejercicio5Activity
import com.example.android_intenciones.ejercicio6.Ejercicio6Activity
import com.example.android_intenciones.ejercicio7.ListaNotasActivity
import com.example.android_intenciones.ejercicio8.Ejercicio8Activity
import com.example.android_intenciones.ejercicio9.Ejercicio9Activity
import com.example.android_intenciones.ejercicio10.Ejercicio10Activity
import com.example.android_intenciones.ejercicio11.Ejercicio11Activity
import com.example.android_intenciones.ejercicio12.Ejercicio12Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnEj1).setOnClickListener { startActivity(Intent(this, Ejercicio1Activity::class.java)) }
        findViewById<Button>(R.id.btnEj2).setOnClickListener { startActivity(Intent(this, Ejercicio2Activity::class.java)) }
        findViewById<Button>(R.id.btnEj3).setOnClickListener { startActivity(Intent(this, Ejercicio3Activity::class.java)) }
        findViewById<Button>(R.id.btnEj4).setOnClickListener { startActivity(Intent(this, Ejercicio4Activity::class.java)) }
        findViewById<Button>(R.id.btnEj5).setOnClickListener { startActivity(Intent(this, Ejercicio5Activity::class.java)) }
        findViewById<Button>(R.id.btnEj6).setOnClickListener { startActivity(Intent(this, Ejercicio6Activity::class.java)) }
        findViewById<Button>(R.id.btnEj7).setOnClickListener { startActivity(Intent(this, ListaNotasActivity::class.java)) }
        findViewById<Button>(R.id.btnEj8).setOnClickListener { startActivity(Intent(this, Ejercicio8Activity::class.java)) }
        findViewById<Button>(R.id.btnEj9).setOnClickListener { startActivity(Intent(this, Ejercicio9Activity::class.java)) }
        findViewById<Button>(R.id.btnEj10).setOnClickListener { startActivity(Intent(this, Ejercicio10Activity::class.java)) }
        findViewById<Button>(R.id.btnEj11).setOnClickListener { startActivity(Intent(this, Ejercicio11Activity::class.java)) }
        findViewById<Button>(R.id.btnEj12).setOnClickListener { startActivity(Intent(this, Ejercicio12Activity::class.java)) }
    }
}
