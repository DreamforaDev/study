package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movie.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    private lateinit var movieTitle: String

    companion object {
        private const val ARG_MOVIE_TITLE = "movie_title"

        fun newInstance(movieTitle: String): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putString(ARG_MOVIE_TITLE, movieTitle)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieTitle = it.getString(ARG_MOVIE_TITLE, "Unknown movie title.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        val theaterName1 = binding.tvTheater1.text.toString()
        val theaterName2 = binding.tvTheater2.text.toString()
        val theaterName3 = binding.tvTheater3.text.toString()

        binding.cvTheater1.setOnClickListener {
            navigationToCompletedPage(theaterName1)
        }

        binding.cvTheater2.setOnClickListener {
            navigationToCompletedPage(theaterName2)
        }

        binding.cvTheater3.setOnClickListener {
            navigationToCompletedPage(theaterName3)
        }
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.RoundBottomSheetDialogTheme
    }

    private fun navigationToCompletedPage(theaterName: String) {
        val intent =
            ReservationCompletedActivity.createIntent(requireContext(), movieTitle, theaterName)
        startActivity(intent)
        dismiss()
    }
}
