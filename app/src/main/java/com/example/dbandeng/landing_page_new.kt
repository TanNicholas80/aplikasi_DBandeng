package com.example.dbandeng

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbandeng.adaptor.landing_AdaptorNews
import com.example.dbandeng.modul.ModulNews
import com.example.dbandeng.response.GetArticleResponse
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
 * Use the [landing_page_new.newInstance] factory method to
 * create an instance of this fragment.
 */
class landing_page_new : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    var recyclerView: RecyclerView? = null
    var modulNewsDump: ModulNews? = null
    var NewsArrayList : ArrayList<ModulNews>? = null
    var newsAdaptor : landing_AdaptorNews? = null
    var adaptorNew : landing_AdaptorNews? = null
    var recyclerLimit: RecyclerView? = null


    private lateinit var NewsToolbar: Toolbar
    private lateinit var newsLayout: View
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
        setHasOptionsMenu(true);
        newsLayout = inflater.inflate(R.layout.fragment_landing_page_new, container, false)
        // setup Recyler View
        getNewsLanding()
        recyclerView = newsLayout.findViewById(R.id.news_recycle)
        recyclerView?.setLayoutManager(LinearLayoutManager(requireContext()))
        // setup recycler limit
        recyclerLimit = newsLayout.findViewById(R.id.recycler_limit)
        recyclerLimit?.setLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
        // setup toolbar
        NewsToolbar = newsLayout.findViewById(R.id.news_toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(NewsToolbar)
        val titlearticle = "ARTICLES"

        val spannableString = SpannableString(titlearticle)
        spannableString.setSpan(ForegroundColorSpan(Color.WHITE), 0, titlearticle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, titlearticle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        (requireActivity() as AppCompatActivity).supportActionBar!!.title = spannableString

        return newsLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment landing_page_new.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            landing_page_new().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        newsAdaptor = landing_AdaptorNews(NewsArrayList)
        inflater.inflate(R.menu.menu_dbandeng, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search_bar)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Cari Berita ..."
        super.onCreateOptionsMenu(menu,inflater);
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<ModulNews>()

                if(newText != null) {
                    val query = newText.lowercase(Locale.ROOT)
                    for (i in NewsArrayList!!) {
                        if (i.jdlArticle.lowercase(Locale.ROOT).contains(query)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(requireContext(), "Data Artikel Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        newsAdaptor!!.onApplySearch(searchList)
                    }
                }
                return true
            }
        })
    }
    // Get Article Berita
    fun getNewsLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getNews = interfaceDbandeng.GetArticle()
        getNews.enqueue(object : Callback<GetArticleResponse> {
            override fun onResponse(call: Call<GetArticleResponse>, response: Response<GetArticleResponse>) {
                val responseData: List<ModulNews> = response.body()!!.data
                NewsArrayList = ArrayList<ModulNews>(responseData)
                newsAdaptor = landing_AdaptorNews(NewsArrayList)
                adaptorNew = landing_AdaptorNews(NewsArrayList)
                adaptorNew!!.setLimit(3)
                recyclerView!!.setAdapter(newsAdaptor)
                recyclerLimit!!.setAdapter(adaptorNew)
            }

            override fun onFailure(call: Call<GetArticleResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "gagal get News" + t.message, Toast.LENGTH_LONG)
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }
}