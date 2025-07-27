package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KonsultasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_konsultasi)
        val txtKonsul = findViewById<TextView>(R.id.txtkonsul)

        txtKonsul.setOnClickListener {
            val intent = Intent(this, DetailKonsultasiActivity::class.java)
            startActivity(intent)
        }
    }
}