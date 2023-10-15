package com.example.dbandeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.response.GetProductResponse;
import com.example.dbandeng.response.ProfilMitraResponse;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beranda extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnProfil, btnKelolaIot, btnHasilIot, btnCRUDProduk;
    ModulMitra modulMitra;
    CircleImageView imgMitra;
    TextView namaMitra;
    String authToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        btnCRUDProduk = findViewById(R.id.btnCrudProduk);
        btnKelolaIot = findViewById(R.id.btnKelolaIot);
        btnHasilIot = findViewById(R.id.btnHasilIot);
        btnProfil = findViewById(R.id.btnProfil);
        imgMitra = findViewById(R.id.berandaFotoMitra);
        namaMitra = findViewById(R.id.berandaNamaMitra);

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        authToken = preferences.getString("auth_token", null);
        String idMitra = preferences.getString("id_mitra", null);
        getMitraDataProfile(authToken, idMitra);

        btnCRUDProduk.setOnClickListener(this);
        btnKelolaIot.setOnClickListener(this);
        btnHasilIot.setOnClickListener(this);
        btnProfil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnCrudProduk) {
            startActivity(new Intent(Beranda.this, CRUD_Product.class));
        } else if (v.getId()==R.id.btnKelolaIot) {
            startActivity(new Intent(Beranda.this, kelola_iot.class));
        } else if (v.getId()==R.id.btnHasilIot) {
            startActivity(new Intent(Beranda.this, hasil_iot.class));
        } else if (v.getId()==R.id.btnProfil) {
            startActivity(new Intent(Beranda.this, landing_page_profile_admin.class));
        }
    }

    public void getMitraDataProfile(String authToken, String idMitra) {
        InterfaceDbandeng interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng.class);
        Call<ProfilMitraResponse> getMitraBeranda = interfaceDbandeng.getMitra("Bearer " + authToken, idMitra);
        getMitraBeranda.enqueue(new Callback<ProfilMitraResponse>() {
            @Override
            public void onResponse(Call<ProfilMitraResponse> call, Response<ProfilMitraResponse> response) {
                if (response.isSuccessful()) {
                    ProfilMitraResponse res = response.body();
                    if (res != null) {
                        modulMitra = res.getModulMitra();
                    }
                    String ImageUrl = modulMitra.getFoto_mitra();
                    namaMitra.setText(modulMitra.getNama_mitra());
                    if(ImageUrl.isEmpty() == false) {
                        Picasso.get().load(ImageUrl).into(imgMitra);
                    }else{
                        imgMitra.setImageResource(R.drawable.user_profile_empty);
                    }
                } else  {
                    Toast.makeText(Beranda.this, "Gagal Get Informasi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfilMitraResponse> call, Throwable t) {
                Toast.makeText(Beranda.this, "Gagal Get Informasi", Toast.LENGTH_LONG).show();
            }
        });
    }
}