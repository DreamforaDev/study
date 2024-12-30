package com.example.movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    val movieList = listOf(
        ListItem.MovieItem(1,R.drawable.poster_1,"해리포터와 마법의 돌","상영일 : 2001-01-10~2001-03-01","러닝타임 : 180분"),
        ListItem.MovieItem(2,R.drawable.poster_2,"해리포터와 비밀의 방","상영일 : 2002-01-10~2002-03-01","러닝타임 : 180분"),
        ListItem.MovieItem(3,R.drawable.poster_3,"해리포터와 아즈카반의 죄수","상영일 : 2004-01-10~2004-03-01","러닝타임 : 180분"),
        ListItem.AdItem(R.drawable.baemin_ad,"https://www.dreamfora.com/"),
        ListItem.MovieItem(4, R.drawable.poster_4,"해리포터와 불의 잔","상영일 : 2005-01-10~2005-03-01","러닝타임 : 180분"),
        ListItem.MovieItem(5, R.drawable.poster_5,"해리포터와 불사조 기사단","상영일 : 2007-01-10~2007-03-01","러닝타임 : 180분"),
        ListItem.MovieItem(6, R.drawable.poster_6,"해리포터와 혼혈 왕자","상영일 : 2009-01-10~2009-03-01","러닝타임 : 180분"),
        ListItem.AdItem(R.drawable.toss_ad,"https://www.dreamfora.com/"),
        ListItem.MovieItem(7, R.drawable.poster_7,"해리포터와 죽음의 성물 I","상영일 : 2010-01-10~2010-03-01","러닝타임 : 180분"),
        ListItem.MovieItem(8, R.drawable.poster_8,"해리포터와 죽음의 성물 II","상영일 : 2011-01-10~2011-03-01","러닝타임 : 180분")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewAdapter = RecyclerViewAdapter(

            onMovieClickListener = { movieItem ->
                val intent = Intent(this,MovieDetailActivity::class.java).apply {
                    putExtra("title",movieItem.title)
                    putExtra("release_time",movieItem.releaseTime)
                    putExtra("runtime",movieItem.runtime)
                    putExtra("poster",movieItem.poster)
                    putExtra("seriesNumber",movieItem.series)
                }
                startActivity(intent)
            },

            onButtonClickListener = {
                val bottomSheetFragment = BottomSheetFragment()
                bottomSheetFragment.show(supportFragmentManager,bottomSheetFragment.tag)
            },

            onAdClickListener = {

            }
        )

        recyclerViewAdapter.submitList(movieList)

        binding.mainRecyclerview.adapter = recyclerViewAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
