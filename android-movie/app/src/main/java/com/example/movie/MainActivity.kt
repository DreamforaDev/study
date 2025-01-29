package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigationView()

        if (savedInstanceState == null) {
            handleIntent(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val targetFragment = intent?.getStringExtra(EXTRA_TARGET_FRAGMENT)
        Log.d("MainActivity", "Handling intent with EXTRA_TARGET_FRAGMENT: $targetFragment")

        if (targetFragment == FRAGMENT_RESERVATION_DETAILS) {
            Log.d("MainActivity", "Navigating to ReservationDetailsFragment")
            binding.bottomNavView.selectedItemId = R.id.reservation_details
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, ReservationDetailsFragment())
                .commit()
        } else {
            Log.d("MainActivity", "Navigating to MovieListFragment")
            binding.bottomNavView.selectedItemId = R.id.home
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MovieListFragment())
                .commit()
        }
    }

    private fun setBottomNavigationView() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, MovieListFragment())
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

    companion object {
        const val EXTRA_TARGET_FRAGMENT = "target_fragment"
        const val FRAGMENT_RESERVATION_DETAILS = "reservation_details"
    }
}
