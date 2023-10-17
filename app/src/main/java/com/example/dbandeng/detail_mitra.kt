package com.example.dbandeng

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbandeng.adaptor.AdaptorModulProductNew
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.modul.ModulMitra
import com.example.dbandeng.modul.ModulMitraLP
import com.example.dbandeng.modul.ModulProduk
import com.example.dbandeng.modul.ModulProdukNew
import com.example.dbandeng.response.GetProductResponse
import com.example.dbandeng.response.getDetailMitraResponse
import com.example.dbandeng.utils.AddTextOnChangeListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class detail_mitra : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    lateinit var foto_mitra: CircleImageView
    lateinit var mitraName : TextView
    lateinit var mitraPhone: TextView
    lateinit var mitraAddress: TextView
    lateinit var idMitra:String
    lateinit var searchInput : TextInputEditText
    var modulMitra : ArrayList<ModulMitraLP>? = null
    var produkArrayList: ArrayList<ModulProdukNew>? = null
    var produkAdaptor : AdaptorModulProductNew? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mitra)

        foto_mitra = findViewById(R.id.dtl_mitra_foto)
        mitraName = findViewById(R.id.dtl_nama_mitra)
        mitraPhone = findViewById(R.id.dtl_telp_mitra)
        mitraAddress = findViewById(R.id.dtl_alamat_mitra)
        recyclerView = findViewById(R.id.dtl_recycler_produk)
        recyclerView?.setLayoutManager(GridLayoutManager(this, 2))
        searchInput = findViewById(R.id.cariProduk)

        //searchInput.editText?.addTextChangedListener {object : AddTextOnChangeListener(this, searchInput.editText);)

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Metode ini dipanggil sebelum teks berubah
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Metode ini dipanggil saat teks berubah
                val newText = s.toString()

                val searchList = ArrayList<ModulProdukNew>()

                if(newText != null) {
                    val query = newText.lowercase(Locale.ROOT)
                    for (i in produkArrayList!!) {
                        if (i.nmProduk.lowercase(Locale.ROOT).contains(query)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(this@detail_mitra, "Data Produk Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        produkAdaptor!!.onApplySearch(searchList)
                    }
                }
                // Di sini Anda dapat memproses atau mengubah data sesuai dengan teks yang baru dimasukkan
            }

            override fun afterTextChanged(s: Editable?) {
                // Metode ini dipanggil setelah teks berubah
            }
        })

        idMitra = intent.getStringExtra("id_mitra").toString()
        val name_mitra = intent.getStringExtra("nama_mitra")
        val fotoMitra = intent.getStringExtra("foto_mitra")
        mitraName.setText(name_mitra)
        if(fotoMitra?.isEmpty() == false) {
            Picasso.get().load(fotoMitra).into(foto_mitra)
        }else{
            foto_mitra.setImageResource(R.drawable.user_profile_empty)
        }
        Log.d("cek_id", "Selected URI: $idMitra")

        try {
            getDetailMitra(idMitra)
            getProdukMitra(idMitra)
        } catch (e: Exception) {
            Log.d("crud_produk", e.message!!)
        }
    }

    fun getDetailMitra(idMitra:String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getDetailMitra: Call<getDetailMitraResponse>? = interfaceDbandeng?.getDetailMitra(idMitra)
        getDetailMitra?.enqueue(object: Callback<getDetailMitraResponse> {
            override fun onResponse(call: Call<getDetailMitraResponse>, response: Response<getDetailMitraResponse>) {
                if (response.isSuccessful) {
                    val res: getDetailMitraResponse? = response.body()
                    if (res != null) {
                        modulMitra = res.data
                    }
                    val firstModulMitra = modulMitra?.firstOrNull()
                    mitraPhone.setText(firstModulMitra?.no_hp)
                    mitraAddress.setText(firstModulMitra?.alamat)
                } else {
                    Toast.makeText(this@detail_mitra, "Gagal Mendapatkan Informasi", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<getDetailMitraResponse>, t: Throwable) {
                Toast.makeText(this@detail_mitra, "Gagal Mendapatkan Informasi", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getProdukMitra(idMitra:String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getProdukByMitra:Call<GetProductResponse>? = interfaceDbandeng?.getProductByMitra(idMitra)
        getProdukByMitra?.enqueue(object: Callback<GetProductResponse> {
            override fun onResponse(call: Call<GetProductResponse>, response: Response<GetProductResponse>) {
                val responseData = response.body()!!.data
                val gson = Gson()
                val modelMitra = gson.fromJson(responseData, ModulMitra::class.java)
                val produkMitra: List<ModulProdukNew> = modelMitra.products
                produkArrayList = ArrayList<ModulProdukNew>(produkMitra)
                produkAdaptor = AdaptorModulProductNew(produkArrayList, modelMitra.nama_mitra)
                recyclerView!!.setAdapter(produkAdaptor)
                Log.d("cek_resp", "$responseData");
            }

            override fun onFailure(call: Call<GetProductResponse>, t: Throwable) {
                Log.d("cek_resp", t.message.toString());
                Toast.makeText(this@detail_mitra, "gagal get produk" + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}