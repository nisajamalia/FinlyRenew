package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nisa.finlyrenew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment()
                addFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_komunitas -> {
                val komunitasFragment = KomunitasFragment()
                addFragment(komunitasFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_pengajuan -> {
                // BUKA ACTIVITY untuk Pengajuan
                val intent = Intent(this, PengajuanActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener false
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fm_main, fragment, fragment::class.java.simpleName)
            .addToBackStack(null).commit()
    }
    val defaultMainView = HomeFragment.defaultFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val navMain = findViewById<BottomNavigationView>(R.id.nav_main)
        navMain.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        addFragment(defaultMainView)
    }

}