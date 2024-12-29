package com.example.movie

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewAdapter = RecyclerViewAdapter()

        val movieList : List<Movie> = listOf(
            Movie(R.drawable.poster_1,"해리포터와 마법의 돌","2001-01-10~2001-03-01","180분"),
            Movie(R.drawable.poster_2,"해리포터와 비밀의 방","2002-01-10~2002-03-01","180분"),
            Movie(R.drawable.poster_3,"해리포터와 아즈카반의 죄수","2004-01-10~2004-03-01","180분"),
            Movie(R.drawable.poster_4,"해리포터와 불의 잔","2005-01-10~2005-03-01","180분"),
            Movie(R.drawable.poster_5,"해리포터와 불사조 기사단","2007-01-10~2007-03-01","180분"),
            Movie(R.drawable.poster_6,"해리포터와 혼혈 왕자","2009-01-10~2009-03-01","180분"),
            Movie(R.drawable.poster_7,"해리포터와 죽음의 성물 I","2010-01-10~2010-03-01","180분"),
            Movie(R.drawable.poster_8,"해리포터와 죽음의 성물 II","2011-01-10~2011-03-01","180분")
        )

        //val adList : List<Ads> = listOf(
//            Ads(R.drawable.ic_launcher_background,1)
//        )

        recyclerViewAdapter.submitList(movieList.toMutableList())
        binding.mainRecyclerview.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = recyclerViewAdapter
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}