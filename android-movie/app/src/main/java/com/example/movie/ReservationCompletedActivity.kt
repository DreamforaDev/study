package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movie.databinding.ActivityReservationCompletedBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationCompletedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservationCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }

        val movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        val theaterName = intent.getStringExtra(EXTRA_THEATER_NAME)
        val ticketNumber = intent.getIntExtra(EXTRA_TICKET_NUMBER, 1)
        val ticketPrice = ticketNumber * 10_000

        binding.tvTitle.text = movieTitle
        binding.tvTheater.text = theaterName

        binding.tvMoviegoer.text = getString(R.string.ticket_count_format, ticketNumber)
        binding.tvTicketsPrice.text = getString(R.string.ticket_price_format, ticketPrice)

        val nowDate: LocalDate = LocalDate.now()
        val nowTime: LocalTime = LocalTime.now()

        val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.getDefault())
        val timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.getDefault())
        val formattedDate = nowDate.format(dateFormatter)
        val formattedTime = nowTime.format(timeFormatter)

        binding.tvReservationDateTime.text =
            getString(R.string.reservation_dateTime, formattedDate, formattedTime)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_completed_root)) { reservationCompletedView, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            reservationCompletedView.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    companion object {
        private const val EXTRA_MOVIE_TITLE = "movie_title"
        private const val EXTRA_THEATER_NAME = "theater_name"
        private const val EXTRA_TICKET_NUMBER = "ticket_number"
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val TIME_FORMAT = "HH:mm"

        fun createIntent(
            context: Context,
            movieTitle: String,
            theaterName: String,
            ticketNumber: Int? = null
        ):
                Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TITLE, movieTitle)
                putExtra(EXTRA_THEATER_NAME, theaterName)
                ticketNumber?.let { putExtra(EXTRA_TICKET_NUMBER, it) }
            }
        }
    }
}
