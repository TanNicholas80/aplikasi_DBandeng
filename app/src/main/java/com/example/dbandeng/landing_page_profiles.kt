package com.example.dbandeng

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.dbandeng.modul.ModulUser
import com.example.dbandeng.response.*
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

        // Edit Foto User
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                updateFotoUser(authToken,idUser,uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

        foto_user.setOnClickListener {
            Log.d("PhotoPicker", "otw selected")
            try {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }catch(error: Exception){
                Log.d("PhotoPicker", error.message.toString())
            }

            val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
        }

        // Setup onclick Button
        val btnEditUser : Button = profileLayout.findViewById(R.id.Edit_User)
        btnEditUser.setOnClickListener {
            authToken = preferences.getString("auth_token", null).toString();
            idUser = preferences.getString("id_user", null).toString();
            showEditUserPopUp(requireContext(), "Bearer " + authToken, idUser)
        }

        val btnLogoutUser : Button = profileLayout.findViewById(R.id.Logout_User)
        btnLogoutUser.setOnClickListener {
            authToken = preferences.getString("auth_token", null).toString();
            logoutUser(requireContext(),"Bearer " + authToken)
        }

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

    private fun showEditUserPopUp(context: Context, authToken: String?, idUser: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val editUserPopUp = Dialog(context)
        editUserPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
        editUserPopUp.setCancelable(false)
        editUserPopUp.setContentView(R.layout.layout_popup_edit_user)
        editUserPopUp.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        editUserPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val editNamaUser : EditText? = editUserPopUp.findViewById(R.id.Edit_Nama_User)
        val editAlamatUser : EditText? = editUserPopUp.findViewById(R.id.Edit_Alamat_User)
        val editNoTelpUser: EditText? = editUserPopUp.findViewById(R.id.Edit_No_HP_User)
        val editEmailUser: EditText? = editUserPopUp.findViewById(R.id.Edit_Email_User)
        val btnBatalEditUser: Button? = editUserPopUp.findViewById(R.id.Btn_Batal_Edit_User)
        val btnSimpanEditUser: Button? = editUserPopUp.findViewById(R.id.Btn_Simpan_Edit_User)

        editNamaUser?.setText(modulUser?.name)
        editAlamatUser?.setText(modulUser?.alamatUser)
        editNoTelpUser?.setText(modulUser?.no_user)
        editEmailUser?.setText(modulUser?.email)

        btnSimpanEditUser?.setOnClickListener {
            val xNamaUser = editNamaUser?.text.toString()
            val xAlamatUser = editAlamatUser?.text.toString()
            val xNoTelpUser = editNoTelpUser?.text.toString()
            val xEmailUser = editEmailUser?.text.toString()

            val EditDataUser: Call<EditProfilUserRes>? = interfaceDbandeng?.editUser(authToken, idUser, xNamaUser, xEmailUser, xNoTelpUser, xAlamatUser )
            EditDataUser?.enqueue(object : Callback<EditProfilUserRes> {
                override fun onResponse(call: Call<EditProfilUserRes>, response: Response<EditProfilUserRes>) {
                    if (response.isSuccessful) {
                        val res: EditProfilUserRes? = response.body()
                        val rep = res?.getResponse()
                        val textToaster = rep
                        editUserPopUp.dismiss()
                        Toast.makeText(requireContext(), "${textToaster}", Toast.LENGTH_LONG).show()
                        getUserDataProfile(authToken,idUser)
                    } else {
                        Toast.makeText(requireContext(), "Profil Gagal Terupdate", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<EditProfilUserRes>, t: Throwable) {
                    Toast.makeText(requireContext(), "Profil Gagal Terupdate", Toast.LENGTH_LONG).show()
                }

            })
        }
        btnBatalEditUser?.setOnClickListener {
            editUserPopUp.dismiss()
        }

        editUserPopUp.show()
    }

    private fun uriToFile(uri: Uri): File? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireActivity().applicationContext.contentResolver.query(uri, projection, null, null, null)
        return cursor?.use { c ->
            val columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            c.moveToFirst()
            val filePath = c.getString(columnIndex)
            File(filePath)
        }
    }

    private fun updateFotoUser(authToken: String?, idUser: String?, uri: Uri){
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val file: File? = uriToFile(uri)
        val requestFile: RequestBody = file!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("foto_user", file.name, requestFile)
        val editFotoUser: Call<ProfilFotoUserRes>? = interfaceDbandeng?.editFotoUser("Bearer "+authToken,idUser,body)

        editFotoUser?.enqueue(object : Callback<ProfilFotoUserRes> {
            override fun onResponse(call: Call<ProfilFotoUserRes>, response: Response<ProfilFotoUserRes>) {
                Log.d("sendPhoto", "a" + call.request().toString())
                Log.d("sendPhoto", response.code().toString() + " " + response.message())
                if(response.isSuccessful) {
                    Toast.makeText(requireContext(), "Berhasil Update Foto", Toast.LENGTH_LONG).show()
                    getUserDataProfile("Bearer " + authToken,idUser)
                }
            }

            override fun onFailure(call: Call<ProfilFotoUserRes>, t: Throwable) {
                Log.d("sendPhoto", t.message.toString())
                Toast.makeText(requireContext(), "Gagal Update Foto", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun logoutUser(context: Context, authToken: String?) {
        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
        val logoutUserPopUp = Dialog(context)
        logoutUserPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
        logoutUserPopUp.setCancelable(false)
        logoutUserPopUp.setContentView(R.layout.layout_popup_logout)
        logoutUserPopUp.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        logoutUserPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnYa : Button? = logoutUserPopUp.findViewById(R.id.btnYalogout)
        val btnTidak : Button? = logoutUserPopUp.findViewById(R.id.btnTidaklogout)

        btnYa?.setOnClickListener {
            val LogoutUser: Call<LogoutUserRes>? = interfaceDbandeng?.logoutUser(authToken)

            LogoutUser?.enqueue(object : Callback<LogoutUserRes> {
                override fun onResponse(call: Call<LogoutUserRes>, response: Response<LogoutUserRes>) {
                    if(response.isSuccessful) {
                        val res : LogoutUserRes? = response.body()
                        val rep = res?.getResponse()
                        val textToaster = rep
                        Toast.makeText(requireContext(), "${textToaster}", Toast.LENGTH_LONG).show()
                        val loginUser_layout = Intent(requireContext(), login_user::class.java);// ntar ganti beranda lagi

                        startActivity(loginUser_layout);
                    } else {
                        Toast.makeText(requireContext(), "Logout Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LogoutUserRes>, t: Throwable) {
                    Toast.makeText(requireContext(), "Logout User Gagal", Toast.LENGTH_LONG).show()
                }

            })
        }

        btnTidak?.setOnClickListener {
            logoutUserPopUp.dismiss()
        }

        logoutUserPopUp.show()
    }
}