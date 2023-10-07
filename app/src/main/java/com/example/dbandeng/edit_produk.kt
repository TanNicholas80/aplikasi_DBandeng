package com.example.dbandeng

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
import com.example.dbandeng.modul.ModulProdukNew
import com.example.dbandeng.response.updateProdukResponse
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class edit_produk : AppCompatActivity() {
    lateinit var editNamaProduk:EditText
    lateinit var editHrgProduk:EditText
    lateinit var editStokProduk:EditText
    lateinit var editBeratProduk:EditText
    lateinit var editDskProduk: EditText
    lateinit var editLinkProduk: EditText
    lateinit var editFotoProduk: ImageView
    lateinit var btnBatalEdit: Button
    lateinit var btnSimpanEdit: Button
    lateinit var modulProdukNew: ModulProdukNew
    lateinit var authToken : String
    lateinit var productId : String
    private var uri: Uri? = null
    lateinit var foto : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_produk)
        editNamaProduk = findViewById(R.id.editNamaProduk)
        editFotoProduk = findViewById(R.id.editFotoProduk)
        editHrgProduk = findViewById(R.id.editHargaProduk)
        editStokProduk = findViewById(R.id.editStokProduk)
        editBeratProduk = findViewById(R.id.editBeratProduk)
        editDskProduk = findViewById(R.id.editDeskripsiProduk)
        editLinkProduk = findViewById(R.id.editLinkProduk);

        val nama = intent.getStringExtra("nama_produk")
        foto = intent.getStringExtra("foto_produk").toString()
        val harga = intent.getStringExtra("harga_produk")
        val stok = intent.getStringExtra("stok_produk")
        val berat = intent.getStringExtra("berat_produk")
        val deskripsi = intent.getStringExtra("dsk_produk")
        val link = intent.getStringExtra("link_produk")
        editNamaProduk.setText(nama)
        val ImageUrl = foto
        Picasso.get().load(ImageUrl).into(editFotoProduk)
        editHrgProduk.setText(harga)
        editStokProduk.setText(stok)
        editBeratProduk.setText(berat)
        editDskProduk.setText(deskripsi)
        editLinkProduk.setText(link)

        btnBatalEdit = findViewById(R.id.Btn_Batal_Edit_Produk);
        btnSimpanEdit = findViewById(R.id.Btn_Simpan_Edit_Produk);

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

        editFotoProduk.setOnClickListener {
            Log.d("PhotoPicker", "otw selected")
            try {
                // Pilih media terlebih dahulu saat tombol ditekan
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } catch(error: Exception){
                Log.d("PhotoPicker", error.message.toString())
            }

            val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        }

        btnSimpanEdit.setOnClickListener {
            val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
            authToken = preferences.getString("auth_token", null).toString();
            val id = intent.getStringExtra("id_produk")
            productId = id.toString();
            if (uri != null) {
                // Lanjutkan dengan menggunakan resultUri
                // Panggil fungsi yang membutuhkan resultUri di sini
                updateProdukMitra("Bearer " + authToken, productId, uri!!)
            } else {
                updateProdukMitraWithoutFoto("Bearer " + authToken, productId,)
            }
        }

        btnBatalEdit.setOnClickListener {
            val CRUDProduct_layout = Intent(this@edit_produk, CRUD_Product::class.java);// ntar ganti beranda lagi

            startActivity(CRUDProduct_layout);
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

    fun updateProdukMitraWithoutFoto(authToken: String?, idMitra: String?,){
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val xEditNamaProduk = editNamaProduk?.text.toString()
        val xEditHrgProduk = editHrgProduk?.text.toString()
        val xEditStokProduk = editStokProduk?.text.toString()
        val xEditBeratProduk = editBeratProduk?.text.toString()
        val xEditDskProduk = editDskProduk?.text.toString()
        val xEditLinkProduk = editLinkProduk?.text.toString()

        //merubah semua input request menajdi multipart
        val requestName: RequestBody = xEditNamaProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestHrg: RequestBody = xEditHrgProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestStok: RequestBody = xEditStokProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestBerat: RequestBody = xEditBeratProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestDsk: RequestBody = xEditDskProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestLink: RequestBody = xEditLinkProduk.toRequestBody("text/plain".toMediaTypeOrNull());

        val editProduk: Call<updateProdukResponse>? = interfaceDbandeng?.updateProdukMitraWithoutPhoto(authToken,idMitra, requestName, requestHrg, requestStok, requestBerat, requestDsk, requestLink)
        editProduk?.enqueue(object : Callback<updateProdukResponse> {
            override fun onResponse(call: Call<updateProdukResponse>, response: Response<updateProdukResponse>) {
                if(response.isSuccessful) {
                    Toast.makeText(this@edit_produk, "Produk Berhasil Diperbarui", Toast.LENGTH_LONG).show()
                    val CRUDProduct_layout = Intent(this@edit_produk, CRUD_Product::class.java);// ntar ganti beranda lagi

                    startActivity(CRUDProduct_layout);
                }
            }

            override fun onFailure(call: Call<updateProdukResponse>, t: Throwable) {
                Log.d("sendPhoto", "a" + t.message.toString())
                Log.d("sendPhoto", "b " + call.request().toString())
                Toast.makeText(this@edit_produk, "Produk Gagal Diperbarui", Toast.LENGTH_LONG).show()
            }

        })
    }
    fun updateProdukMitra(authToken: String?, idMitra: String?, uri: Uri) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val xEditNamaProduk = editNamaProduk?.text.toString()
        val file: File? = uriToFile(uri);
        val requestFile: RequestBody = file!!.asRequestBody("multipart/form-data".toMediaTypeOrNull());
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("foto_produk", file.name, requestFile)
        val xEditHrgProduk = editHrgProduk?.text.toString()
        val xEditStokProduk = editStokProduk?.text.toString()
        val xEditBeratProduk = editBeratProduk?.text.toString()
        val xEditDskProduk = editDskProduk?.text.toString()
        val xEditLinkProduk = editLinkProduk?.text.toString()

        //merubah semua input request menajdi multipart
        val requestName: RequestBody = xEditNamaProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestHrg: RequestBody = xEditHrgProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestStok: RequestBody = xEditStokProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestBerat: RequestBody = xEditBeratProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestDsk: RequestBody = xEditDskProduk.toRequestBody("text/plain".toMediaTypeOrNull());
        val requestLink: RequestBody = xEditLinkProduk.toRequestBody("text/plain".toMediaTypeOrNull());

        val editProduk: Call<updateProdukResponse>? = interfaceDbandeng?.updateProdukMitra(authToken,idMitra, requestName, body, requestHrg, requestStok, requestBerat, requestDsk, requestLink)
        editProduk?.enqueue(object : Callback<updateProdukResponse> {
            override fun onResponse(call: Call<updateProdukResponse>, response: Response<updateProdukResponse>) {
                Log.d("sendPhoto", "a" + call.request().toString())
                Log.d("sendPhoto", response.code().toString() + " " + response.body().toString())
                if(response.isSuccessful) {
                    Toast.makeText(this@edit_produk, "Produk Berhasil Diperbarui", Toast.LENGTH_LONG).show()
                    val CRUDProduct_layout = Intent(this@edit_produk, CRUD_Product::class.java);// ntar ganti beranda lagi

                    startActivity(CRUDProduct_layout);
                }
            }

            override fun onFailure(call: Call<updateProdukResponse>, t: Throwable) {
                Log.d("sendPhoto", "a" + t.message.toString())
                Log.d("sendPhoto", "b " + call.request().toString())
                Toast.makeText(this@edit_produk, "Produk Gagal Diperbarui", Toast.LENGTH_LONG).show()
            }

        })
    }
}