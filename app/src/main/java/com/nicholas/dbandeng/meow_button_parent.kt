package com.nicholas.dbandeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class meow_button_parent : AppCompatActivity() {
    private var bottomNavigation: MeowBottomNavigation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_button_parent)
        addFragment(landing_page_home.newInstance())
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation?.show(1)
        bottomNavigation?.add(MeowBottomNavigation.Model(1, R.drawable.home_icon_solid))
        bottomNavigation?.add(MeowBottomNavigation.Model(2, R.drawable.product_icon))
        bottomNavigation?.add(MeowBottomNavigation.Model(3, R.drawable.news_user))
        bottomNavigation?.add(MeowBottomNavigation.Model(4, R.drawable.profile_user))

        bottomNavigation?.setOnClickMenuListener {
            when(it.id){
                1 -> {
                    replaceFragment(landing_page_home.newInstance())
                }
                2 -> {
                    replaceFragment(landing_page_products.newInstance())
                }
                3 -> {
                    replaceFragment(landing_page_new.newInstance())
                }
                4 -> {
                    replaceFragment(landing_page_profiles.newInstance())
                }
                else -> {
                    replaceFragment(landing_page_home.newInstance())
                }
            }
        }
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}