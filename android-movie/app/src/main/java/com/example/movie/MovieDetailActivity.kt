package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movie.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding


    private var ticketsCount: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val seriesNumber = intent.getIntExtra(EXTRA_SERIES_NUMBER, -1)
        val posterResId = intent.getIntExtra(EXTRA_POSTER, -1)
        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Unknown Title"
        val releaseTime = intent.getStringExtra(EXTRA_RELEASE_TIME) ?: "Unknown Release Time"
        val runtime = intent.getStringExtra(EXTRA_RUNTIME) ?: "Unknown Runtime"

        if (posterResId != -1) {
            binding.ivPoster.setImageResource(posterResId)
        }
        binding.tvTitle.text = title
        binding.tvReleaseTime.text = releaseTime
        binding.tvRuntime.text = runtime

        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }

        val movieDetail = MovieDetailList.movieDetailList.find { it.series == seriesNumber }

        if (movieDetail != null) {
            binding.tvMovieDetail.text = movieDetail.description
        } else {
            binding.tvMovieDetail.text = "No details available."
        }

        binding.cvMinusBtn.setOnClickListener {
            if (ticketsCount > 1) {
                ticketsCount--
                updateTicketsCount()
            }
        }

        binding.cvAddBtn.setOnClickListener {
            ticketsCount++
            updateTicketsCount()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateTicketsCount() {
        binding.tvTicket.text = ticketsCount.toString()
    }

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_RELEASE_TIME = "release_time"
        private const val EXTRA_RUNTIME = "runtime"
        private const val EXTRA_POSTER = "poster"
        private const val EXTRA_SERIES_NUMBER = "seriesNumber"

        fun createIntent(
            context: Context,
            movieTitle: String,
            releaseTime: String,
            runtime: String,
            poster: Int,
            seriesNumber: Int
        ):
                Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, movieTitle)
                putExtra(EXTRA_RELEASE_TIME, releaseTime)
                putExtra(EXTRA_RUNTIME, runtime)
                putExtra(EXTRA_POSTER, poster)
                putExtra(EXTRA_SERIES_NUMBER, seriesNumber)
            }
        }
    }
}
