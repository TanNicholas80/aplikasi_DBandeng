package com.example.dbandeng

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.modul.ModulMitra
import com.example.dbandeng.response.ProfilMitraResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class landing_page_profile : AppCompatActivity() {

    private lateinit var profileToolbar: Toolbar
    lateinit var foto_mitra: CircleImageView
    lateinit var namaLengkap: TextView
    lateinit var namaUser: TextView
    lateinit var emailUser: TextView
    lateinit var alamatUser: TextView
    lateinit var jenisKel: TextView
    lateinit var tglLahir: TextView
    lateinit var hpUser: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_profile)
        // Setup support Action button
        profileToolbar = findViewById(R.id.profile_toolbar)
        setSupportActionBar(profileToolbar)
        foto_mitra = findViewById(R.id.profile_user)
        namaLengkap = findViewById(R.id.namalengkap)
        namaUser = findViewById(R.id.Nama_User)
        emailUser = findViewById(R.id.Email_User)
        alamatUser = findViewById(R.id.Alamat_User)
        hpUser = findViewById(R.id.No_Hp_User)
        jenisKel = findViewById(R.id.JenisKel_User)
        tglLahir = findViewById(R.id.tanggalLahir_User)


        val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        val authToken : String? = preferences.getString("auth_token", null);
        val idMitra : String? = preferences.getString("id_mitra", null);
        getMitraDataProfile("Bearer " + authToken,idMitra)


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
            }
        }
        // setup pop up edit
        val btnEditUser : Button = findViewById(R.id.Edit_User)
        btnEditUser.setOnClickListener {
            val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
            val authToken : String? = preferences.getString("auth_token", null);
            val idMitra : String? = preferences.getString("id_mitra", null);
            showEditPopUp("Bearer " + authToken, idMitra)
        }
    }

    private fun getMitraDataProfile(authToken: String?, idMitra: String?){
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val getDataMitra: Call<ProfilMitraResponse>? = interfaceDbandeng?.getMitra(authToken, idMitra )
        Log.d("cekToken", authToken + " -- " + idMitra);
        getDataMitra?.enqueue(object : Callback<ProfilMitraResponse> {
            override fun onResponse(call: Call<ProfilMitraResponse>, response: Response<ProfilMitraResponse>) {
                if (response.isSuccessful) {
                    val res: ProfilMitraResponse? = response.body()
                    val modulMitra : ModulMitra? = res?.getModulMitra()
                    val ImageUrl = modulMitra?.foto_mitra
                    Picasso.get().load(ImageUrl).into(foto_mitra)
                    namaLengkap.setText(modulMitra?.nama_lengkap)
                    namaUser.setText(modulMitra?.nama_mitra)
                    alamatUser.setText(modulMitra?.alamat)
                    jenisKel.setText(modulMitra?.jkel)
                    hpUser.setText(modulMitra?.no_hp)
                    emailUser.setText(modulMitra?.email)
                    tglLahir.setText(modulMitra?.tglLahir)

                    Toast.makeText(this@landing_page_profile, "Berhasil Login user", Toast.LENGTH_LONG).show()

                } else {

                    Toast.makeText(this@landing_page_profile, "Gagal Login", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ProfilMitraResponse>, t: Throwable) {
                Log.d("RegisUser", t.message.toString());
                Toast.makeText(this@landing_page_profile, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showEditPopUp(authToken: String?, idMitra: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val EditDataMitra: Call<ProfilMitraResponse>? = interfaceDbandeng?.editMitra(authToken, idMitra )
        val editPopUp = Dialog(this)
        editPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
        editPopUp.setCancelable(false)
        editPopUp.setContentView(R.layout.layout_popup_edit)
        editPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // val editFotoUser : CircleImageView = findViewById(R.id.Edit_Foto_Mitra)
        val editNamaLengkap : EditText = findViewById(R.id.Edit_Nama_Mitra)
        val editAlamatMitra : EditText = findViewById(R.id.Edit_Alamat_Mitra)
        val editTglLahir: EditText = findViewById(R.id.Edit_Tgl_Lahir)
        val editJenisKel: EditText = findViewById(R.id.Edit_Jenis_Kel)
        val editNoHpMitra : EditText = findViewById(R.id.Edit_No_Hp_Mitra)
        val btnSaveEdit : Button = findViewById(R.id.Btn_Simpan_Edit)
        val btnBatalEdit : Button = findViewById(R.id.Btn_Batal_Edit)

        btnSaveEdit.setOnClickListener {
            
        }

        btnBatalEdit.setOnClickListener {
            editPopUp.dismiss()
        }

        editPopUp.show()
    }

    private fun editFotoMitra(authToken: String?, idMitra: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val EditFotoMitra: Call<ProfilMitraResponse>? = interfaceDbandeng?.editMitra(authToken, idMitra)


    }
}