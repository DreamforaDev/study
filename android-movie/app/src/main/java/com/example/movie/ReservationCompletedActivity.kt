package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movie.databinding.ActivityReservationCompletedBinding
import org.json.JSONArray
import org.json.JSONObject
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

        val formattedDate = getCurrentFormattedDate()
        val formattedTime = getCurrentFormattedTime()

        binding.tvReservationDateTime.text =
            getString(R.string.reservation_dateTime, formattedDate, formattedTime)

        saveData()

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

    private fun getCurrentFormattedDate(): String {

        val calendar = java.util.Calendar.getInstance()
        val dateFormatter = java.text.SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormatter.format(calendar.time)
    }

    private fun getCurrentFormattedTime(): String {
        val calendar = java.util.Calendar.getInstance()
        val timeFormatter = java.text.SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return timeFormatter.format(calendar.time)

    }

    private fun saveData() {
        val sharedPreferences =
            getSharedPreferences(PreferenceKeys.SHARED_PREFERENCE_NAME.key, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()

        val existingDataJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val existingDataList = JSONArray(existingDataJson)

        val newReservation = JSONObject().apply {
            put(PreferenceKeys.DATE.key, getCurrentFormattedDate())
            put(PreferenceKeys.TIME.key, getCurrentFormattedTime())
            put(
                PreferenceKeys.THEATER_NAME.key,
                intent.getStringExtra(EXTRA_THEATER_NAME) ?: "Unknown theater"
            )
            put(
                PreferenceKeys.MOVIE_TITLE.key,
                intent.getStringExtra(EXTRA_MOVIE_TITLE) ?: "Unknown title"
            )
        }

        existingDataList.put(newReservation)

        Log.d("ReservationCompleted", "Updated Data List: $existingDataList")
        Toast.makeText(this,"예매 성공!",Toast.LENGTH_SHORT).show()

        edit.putString(PreferenceKeys.MOVIES_LIST.key, existingDataList.toString())
        edit.apply()
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
}
