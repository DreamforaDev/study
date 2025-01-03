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

    val movieDetailList = listOf(
        "친척집에서 구박받는 생활을 하던 해리는 11살 생일을 앞두고 호그와트 마법학교로부터 입학초대장을 받고 자신이 마법사라는 " +
                "사실을 알게 된다. 해리는 호그와트 마법학교로 가는 열차에서 친구 론과 헤르미온느를 사귀고 함께 마법과 신비, " +
                "모험으로 가득한 학교생활을 시작한다.",
        "도비는 해리에게 호그와트에 돌아가면 위험한 일을 당할 것이라 경고하며 그가 학교로 가는 것을 방해한다. " +
                "하지만 해리는 론과 함께 하늘을 나는 차를 타고 호그와트로 떠나고, 곧 의문의 사건들을 마주한다.",
        "아즈카반의 죄수 시리우스 블랙의 탈옥으로 인해 마법 세계는 혼란과 두려움에 빠지고, 이내 시리우스는 12년 동안 준비해온" +
                " 복수를 위해 호그와트로 향한다. 한편, 해리 포터는 과거 시리우스 블랙의 정체를 알게 되고 시리우스가 자신을 찾아오기만을 기다린다.",
        "명성이 자자한 세 곳의 마법 학교에서 선정된 뛰어난 마법사들이 경쟁을 펼치는 트리위저드 대회가 개최되고," +
                " 예상치 못했던 해리의 이름이 불의 잔에 의해서 불린다. 한편, 오랜 세월 그 종적을 감추었던 악의 세력이 " +
                "돌아올지도 모른다는 불길한 징조가 나타나고, 해리와 친구들의 위험천만한 모험이 펼쳐진다.",
        "호그와트의 방학이 끝나기만을 기다리던 해리는 머글을 상대로 마법을 써서 퇴학당했다는 편지를 받는다. " +
                "이후 오러들에게 이끌려 불사조 기사단의 집결지에 도착한 해리는 법정에서 자신의 결백을 주장하고, " +
                "볼드모트가 돌아왔다는 사실을 알리지만 사람들은 이를 믿지 않는다.",
        "덤블도어 교수는 볼드모트가 힘을 키우며 세상을 위협하자 해리와 함께 볼드모트를 불멸의 존재로 만든 호크룩스 7개를 찾는 여정을 시작한다." +
                " 덤블도어는 호크룩스에 대한 단서를 기억 속에서 찾기 위해 슬러그혼 교수를 초청한다. 한편, 론의 여자친구가 된 " +
                "라벤더는 헤르미온느를 경계하고, 세 사람 사이에는 묘한 기류가 흐르기 시작한다.",
        "불사조 기사단은 한층 더 강해진 볼드모트와 죽음을 먹는 자들로부터 해리를 지켜내기 위해 분투한다. " +
                "한편 해리와 론 그리고 헤르미온느를 찾아온 마법부 장관 루퍼스 스크림저는 세 사람에게 덤블도어가 남긴 유품을 전해준다." +
                " 한편 빌과 플뢰르의 결혼식은 죽음을 먹는 자들의 습격으로 아수라장이 되고, 가까스로 피신한 해리와 친구들은 " +
                "볼드모트의 호크룩스 중 하나인 로켓을 찾아 떠난다.",
        "해리와 론, 헤르미온느는 볼드모트를 불멸로 만들어주는 다섯 번째 호크룩스를 찾기 위해 호그와트로 돌아온다. " +
                "이후 자신의 호크룩스들이 파괴되었다는 사실을 눈치챈 볼드모트는 죽음을 먹는 자들과 함께 호그와트로 향한다. " +
                "마법사들의 전쟁터가 되어버린 호그와트에서 해리는 싸움을 끝낼 수 있는 마지막 호크룩스에 대한 실마리를 얻는다."
    )

    private var ticketsCount: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        when (val seriesNumber = intent.getIntExtra(EXTRA_SERIES_NUMBER, -1)) {
            1 -> binding.tvMovieDetail.text = movieDetailList[0]
            2 -> binding.tvMovieDetail.text = movieDetailList[1]
            3 -> binding.tvMovieDetail.text = movieDetailList[2]
            4 -> binding.tvMovieDetail.text = movieDetailList[3]
            5 -> binding.tvMovieDetail.text = movieDetailList[4]
            6 -> binding.tvMovieDetail.text = movieDetailList[5]
            7 -> binding.tvMovieDetail.text = movieDetailList[6]
            8 -> binding.tvMovieDetail.text = movieDetailList[7]
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
