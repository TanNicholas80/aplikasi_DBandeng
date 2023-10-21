package com.nicholas.dbandeng;

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.nicholas.dbandeng.response.CreateProdukResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.create
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class createproduct : AppCompatActivity() {
    lateinit var inputNamaProduk: EditText
    lateinit var inputHrgProduk: EditText
    lateinit var inputStokProduk: EditText
    lateinit var inputBeratProduk: EditText
    lateinit var inputDskProduk: EditText
    lateinit var inputLinkProduk: EditText
    lateinit var inputFotoProduk: ImageView
    lateinit var btnBatal: Button
    lateinit var btnSimpan: Button
    lateinit var authToken : String
    lateinit var idMitra : String
    private var uri: Uri? = null // Deklarasikan variabel uri sebagai variabel global
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createproduct);

        inputNamaProduk = findViewById(R.id.namaProduk);
        inputFotoProduk = findViewById(R.id.fotoProduk);
        inputHrgProduk = findViewById(R.id.hargaProduk);
        inputStokProduk = findViewById(R.id.stokProduk);
        inputBeratProduk = findViewById(R.id.beratProduk);
        inputDskProduk = findViewById(R.id.deskripsiProduk);
        inputLinkProduk = findViewById(R.id.linkProduk);

        btnBatal = findViewById(R.id.Btn_Batal_Create);
        btnSimpan = findViewById(R.id.Btn_Simpan_Produk);
        //setup edit foto on circleimageview click
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { resultUri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker

            if (resultUri != null) {
                uri = resultUri
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

        inputFotoProduk.setOnClickListener {
            Log.d("PhotoPicker", "otw selected")
            try {
                // Pilih media terlebih dahulu saat tombol ditekan
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } catch(error: Exception){
                Log.d("PhotoPicker", error.message.toString())
            }

            val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        }

        btnSimpan.setOnClickListener {
            val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
            authToken = preferences.getString("auth_token", null).toString();
            idMitra = preferences.getString("id_mitra", null).toString();
            if (uri != null) {
                // Lanjutkan dengan menggunakan resultUri
                // Panggil fungsi yang membutuhkan resultUri di sini
                Toast.makeText(this@createproduct, "Menyimpan produk....", Toast.LENGTH_LONG).show()
                createProdukMitra("Bearer " + authToken, idMitra, uri!!)
            } else {
                Toast.makeText(this, "Pilih foto terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
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
    fun createProdukMitra(authToken: String?, idMitra: String?, uri: Uri) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val file: File? = uriToFile(uri);
        val requestFile: RequestBody = file!!.asRequestBody("multipart/form-data".toMediaTypeOrNull());
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("foto_produk", file.name, requestFile)
        val xNamaProduk = inputNamaProduk.text.toString()
        val xHrgProduk = inputHrgProduk.text.toString()
        val xStokProduk = inputStokProduk.text.toString()
        val xBeratProduk = inputBeratProduk.text.toString()
        val xDskProduk = inputDskProduk.text.toString()
        val xLinkProduk = inputLinkProduk.text.toString()

        //merubah semua input request menajdi multipart
        val requestName: RequestBody = xNamaProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestHrg: RequestBody = xHrgProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestStok: RequestBody = xStokProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestBerat: RequestBody = xBeratProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestDsk: RequestBody = xDskProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestLink: RequestBody = xLinkProduk.toRequestBody("text/plain".toMediaTypeOrNull());

        val createProduk: Call<CreateProdukResponse>? = interfaceDbandeng?.createProdukMitra(authToken,idMitra, requestName, body, requestHrg, requestStok, requestBerat, requestDsk, requestLink)
        createProduk?.enqueue(object : Callback<CreateProdukResponse> {
            override fun onResponse(call: Call<CreateProdukResponse>, response: Response<CreateProdukResponse>) {
                Log.d("sendPhoto", "a" + call.request().toString())
                Log.d("sendPhoto", response.code().toString() + " " + response.body().toString())
                if(response.isSuccessful) {
                    Toast.makeText(this@createproduct, "Produk Berhasil Terbuat", Toast.LENGTH_LONG).show()
                    val CRUDProduct_layout = Intent(this@createproduct, CRUD_Product::class.java);// ntar ganti beranda lagi

                    startActivity(CRUDProduct_layout);
                }
            }

            override fun onFailure(call: Call<CreateProdukResponse>, t: Throwable) {
                Log.d("sendPhoto", "a" + t.message.toString())
                Log.d("sendPhoto", "b " + call.request().toString())
                Toast.makeText(this@createproduct, "Produk Gagal Terbuat", Toast.LENGTH_LONG).show()
            }

        })
    }
}
