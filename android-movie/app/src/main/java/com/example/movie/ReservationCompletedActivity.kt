package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.movie.databinding.ActivityReservationCompletedBinding
import com.example.movie.viewModel.MainViewModel
import java.time.LocalDateTime

class ReservationCompletedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationCompletedBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_completed)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }

        val movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE) ?: "Unknown movieTitle"
        val theaterName = intent.getStringExtra(EXTRA_THEATER_NAME) ?: "Unknown theaterName"
        val ticketNumber = intent.getIntExtra(EXTRA_TICKET_NUMBER, 1)
        val nowDateTime = LocalDateTime.now()

        viewModel.setReservationDetails(movieTitle, theaterName, ticketNumber, nowDateTime)

        viewModel.saveReservationToPref()

        viewModel.toastMessage.observe(
            this,
            Observer {
                    message ->
                message?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    companion object {
        private const val EXTRA_MOVIE_TITLE = "movie_title"
        private const val EXTRA_THEATER_NAME = "theater_name"
        private const val EXTRA_TICKET_NUMBER = "ticket_number"

        fun createIntent(
            context: Context,
            movieTitle: String,
            theaterName: String,
            ticketNumber: Int? = null,
        ): Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TITLE, movieTitle)
                putExtra(EXTRA_THEATER_NAME, theaterName)
                ticketNumber?.let { putExtra(EXTRA_TICKET_NUMBER, it) }
            }
        }
    }
}
