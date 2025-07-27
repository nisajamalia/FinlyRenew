package com.nisa.finlyrenew

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KelolaDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_kelola_detail)

        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }
}