package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPembayaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail_pembayaran)
        val btnBayar = findViewById<Button>(R.id.btnBayar)

        btnBayar.setOnClickListener {
            val intent = Intent(this, DetailPembayaranSuksesActivity:: class.java)
            startActivity(intent)
        }
    }
}