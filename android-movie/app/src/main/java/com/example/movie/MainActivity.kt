package com.example.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigationView()

        if(savedInstanceState == null){
            binding.bottomNavView.selectedItemId = R.id.home
        }
    }

    fun setBottomNavigationView() {

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.reservation_details -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, ReservationDetailsFragment()).commit()
                    true
                }

                R.id.setting -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, SettingFragment())
                        .commit()
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

}
