package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movie.databinding.ActivityReservationCompletedBinding
import com.example.movie.viewModel.ReservationCompletedViewModel
import java.time.LocalDateTime

class ReservationCompletedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationCompletedBinding
    private val viewModel: ReservationCompletedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_completed)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.toolbar.btnBack.setOnClickListener {
            val intent =
                Intent(this, MainActivity::class.java).apply {
                    putExtra(MainActivity.EXTRA_TARGET_FRAGMENT, MainActivity.FRAGMENT_RESERVATION_DETAILS)
                }
            Log.d(
                "ReservationCompletedActivity",
                "Sending intent with EXTRA_TARGET_FRAGMENT: ${MainActivity.FRAGMENT_RESERVATION_DETAILS}",
            )

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        val movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE) ?: "Unknown movieTitle"
        val theaterName = intent.getStringExtra(EXTRA_THEATER_NAME) ?: "Unknown theaterName"
        val ticketNumber = intent.getIntExtra(EXTRA_TICKET_NUMBER, 1)
        val nowDateTime = intent.getStringExtra(EXTRA_DATE_TIME) ?: "Unknown dateTime"
        val localDateTime: LocalDateTime = nowDateTime.let { LocalDateTime.parse(it) }

        viewModel.saveReservationDetails(movieTitle, theaterName, ticketNumber, localDateTime)

        viewModel.toastMessage.observe(
            this,
        ) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val EXTRA_MOVIE_TITLE = "movie_title"
        private const val EXTRA_THEATER_NAME = "theater_name"
        private const val EXTRA_TICKET_NUMBER = "ticket_number"
        private const val EXTRA_DATE_TIME = "date_time"

        fun createIntent(
            context: Context,
            movieTitle: String,
            theaterName: String,
            ticketNumber: Int? = 1,
            nowDateTime: String,
        ): Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TITLE, movieTitle)
                putExtra(EXTRA_THEATER_NAME, theaterName)
                ticketNumber?.let { putExtra(EXTRA_TICKET_NUMBER, it) }
                putExtra(EXTRA_DATE_TIME, nowDateTime)
            }
        }
    }
}
