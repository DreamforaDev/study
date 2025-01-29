package com.example.movie.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.ReservationDetailsAdapter
import com.example.movie.model.ReservedMovie
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("app:items")
fun setItems(
    recyclerView: RecyclerView,
    items: List<ReservedMovie>?,
) {
    val adapter = recyclerView.adapter as? ReservationDetailsAdapter
    adapter?.submitList(items ?: emptyList())
}

private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

@BindingAdapter("app:formattedDateTime")
fun bindFormattedDateTime(
    view: TextView,
    dateTime: LocalDateTime,
) {
    dateTime.let {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)
        view.text = it.format(formatter)
    }
}
