package com.example.dbandeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class lading_page_home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lading_page_home)

        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.icon_user))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.icon_download))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.icon_kecepatan))
        bottomNavigation.show(1)
    }
}