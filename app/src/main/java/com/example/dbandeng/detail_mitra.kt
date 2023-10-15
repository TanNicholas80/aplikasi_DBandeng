package com.example.dbandeng

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.modul.ModulMitraLP
import com.example.dbandeng.modul.ModulProduk
import com.example.dbandeng.response.getDetailMitraResponse
import com.example.dbandeng.response.getProductByMitraRes
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detail_mitra : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    lateinit var foto_mitra: CircleImageView
    lateinit var mitraName : TextView
    lateinit var mitraPhone: TextView
    lateinit var mitraAddress: TextView
    lateinit var idMitra:String
    var modulMitra : ArrayList<ModulMitraLP>? = null
    var produkArrayList: ArrayList<ModulProduk>? = null
    var produkAdaptor : Adaptor_Product? = null
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
        val getProdukByMitra:Call<getProductByMitraRes>? = interfaceDbandeng?.getProductByMitra(idMitra)
        getProdukByMitra?.enqueue(object: Callback<getProductByMitraRes> {
            override fun onResponse(call: Call<getProductByMitraRes>, response: Response<getProductByMitraRes>) {
                val responseData: ArrayList<ModulProduk> = response.body()!!.data
                produkArrayList = ArrayList<ModulProduk>(responseData)
                produkAdaptor = Adaptor_Product(produkArrayList)
                recyclerView!!.setAdapter(produkAdaptor)
                Log.d("cek_resp", "$responseData");
            }

            override fun onFailure(call: Call<getProductByMitraRes>, t: Throwable) {
                Toast.makeText(this@detail_mitra, "gagal get produk" + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}