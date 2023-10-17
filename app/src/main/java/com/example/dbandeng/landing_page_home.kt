package com.example.dbandeng

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dbandeng.adaptor.AdaptorMitra
import com.example.dbandeng.adaptor.Adaptor_Product
import com.example.dbandeng.adaptor.landing_AdaptorNews
import com.example.dbandeng.modul.*
import com.example.dbandeng.response.GetAllMitraLandingResponse
import com.example.dbandeng.response.GetArticleResponse
import com.example.dbandeng.response.GetProductLandingRes
import com.example.dbandeng.response.ProfilUserResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    var recyclerViewProduk: RecyclerView? = null
    var recyclerViewBerita: RecyclerView? = null
    var recyclerViewMitra: RecyclerView? = null
    var modulProdukDump: ModulProduk? = null
    var ProdukArrayList = ArrayList<ModulProduk>()
    var modulNewsDump: ModulNews? = null
    var NewsArrayList = ArrayList<ModulNews>()
    var modulMitraDump: ModulMitra? = null
    var MitraArrayList = ArrayList<ModulMitraLP>()
    lateinit var adaptorProduk: Adaptor_Product
    lateinit var adaptorNews: landing_AdaptorNews
    private lateinit var homeLayout: View

    lateinit var fotoProfil: CircleImageView;
    lateinit var namaUser: TextView;
    lateinit var modulUser: ModulUser;

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

        getProdukLanding();
        getNewsLanding();
        getMitraLanding();

        homeLayout = inflater.inflate(R.layout.fragment_landing_page_home, container, false)

        recyclerViewProduk = homeLayout.findViewById(R.id.recycler_produk_baru)
        recyclerViewBerita = homeLayout.findViewById(R.id.recycler_berita_terbaru)
        recyclerViewMitra = homeLayout.findViewById(R.id.recycler_mitra)
        fotoProfil = homeLayout.findViewById(R.id.foto_profile_home)
        namaUser = homeLayout.findViewById(R.id.nama_user_home)

        val preferences = context?.getSharedPreferences("my_preferences", AppCompatActivity.MODE_PRIVATE)
        val authToken = preferences?.getString("auth_token", null).toString();
        val idUser = preferences?.getString("id_user", null).toString();

        getUserDataProfile("Bearer " + authToken, idUser)

        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.welcome_1))
        imageList.add(SlideModel(R.drawable.welcome_2))
        imageList.add(SlideModel(R.drawable.welcome_3))

        val imageSlider = homeLayout.findViewById<ImageSlider>(R.id.image_slider_home)

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)


        val layoutManagerProduk = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProduk?.layoutManager = layoutManagerProduk
        adaptorProduk = Adaptor_Product(ProdukArrayList)
        adaptorProduk.setLimit(3)
        recyclerViewProduk?.setAdapter(adaptorProduk)


        val layoutManagerNews = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewBerita?.layoutManager = layoutManagerNews
        adaptorNews = landing_AdaptorNews(NewsArrayList)
        adaptorNews.setLimit(3)
        recyclerViewBerita?.setAdapter(adaptorNews)


        val layoutManagerMitra = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMitra?.layoutManager = layoutManagerMitra
        val adaptor_mitra = AdaptorMitra(MitraArrayList)
        recyclerViewMitra?.setAdapter(adaptor_mitra)

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

    fun getProdukLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getProduk = interfaceDbandeng.GetAllProduk()
        getProduk.enqueue(object : Callback<GetProductLandingRes> {
            override fun onResponse(call: Call<GetProductLandingRes>, response: Response<GetProductLandingRes>) {
                val responseData: List<ModulProduk> = response.body()!!.data
                ProdukArrayList = java.util.ArrayList<ModulProduk>(responseData)
                adaptorProduk = Adaptor_Product(ProdukArrayList)
                adaptorProduk.setLimit(6)
                recyclerViewProduk?.setAdapter(adaptorProduk)
            }

            override fun onFailure(call: Call<GetProductLandingRes>, t: Throwable) {
                Toast.makeText(requireContext(), "gagal get produk" + t.message, Toast.LENGTH_LONG).show()
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }

    fun getNewsLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getNews = interfaceDbandeng.GetArticle()
        getNews.enqueue(object : Callback<GetArticleResponse> {
            override fun onResponse(call: Call<GetArticleResponse>, response: Response<GetArticleResponse>) {
                if(response.isSuccessful){
                    val responseData: List<ModulNews>? = response.body()?.data
                    NewsArrayList = java.util.ArrayList<ModulNews>(responseData)
                    adaptorNews = landing_AdaptorNews(NewsArrayList)
                    adaptorNews.setLimit(3)
                    recyclerViewBerita?.setAdapter(adaptorNews)
                }
            }

            override fun onFailure(call: Call<GetArticleResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "gagal get News" + t.message, Toast.LENGTH_LONG)
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }

    fun getMitraLanding() {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val getAllMitra = interfaceDbandeng.getAllMitra()
        getAllMitra.enqueue(object : Callback<GetAllMitraLandingResponse> {
            override fun onResponse(call: Call<GetAllMitraLandingResponse>, response: Response<GetAllMitraLandingResponse>) {
                if(response.isSuccessful){
                    val responseData: GetAllMitraLandingResponse? = response.body()
                    MitraArrayList = responseData!!.data
                    val adaptor_mitra = AdaptorMitra(MitraArrayList)
                    recyclerViewMitra?.setAdapter(adaptor_mitra)
                }
            }

            override fun onFailure(call: Call<GetAllMitraLandingResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "gagal get News" + t.message, Toast.LENGTH_LONG)
                Log.d("crud_produk", "error" + t.message)
            }
        })
    }

    private fun getUserDataProfile(authToken: String?, idUser: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val getDataUser: Call<ProfilUserResponse>? = interfaceDbandeng?.getUser(authToken, idUser)
        getDataUser?.enqueue(object : Callback<ProfilUserResponse> {
            override fun onResponse(call: Call<ProfilUserResponse>, response: Response<ProfilUserResponse>) {
                Log.d("RegisUser", "a" + call.request().toString());
                Log.d("RegisUser", "b" + response.toString());
                Log.d("RegisUser", "c" + authToken + " " + idUser);
                if (response.isSuccessful) {
                    val res: ProfilUserResponse? = response.body()
                    if (res != null) {
                        modulUser = res.getModulUser()
                    }
                    val ImageUrl = modulUser?.foto_user
                    namaUser.setText(modulUser.name)
                    if(ImageUrl?.isEmpty() == false) {
                        Picasso.get().load(ImageUrl).into(fotoProfil)
                    }else{
                        fotoProfil.setImageResource(R.drawable.user_profile_empty)
                    }
                } else {

                    Toast.makeText(requireContext(), "Gagal Get Informasi", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ProfilUserResponse>, t: Throwable) {
                Log.d("RegisUser", t.message.toString());
                Toast.makeText(requireContext(), "Gagal Get Informasi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}