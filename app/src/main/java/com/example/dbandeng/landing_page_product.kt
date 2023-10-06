package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.modul.ModulProduk
import com.example.dbandeng.response.GetProductLandingRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class landing_page_product : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var ProdukArrayList = ArrayList<ModulProduk>()
    private lateinit var produkToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page_product)
        // Setup Recycler view Product
        recyclerView = findViewById(R.id.product_recycle)
        recyclerView?.setLayoutManager(GridLayoutManager(this, 2))
        try {
            getProdukLanding()
        } catch (e: Exception) {
            Log.d("crud_produk", e.message!!)
        }
        val bottomNavigation = findViewById(R.id.bottomNavigation) as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_not_active))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.product_active))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.news_user))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.profile_user))
        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    val landing_page_layout = Intent(this@landing_page_product, lading_page_home::class.java);
                    startActivity(landing_page_layout)
                }

                2 -> {
                    val product_page_layout = Intent(this@landing_page_product, landing_page_product::class.java);
                    startActivity(product_page_layout)
                }

                3 -> {
                    val news_page_layout = Intent(this@landing_page_product, landing_page_news::class.java);
                    startActivity(news_page_layout)
                }

                4 -> {
                    val profile_user_layout = Intent(this@landing_page_product, landing_page_profile::class.java);
                    startActivity(profile_user_layout)
                }
            }
        }
//        val modulProdukDump = ModulProduk("1", "Babi Goreng", "Sholeh Ac", "gak halal","", "18", "10.000")
//        for (i in 0..14) {
//            ProdukArrayList.add(modulProdukDump)
//        }
//        recyclerView = findViewById(R.id.product_recycle)
//        recyclerView?.setLayoutManager(GridLayoutManager(this, 2))
//        val adaptorProduk = Adaptor_Product(ProdukArrayList)
//        recyclerView?.setAdapter(adaptorProduk)
        // setup toolbar
        produkToolbar = findViewById(R.id.produk_toolbar)
        setSupportActionBar(produkToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val adaptorProduk = Adaptor_Product(ProdukArrayList)
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_dbandeng, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search_bar)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Cari Produk ..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<ModulProduk>()

                if(newText != null) {
                    for (i in ProdukArrayList) {
                        if (i.nama_produk.lowercase(Locale.ROOT).contains(newText)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(this@landing_page_product, "Data Produk Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        adaptorProduk.onApplySearch(searchList)
                    }
                }
                return true
            }

        })
        return true
    }

    fun getProdukLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getProduk = interfaceDbandeng.GetAllProduk()
        getProduk.enqueue(object : Callback<GetProductLandingRes> {
            override fun onResponse(call: Call<GetProductLandingRes>, response: Response<GetProductLandingRes>) {
                val responseData: List<ModulProduk> = response.body()!!.data
                val produkLanding: ArrayList<ModulProduk> = ArrayList<ModulProduk>(responseData)
                val produkAdaptor : Adaptor_Product = Adaptor_Product(produkLanding)
                recyclerView!!.setAdapter(produkAdaptor)
            }

            override fun onFailure(call: Call<GetProductLandingRes>, t: Throwable) {
                Toast.makeText(this@landing_page_product, "gagal get produk" + t.message, Toast.LENGTH_LONG)
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }
}