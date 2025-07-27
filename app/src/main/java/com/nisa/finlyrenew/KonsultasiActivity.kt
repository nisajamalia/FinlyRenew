package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nisa.finlyrenew.databinding.ActivityKonsultasiBinding

class KonsultasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKonsultasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityKonsultasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup back button to go to HomeActivity
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish() // Close current activity
        }

        // Handle consultation click
        binding.txtkonsul.setOnClickListener {
            startActivity(Intent(this, DetailKonsultasiActivity::class.java))
        }
    }
}