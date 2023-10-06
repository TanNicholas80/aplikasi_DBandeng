package com.example.dbandeng

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.modul.ModulMitra
import com.example.dbandeng.response.EditProfilMitraRes
import com.example.dbandeng.response.LogoutMitraRes
import com.example.dbandeng.response.ProfilMitraResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class landing_page_profile_admin : AppCompatActivity() {

    private lateinit var profileToolbar: Toolbar
    lateinit var foto_mitra: CircleImageView
    lateinit var namaLengkap: TextView
    lateinit var namaUser: TextView
    lateinit var emailUser: TextView
    lateinit var alamatUser: TextView
    lateinit var jenisKel: TextView
    lateinit var tglLahir: TextView
    lateinit var hpUser: TextView
    lateinit var modulMitra : ModulMitra
    lateinit var authToken : String
    lateinit var idMitra : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_profile_admin)
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
        authToken = preferences.getString("auth_token", null).toString();
        idMitra = preferences.getString("id_mitra", null).toString();
        getMitraDataProfile("Bearer " + authToken, idMitra)


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
                    val landing_page_layout = Intent(this@landing_page_profile_admin, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }

                2 -> {
                    val product_page_layout = Intent(this@landing_page_profile_admin, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }

                3 -> {
                    val news_page_layout = Intent(this@landing_page_profile_admin, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }
            }
        }
        // setup pop up edit
        val btnEditUser : Button = findViewById(R.id.Edit_User)
        btnEditUser.setOnClickListener {
            authToken = preferences.getString("auth_token", null).toString();
            idMitra = preferences.getString("id_mitra", null).toString();
            showEditPopUp(this, "Bearer " + authToken, idMitra)
        }

        val btnLogoutUser : Button = findViewById(R.id.Logout_User)
        btnLogoutUser.setOnClickListener {
            authToken = preferences.getString("auth_token", null).toString();
            logoutMitra("Bearer " + authToken)
        }

        //setup edit foto on circleimageview click
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                updateFotoMitra(authToken,idMitra,uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }



        foto_mitra.setOnClickListener {
            Log.d("PhotoPicker", "otw selected")
            try {

                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }catch(error: Exception){
                Log.d("PhotoPicker", error.message.toString())
            }

            val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        }
    }

    private fun getMitraDataProfile(authToken: String?, idMitra: String?){
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val getDataMitra: Call<ProfilMitraResponse>? = interfaceDbandeng?.getMitra(authToken, idMitra)
        Log.d("cekToken", authToken + " -- " + idMitra);
        getDataMitra?.enqueue(object : Callback<ProfilMitraResponse> {
            override fun onResponse(call: Call<ProfilMitraResponse>, response: Response<ProfilMitraResponse>) {
                Log.d("cekToken", response.code().toString() + " " + response.message())
                if (response.isSuccessful) {
                    val res: ProfilMitraResponse? = response.body()
                    if (res != null) {
                        modulMitra = res.getModulMitra()
                    }
                    val ImageUrl = modulMitra?.foto_mitra
                    Picasso.get().load(ImageUrl).into(foto_mitra)
                    namaLengkap.setText(modulMitra?.nama_lengkap)
                    namaUser.setText(modulMitra?.nama_mitra)
                    alamatUser.setText(modulMitra?.alamat)
                    jenisKel.setText(modulMitra?.jkel)
                    hpUser.setText(modulMitra?.no_hp)
                    emailUser.setText(modulMitra?.email)
                    tglLahir.setText(modulMitra?.tglLahir)

                    //Toast.makeText(this@landing_page_profile, "Berhasil Login user", Toast.LENGTH_LONG).show()

                } else {

                    Toast.makeText(this@landing_page_profile_admin, "Gagal Login", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ProfilMitraResponse>, t: Throwable) {
                Log.d("RegisUser", t.message.toString());
                Toast.makeText(this@landing_page_profile_admin, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showEditPopUp(context: Context, authToken: String?, idMitra: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val editPopUp = Dialog(context)
        editPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
        editPopUp.setCancelable(false)
        editPopUp.setContentView(R.layout.layout_popup_edit)
        editPopUp.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        editPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Menyimpan Value Edit
        // val editFotoUser : CircleImageView = findViewById(R.id.Edit_Foto_Mitra)
        val editNamaLengkap : EditText? = editPopUp.findViewById(R.id.Edit_Nama_Mitra)
        Log.d("Edit Text CEK", editNamaLengkap.toString())
        val editAlamatMitra : EditText? = editPopUp.findViewById(R.id.Edit_Alamat_Mitra)
        val editTglLahir: EditText? = editPopUp.findViewById(R.id.Edit_Tgl_Lahir)
        val editJenisKel: EditText? = editPopUp.findViewById(R.id.Edit_Jenis_Kel)
        val editNoHpMitra : EditText? = editPopUp.findViewById(R.id.Edit_No_Hp_Mitra)
        val btnSaveEdit : Button? = editPopUp.findViewById(R.id.Btn_Simpan_Edit)
        val btnBatalEdit : Button? = editPopUp.findViewById(R.id.Btn_Batal_Edit)
        editNamaLengkap?.setText(modulMitra?.getNama_lengkap())
        Log.d("Cek", editNamaLengkap.toString())
        Log.d("Id Mitra", idMitra.toString())
        editAlamatMitra?.setText(modulMitra?.getAlamat())
        editTglLahir?.setText(modulMitra?.getTglLahir())
        editJenisKel?.setText(modulMitra?.getJkel())
        editNoHpMitra?.setText(modulMitra?.getNo_hp())
        // Hasil Edit Profile
        btnSaveEdit?.setOnClickListener {
            // Hasil Edit Profile harus di get string saat tekan tombol!
            val xNamaLengkap = editNamaLengkap?.text.toString()
            val xAlamatMitra = editAlamatMitra?.text.toString()
            val xTglLahir = editTglLahir?.text.toString()
            val xJenisKel = editJenisKel?.text.toString()
            val xNoHpMitra = editNoHpMitra?.text.toString()
            Log.d("Nama Lengkap", xNamaLengkap)
            val EditDataMitra: Call<EditProfilMitraRes>? = interfaceDbandeng?.editMitra(authToken, idMitra, xNamaLengkap, xAlamatMitra, xTglLahir, xJenisKel, xNoHpMitra )
            EditDataMitra?.enqueue(object : Callback<EditProfilMitraRes> {
                override fun onResponse(call: Call<EditProfilMitraRes>, response: Response<EditProfilMitraRes>) {
                    Log.d("CekCall", call.request().toString())
                    if(response.isSuccessful) {
                        val res: EditProfilMitraRes? = response.body()
                        val rep = res?.getResponse()
                        val textToaster = rep
                        editPopUp.dismiss()
                        Toast.makeText(this@landing_page_profile_admin, "${textToaster}", Toast.LENGTH_LONG).show()
                        getMitraDataProfile(authToken,idMitra)
                    } else {
                        Toast.makeText(this@landing_page_profile_admin, "Profil Gagal Terupdate", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<EditProfilMitraRes>, t: Throwable) {
                    Toast.makeText(this@landing_page_profile_admin, "Profil Gagal Terupdate", Toast.LENGTH_LONG).show()
                }

            })
        }

        btnBatalEdit?.setOnClickListener {
            editPopUp.dismiss()
        }

        editPopUp.show()
    }

    private fun logoutMitra(authToken: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val LogoutMitra: Call<LogoutMitraRes>? = interfaceDbandeng?.logoutMitra(authToken)

        LogoutMitra?.enqueue(object : Callback<LogoutMitraRes> {
            override fun onResponse(call: Call<LogoutMitraRes>, response: Response<LogoutMitraRes>) {
                if(response.isSuccessful) {
                    val res : LogoutMitraRes? = response.body()
                    val rep = res?.getResponse()
                    val textToaster = rep
                    Toast.makeText(this@landing_page_profile_admin, "${textToaster}", Toast.LENGTH_LONG).show()
                    val loginAdmin_layout = Intent(this@landing_page_profile_admin, login_admin::class.java);// ntar ganti beranda lagi

                    startActivity(loginAdmin_layout);
                } else {
                    Toast.makeText(this@landing_page_profile_admin, "Logout Gagal", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LogoutMitraRes>, t: Throwable) {
                Toast.makeText(this@landing_page_profile_admin, "Logout Mitra Gagal", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun uriToFile(uri: Uri): File? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = applicationContext.contentResolver.query(uri, projection, null, null, null)
        return cursor?.use { c ->
            val columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            c.moveToFirst()
            val filePath = c.getString(columnIndex)
            File(filePath)
        }
    }

    private fun updateFotoMitra(authToken: String?, idMitra: String?, uri: Uri){
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val file:File? = uriToFile(uri);
        val requestFile: RequestBody = file!!.asRequestBody("multipart/form-data".toMediaTypeOrNull());
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("foto_mitra", file.name, requestFile)
        val editFoto: Call<ProfilMitraResponse>? = interfaceDbandeng?.editFotoMitra("Bearer "+authToken,idMitra,body);

        editFoto?.enqueue(object : Callback<ProfilMitraResponse> {
            override fun onResponse(call: Call<ProfilMitraResponse>, response: Response<ProfilMitraResponse>) {
                Log.d("sendPhoto", "a" + call.request().toString())
                Log.d("sendPhoto", response.code().toString() + " " + response.message())
                if(response.isSuccessful) {
                    Toast.makeText(this@landing_page_profile_admin, "Berhasil Update Foto", Toast.LENGTH_LONG).show()
                    getMitraDataProfile("Bearer " + authToken,idMitra)
                }
            }

            override fun onFailure(call: Call<ProfilMitraResponse>, t: Throwable) {
                Log.d("sendPhoto", t.message.toString())
                Toast.makeText(this@landing_page_profile_admin, "Gagal Update Foto", Toast.LENGTH_LONG).show()
            }

        })

    }


//    private fun editFotoMitra(authToken: String?, idMitra: String?) {
//        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
//        fun onCircleImageClick(view: View) {
//            ImagePicker.with(this)
//                .galleryOnly()
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
//        }
//
//        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//            foto_mitra.setImageURI(data?.data)
//        }
//        val EditFotoMitra: Call<ProfilMitraResponse>? = interfaceDbandeng?.editFotoMitra(authToken, idMitra, )
//
//    }
}