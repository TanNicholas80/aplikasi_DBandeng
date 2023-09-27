package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.adaptor.landing_AdaptorNews
import com.example.dbandeng.modul.ModulNews
import com.example.dbandeng.modul.ModulProdukNew
import com.example.dbandeng.response.GetArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class landing_page_news : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var modulNewsDump: ModulNews? = null
    var NewsArrayList = ArrayList<ModulNews>()
    private lateinit var NewsToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_news)
        // setup Recyler View
        recyclerView = findViewById(R.id.news_recycle)
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        try {
            getNewsLanding()
        } catch (e: Exception) {
            Log.d("crud_produk", e.message!!)
        }
        // setup toolbar
        NewsToolbar = findViewById(R.id.news_toolbar)
        setSupportActionBar(NewsToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
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
//        val modulNewsDump = ModulNews("1", "Kampung UMKM Bandeng", "Kampung UMKM Bandeng merupakan sebuah UMKM","", "18/10/2021")
//        for (i in 0..14) {
//            NewsArrayList.add(modulNewsDump)
//        }
//        recyclerView = findViewById(R.id.news_recycle)
//        recyclerView?.setLayoutManager(LinearLayoutManager(this))
//        val adaptorNews = landing_AdaptorNews(NewsArrayList)
//        recyclerView?.setAdapter(adaptorNews)
        // setup carousel
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.news1_min))
        imageList.add(SlideModel(R.drawable.news2_min))
        imageList.add(SlideModel(R.drawable.news3_min))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
    }
    // setup search bar pada action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val adaptorNews = landing_AdaptorNews(NewsArrayList)
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_dbandeng, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search_bar)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Cari Berita ..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<ModulNews>()

                if(newText != null) {
                    for (i in NewsArrayList) {
                        if (i.jdlArticle.lowercase(Locale.ROOT).contains(newText)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(this@landing_page_news, "Data Artikel Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        adaptorNews.onApplySearch(searchList)
                    }
                }
                return true
            }

        })
        return true
    }
    // Get Article Berita
    fun getNewsLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getNews = interfaceDbandeng.GetArticle()
        getNews.enqueue(object : Callback<GetArticleResponse> {
            override fun onResponse(call: Call<GetArticleResponse>, response: Response<GetArticleResponse>) {
                val responseData: List<ModulNews> = response.body()!!.data
                val produkMitra: ArrayList<ModulNews> = ArrayList<ModulNews>(responseData)
                val newsAdaptor : landing_AdaptorNews = landing_AdaptorNews(produkMitra)
                recyclerView!!.setAdapter(newsAdaptor)
            }

            override fun onFailure(call: Call<GetArticleResponse>, t: Throwable) {
                Toast.makeText(this@landing_page_news, "gagal get News" + t.message, Toast.LENGTH_LONG)
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }
}