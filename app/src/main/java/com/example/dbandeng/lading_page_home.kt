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
import com.example.dbandeng.adaptor.AdaptorMitra
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.adaptor.landing_AdaptorNews
import com.example.dbandeng.modul.ModulMitra
import com.example.dbandeng.modul.ModulNews
import com.example.dbandeng.modul.ModulProduk

class lading_page_home : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var ProdukArrayList = ArrayList<ModulProduk>()
    var modulNewsDump: ModulNews? = null
    var NewsArrayList = ArrayList<ModulNews>()
    var modulMitraDump: ModulMitra? = null
    var MitraArrayList = ArrayList<ModulMitra>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lading_page_home)
        // setup bottom Navigation
        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_icon_solid))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.product_icon))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.news_user))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.profile_user))
        bottomNavigation.setOnClickMenuListener {
            when(it.id) {
                1 -> {
                    val landing_page_layout = Intent(this@lading_page_home, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }
                2 -> {
                    val product_page_layout = Intent(this@lading_page_home, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }
                3 -> {
                    val news_page_layout = Intent(this@lading_page_home, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }
                4 -> {
                    val profile_user_layout = Intent(this@lading_page_home, landing_page_profile::class.java);
                    startActivity(profile_user_layout)
                }
            }
        }
        // setup image slider
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.news1))
        imageList.add(SlideModel(R.drawable.news2))
        imageList.add(SlideModel(R.drawable.news3))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider_home)

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        // setup recyclerview Product
//        val modulProdukDump = ModulProduk("1", "Babi Goreng", "Sholeh Ac", "gak halal", "18", "10","18000")
//        for (i in 0..14) {
//            ProdukArrayList.add(modulProdukDump)
//        }
        recyclerView = findViewById(R.id.recycler_produk_baru)
        val layoutManagerProduk = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerProduk
        val adaptorProduk = Adaptor_Product(ProdukArrayList)
        recyclerView?.setAdapter(adaptorProduk)
        // setup recyclerview News
//        val modulNewsDump = ModulNews("1", "Kampung UMKM Bandeng", "Kampung UMKM Bandeng merupakan sebuah UMKM","", "18/10/2021")
//        for (i in 0..14) {
//            NewsArrayList.add(modulNewsDump)
//        }
        recyclerView = findViewById(R.id.recycler_berita_terbaru)
        val layoutManagerNews = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerNews
        val adaptorNews = landing_AdaptorNews(NewsArrayList)
        recyclerView?.setAdapter(adaptorNews)
        // setup recyclerview Mitra
//        val modulMitraDump = ModulMitra("1", "nicholas", "Juwana", "laki", "082134081040", "Jl.Situ Aja", "admin@gmail.com", "Nicholas123", "","")
//        for (i in 0..14) {
//            MitraArrayList.add(modulMitraDump)
//        }
        recyclerView = findViewById(R.id.recycler_mitra)
        val layoutManagerMitra = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerMitra
        val adaptor_mitra = AdaptorMitra(MitraArrayList)
        recyclerView?.setAdapter(adaptor_mitra)
    }
}