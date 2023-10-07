package com.example.dbandeng

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.dbandeng.modul.ModulUser
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
 * Use the [landing_page_profiles.newInstance] factory method to
 * create an instance of this fragment.
 */
class landing_page_profiles : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    lateinit var foto_user: CircleImageView
    lateinit var nama_user: TextView
    lateinit var alamat_user: TextView
    lateinit var No_User: TextView
    lateinit var email_user: TextView
    lateinit var authToken : String
    lateinit var idUser : String
    lateinit var modulUser: ModulUser
    private lateinit var profileToolbar: Toolbar
    private lateinit var profileLayout: View
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
        profileLayout = inflater.inflate(R.layout.fragment_landing_page_profiles, container, false)
        // Setup XML ID
        foto_user = profileLayout.findViewById(R.id.profile_user)
        nama_user = profileLayout.findViewById(R.id.namalengkap_user)
        alamat_user = profileLayout.findViewById(R.id.alamat_User)
        No_User = profileLayout.findViewById(R.id.hp_user)
        email_user = profileLayout.findViewById(R.id.email_user)
        // Get Profile User By ID
        val preferences = requireActivity().getSharedPreferences("my_preferences", AppCompatActivity.MODE_PRIVATE)
        authToken = preferences.getString("auth_token", null).toString();
        idUser = preferences.getString("id_user", null).toString();
        getUserDataProfile("Bearer " + authToken, idUser)
        // setup toolbar
        profileToolbar = profileLayout.findViewById(R.id.profile_toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(profileToolbar)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar!!.title = "Beranda"

        return profileLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment landing_page_profiles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            landing_page_profiles().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getUserDataProfile(authToken: String?, idUser: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        val getDataUser: Call<ProfilUserResponse>? = interfaceDbandeng?.getUser(authToken, idUser)
        Log.d("cekToken", authToken + " -- " + idUser);
        getDataUser?.enqueue(object : Callback<ProfilUserResponse> {
            override fun onResponse(call: Call<ProfilUserResponse>, response: Response<ProfilUserResponse>) {
                Log.d("cekToken", response.code().toString() + " " + response.message())
                if (response.isSuccessful) {
                    val res: ProfilUserResponse? = response.body()
                    if (res != null) {
                        modulUser = res.getModulUser()
                    }
                    val ImageUrl = modulUser?.foto_user
                    if(ImageUrl?.isEmpty() == false) {
                        Picasso.get().load(ImageUrl).into(foto_user)
                    }
                    nama_user.setText(modulUser?.name)
                    alamat_user.setText(modulUser?.alamatUser)
                    No_User.setText(modulUser?.no_user)
                    email_user.setText(modulUser?.email)

                    //Toast.makeText(this@landing_page_profile, "Berhasil Login user", Toast.LENGTH_LONG).show()

                } else {

                    Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ProfilUserResponse>, t: Throwable) {
                Log.d("RegisUser", t.message.toString());
                Toast.makeText(requireContext(), "Gagal" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}