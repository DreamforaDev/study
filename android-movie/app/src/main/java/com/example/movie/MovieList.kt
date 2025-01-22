package com.example.movie

import com.example.movie.model.ListItem

object MovieList {

    val movieList = listOf(
        ListItem.MovieItem(
            1,
            R.drawable.poster_1,
            "해리포터와 마법의 돌",
            "2001-01-10~2001-03-01",
            "150분"
        ),
        ListItem.MovieItem(
            2,
            R.drawable.poster_2,
            "해리포터와 비밀의 방",
            "2002-01-10~2002-03-01",
            "180분"
        ),
        ListItem.MovieItem(
            3,
            R.drawable.poster_3,
            "해리포터와 아즈카반의 죄수",
            "2004-01-10~2004-03-01",
            "200분"
        ),
        ListItem.AdItem(R.drawable.baemin_ad, "https://www.dreamfora.com/"),
        ListItem.MovieItem(
            4,
            R.drawable.poster_4,
            "해리포터와 불의 잔",
            "2005-01-10~2005-03-01",
            "198분"
        ),
        ListItem.MovieItem(
            5,
            R.drawable.poster_5,
            "해리포터와 불사조 기사단",
            "2007-01-10~2007-03-01",
            "176분"
        ),
        ListItem.MovieItem(
            6,
            R.drawable.poster_6,
            "해리포터와 혼혈 왕자",
            "2009-01-10~2009-03-01",
            "167분"
        ),
        ListItem.AdItem(R.drawable.toss_ad, "https://www.dreamfora.com/"),
        ListItem.MovieItem(
            7,
            R.drawable.poster_7,
            "해리포터와 죽음의 성물 I",
            "2010-01-10~2010-03-01",
            "160분"
        ),
        ListItem.MovieItem(
            8,
            R.drawable.poster_8,
            "해리포터와 죽음의 성물 II",
            "2011-01-10~2011-03-01",
            "190분"
        )
    )
}
