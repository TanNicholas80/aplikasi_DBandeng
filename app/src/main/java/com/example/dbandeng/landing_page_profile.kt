package com.example.dbandeng

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class landing_page_profile : AppCompatActivity() {
    private lateinit var profileToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_profile)
        // Setup support Action button
        profileToolbar = findViewById(R.id.profile_toolbar)
        setSupportActionBar(profileToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
        // Setup Meow Button
        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_not_active))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.product_icon))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.news_user))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.profile_active))
        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    val landing_page_layout = Intent(this@landing_page_profile, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }

                2 -> {
                    val product_page_layout = Intent(this@landing_page_profile, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }

                3 -> {
                    val news_page_layout = Intent(this@landing_page_profile, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }

                4 -> {
                    val profile_user_layout = Intent(this@landing_page_profile, landing_page_profile::class.java);
                    startActivity(profile_user_layout)
                }
            }
        }
        // setup pop up edit
        val btnEditUser : Button = findViewById(R.id.Edit_User)
        btnEditUser.setOnClickListener {
            showEditPopUp()
        }
    }

    private fun showEditPopUp() {
        val editPopUp = Dialog(this)
        editPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
        editPopUp.setCancelable(false)
        editPopUp.setContentView(R.layout.layout_popup_edit)
        editPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val editNamaUser : EditText = findViewById(R.id.Edit_Nama_User)
        val editAlamatUser : EditText = findViewById(R.id.Edit_Alamat_User)
        val editNoHpUser : EditText = findViewById(R.id.Edit_No_Hp_User)
        val editEmailUser: EditText = findViewById(R.id.Edit_Email_User)
        val btnSaveEdit : Button = findViewById(R.id.Btn_Simpan_Edit)
        val btnBatalEdit : Button = findViewById(R.id.Btn_Batal_Edit)

        btnSaveEdit.setOnClickListener {

        }

        btnBatalEdit.setOnClickListener {
            editPopUp.dismiss()
        }

        editPopUp.show()
    }
}