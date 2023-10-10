package com.example.dbandeng

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dbandeng.adaptor.AdaptorMitra
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.adaptor.landing_AdaptorNews
import com.example.dbandeng.modul.ModulMitra
import com.example.dbandeng.modul.ModulNews
import com.example.dbandeng.modul.ModulProduk

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [landing_page_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class landing_page_home : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    var recyclerView: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var ProdukArrayList = ArrayList<ModulProduk>()
    var modulNewsDump: ModulNews? = null
    var NewsArrayList = ArrayList<ModulNews>()
    var modulMitraDump: ModulMitra? = null
    var MitraArrayList = ArrayList<ModulMitra>()
    private lateinit var homeLayout: View
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
        homeLayout = inflater.inflate(R.layout.fragment_landing_page_home, container, false)
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.welcome_1))
        imageList.add(SlideModel(R.drawable.welcome_2))
        imageList.add(SlideModel(R.drawable.welcome_3))

        val imageSlider = homeLayout.findViewById<ImageSlider>(R.id.image_slider_home)

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        // setup recyclerview Product
//        val modulProdukDump = ModulProduk("1", "Babi Goreng", "Sholeh Ac", "gak halal", "18", "10","18000")
//        for (i in 0..14) {
//            ProdukArrayList.add(modulProdukDump)
//        }
        recyclerView = homeLayout.findViewById(R.id.recycler_produk_baru)
        val layoutManagerProduk = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerProduk
        val adaptorProduk = Adaptor_Product(ProdukArrayList)
        recyclerView?.setAdapter(adaptorProduk)
        // setup recyclerview News
//        val modulNewsDump = ModulNews("1", "Kampung UMKM Bandeng", "Kampung UMKM Bandeng merupakan sebuah UMKM","", "18/10/2021")
//        for (i in 0..14) {
//            NewsArrayList.add(modulNewsDump)
//        }
        recyclerView = homeLayout.findViewById(R.id.recycler_berita_terbaru)
        val layoutManagerNews = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerNews
        val adaptorNews = landing_AdaptorNews(NewsArrayList)
        recyclerView?.setAdapter(adaptorNews)
        // setup recyclerview Mitra
//        val modulMitraDump = ModulMitra("1", "nicholas", "Juwana", "laki", "082134081040", "Jl.Situ Aja", "admin@gmail.com", "Nicholas123", "","")
//        for (i in 0..14) {
//            MitraArrayList.add(modulMitraDump)
//        }
        recyclerView = homeLayout.findViewById(R.id.recycler_mitra)
        val layoutManagerMitra = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManagerMitra
        val adaptor_mitra = AdaptorMitra(MitraArrayList)
        recyclerView?.setAdapter(adaptor_mitra)

        return homeLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment landing_page_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            landing_page_home().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}