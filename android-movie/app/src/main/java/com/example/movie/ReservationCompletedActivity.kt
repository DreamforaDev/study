package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityReservationCompletedBinding
import com.example.movie.model.Reservation


class ReservationCompletedActivity : AppCompatActivity(),ReservationCompletedContract.View {

    private lateinit var binding: ActivityReservationCompletedBinding
    private lateinit var presenter: ReservationCompletedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ReservationCompletedPresenter(this,this)

        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }

        val movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE) ?: "Unknown movieTitle"
        val theaterName = intent.getStringExtra(EXTRA_THEATER_NAME) ?: "Unknown theaterName"
        val ticketNumber = intent.getIntExtra(EXTRA_TICKET_NUMBER, 1)

        presenter.saveReservation(movieTitle,theaterName,ticketNumber)

        presenter.getReservationDetails(movieTitle,theaterName,ticketNumber)
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
        ):
                Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TITLE, movieTitle)
                putExtra(EXTRA_THEATER_NAME, theaterName)
                ticketNumber?.let { putExtra(EXTRA_TICKET_NUMBER, it) }
            }
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun displayReservationDetails(reservation: Reservation) {
        binding.tvTitle.text = reservation.movieTitle
        binding.tvTheater.text = reservation.theaterName
        binding.tvMoviegoer.text = getString(R.string.ticket_count_format, reservation.ticketNumber)
        binding.tvTicketsPrice.text = getString(R.string.ticket_price_format, reservation.ticketPrice)
        binding.tvReservationDateTime.text = getString(
            R.string.reservation_dateTime,
            reservation.formattedDate,
            reservation.formattedTime
        )
    }
}
