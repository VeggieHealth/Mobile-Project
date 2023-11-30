package com.example.veggiehealth

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.veggiehealth.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()
//        supportActionBar?.apply {
//            supportActionBar?.title = "Veggie Health"
//            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#79AC78")))
//        }
    }
//    fun setActionBarTitle(title: String) {
//        supportActionBar?.title = title
//    }
}