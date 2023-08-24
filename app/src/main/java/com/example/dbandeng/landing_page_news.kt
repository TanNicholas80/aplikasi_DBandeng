package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.modul.ModulNews

class landing_page_news : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var modulNewsDump: ModulNews? = null
    var NewsArrayList = ArrayList<ModulNews>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_news)
        // Setup Meow Button
        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_not_active))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.product_icon))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.news_active))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.profile_user))
        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    val landing_page_layout = Intent(this@landing_page_news, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }

                2 -> {
                    val product_page_layout = Intent(this@landing_page_news, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }

                3 -> {
                    val news_page_layout = Intent(this@landing_page_news, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }

                4 -> {
                    val profile_user_layout = Intent(this@landing_page_news, landing_page_profile::class.java);
                    startActivity(profile_user_layout)
                }
            }
        }
        // Setup Recyclerview
        val modulNewsDump = ModulNews("1", "Kampung UMKM Bandeng", "Kampung UMKM Bandeng merupakan sebuah UMKM", "18/10/2021")
        for (i in 0..14) {
            NewsArrayList.add(modulNewsDump)
        }
        recyclerView = findViewById(R.id.news_recycle)
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        val adaptorNews = landing_AdaptorNews(NewsArrayList)
        recyclerView?.setAdapter(adaptorNews)
        // setup carousel
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.news1))
        imageList.add(SlideModel(R.drawable.news2))
        imageList.add(SlideModel(R.drawable.news3))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
    }
}