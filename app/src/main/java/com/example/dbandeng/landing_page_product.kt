package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.modul.ModulProduk

class landing_page_product : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var ProdukArrayList = ArrayList<ModulProduk>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_product)

        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_not_active))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.product_active))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.news_user))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.profile_user))
        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    val landing_page_layout = Intent(this@landing_page_product, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }

                2 -> {
                    val product_page_layout = Intent(this@landing_page_product, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }

                3 -> {
                    val news_page_layout = Intent(this@landing_page_product, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }

                4 -> {
                    val profile_user_layout = Intent(this@landing_page_product, landing_page_profile::class.java);
                    startActivity(profile_user_layout)
                }
            }
        }
        val modulProdukDump = ModulProduk("1", "Babi Goreng", "Sholeh Ac", "gak halal", "18", "10.000")
        for (i in 0..14) {
            ProdukArrayList.add(modulProdukDump)
        }
        recyclerView = findViewById(R.id.product_recycle)
        recyclerView?.setLayoutManager(GridLayoutManager(this, 2))
        val adaptorProduk = Adaptor_Product(ProdukArrayList)
        recyclerView?.setAdapter(adaptorProduk)
    }
}