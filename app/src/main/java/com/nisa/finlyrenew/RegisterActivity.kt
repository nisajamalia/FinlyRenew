package com.nisa.finlyrenew

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var tanggalLahirEditText: EditText
    private lateinit var jenisKelaminEditText: EditText
    private lateinit var masukTextView: TextView
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        tanggalLahirEditText = findViewById(R.id.yourTanggalLahirEditTextId)
        jenisKelaminEditText = findViewById(R.id.yourJenisKelaminEditTextId)
        masukTextView = findViewById(R.id.tvMasuk)
        registerButton = findViewById(R.id.yourRegisterButtonId)


        tanggalLahirEditText.setOnClickListener {
            showDatePicker()
        }


        jenisKelaminEditText.setOnClickListener {
            showGenderDialog()
        }

        masukTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        registerButton.setOnClickListener {
            Toast.makeText(this, "Register berhasil ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
            tanggalLahirEditText.setText(String.format("%02d-%02d-%04d", d, m + 1, y))
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showGenderDialog() {
        val genderOptions = arrayOf("Laki-laki", "Perempuan", "Lainnya")

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Pilih Jenis Kelamin")
        builder.setItems(genderOptions) { _, which ->
            jenisKelaminEditText.setText(genderOptions[which])
        }

        builder.show()
    }
}