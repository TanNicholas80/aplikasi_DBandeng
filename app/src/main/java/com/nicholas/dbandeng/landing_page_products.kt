package com.nicholas.dbandeng

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.dbandeng.adaptor.Adaptor_Product
import com.nicholas.dbandeng.modul.ModulProduk
import com.nicholas.dbandeng.response.GetProductLandingRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [landing_page_products.newInstance] factory method to
 * create an instance of this fragment.
 */
class landing_page_products : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    var recyclerView: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var produkArrayList: ArrayList<ModulProduk>? = null
    var produkAdaptor : Adaptor_Product? = null
//    var produkArrayList: ArrayList<ModulProduk> = ArrayList()
    private lateinit var produkToolbar: Toolbar
    private lateinit var productLayout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Setup Recycler view Product
        setHasOptionsMenu(true);
        productLayout = inflater.inflate(R.layout.fragment_landing_page_products, container, false)
        recyclerView = productLayout.findViewById(R.id.product_recycle)
        recyclerView?.setLayoutManager(GridLayoutManager(requireContext(), 2))
        try {
            getProdukLanding()
        } catch (e: Exception) {
            Log.d("crud_produk", e.message!!)
        }
        // setup toolbar
        produkToolbar = productLayout.findViewById(R.id.produk_toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(produkToolbar)
        val titleproduk = "PRODUCTS"

        val spannableString = SpannableString(titleproduk)
        spannableString.setSpan(ForegroundColorSpan(Color.WHITE), 0, titleproduk.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, titleproduk.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        (requireActivity() as AppCompatActivity).supportActionBar!!.title = spannableString

        return productLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment landing_page_products.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            landing_page_products().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        produkAdaptor = Adaptor_Product(produkArrayList)
        inflater.inflate(R.menu.menu_dbandeng, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search_bar)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Cari Produk ..."
        super.onCreateOptionsMenu(menu,inflater);
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<ModulProduk>()

                if(newText != null) {
                    val query = newText.lowercase(Locale.ROOT)
                    for (i in produkArrayList!!) {
                        if (i.nama_produk.lowercase(Locale.ROOT).contains(query)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(requireContext(), "Data Produk Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        produkAdaptor!!.onApplySearch(searchList)
                    }
                }
                return true
            }
        })
    }

    fun getProdukLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getProduk = interfaceDbandeng.GetAllProduk()
        getProduk.enqueue(object : Callback<GetProductLandingRes> {
            override fun onResponse(call: Call<GetProductLandingRes>, response: Response<GetProductLandingRes>) {
                val responseData: List<ModulProduk> = response.body()!!.data
                produkArrayList = ArrayList<ModulProduk>(responseData)
                produkAdaptor = Adaptor_Product(produkArrayList)
                recyclerView!!.setAdapter(produkAdaptor)
            }

            override fun onFailure(call: Call<GetProductLandingRes>, t: Throwable) {
                Toast.makeText(requireContext(), "gagal get produk" + t.message, Toast.LENGTH_LONG).show()
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }
}