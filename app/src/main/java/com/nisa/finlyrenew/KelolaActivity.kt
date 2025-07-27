package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KelolaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_kelola)

        val btnback = findViewById<ImageView>(R.id.btn_back)

        btnback.setOnClickListener {
            finish()

        }
        val btnTambah = findViewById<Button>(R.id.btn_tambah_transaksi)

        btnTambah.setOnClickListener {
            val intent = Intent(this, KelolaDetailActivity::class.java)
            startActivity(intent)
        }

    }
}