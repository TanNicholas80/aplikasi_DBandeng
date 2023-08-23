package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class lading_page_home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lading_page_home)

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
    }
}