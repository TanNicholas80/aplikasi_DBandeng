package com.example.dbandeng;

import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import com.example.dbandeng.modul.ModulProduk;
import com.example.dbandeng.response.GetSpesifikProduct;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduk extends AppCompatActivity {
    TextView textNmMitra, textNmProduk, textHrgProduk, textDskProduk;
    ImageView foto_detail_produk;
    ModulProduk modulProduk;
    InterfaceDbandeng interfaceDbandeng;
    String idProduk;
    Toolbar DetailProdukToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        DetailProdukToolbar = findViewById(R.id.toolbar_detail_produk);
        setSupportActionBar(DetailProdukToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kembali");

        textNmMitra = findViewById(R.id.nama_mitra);
        textNmProduk = findViewById(R.id.nama_produk);
        textHrgProduk = findViewById(R.id.harga_produk);
        textDskProduk = findViewById(R.id.deskripsi_produk);
        foto_detail_produk = findViewById(R.id.thumbnail_produk);
        String nama_mitra = getIntent().getStringExtra("nama_mitra");
        String nama_produk = getIntent().getStringExtra("nama_produk");
        String foto_produk_URL = getIntent().getStringExtra("foto_produk");
        String deskripsi_produk = getIntent().getStringExtra("desk_produk");
        String hrg_produk = getIntent().getStringExtra("harga_produk");
        String link_produk = getIntent().getStringExtra("link_produk");

        textNmMitra.setText(nama_mitra);
        textNmProduk.setText(nama_produk);
        textDskProduk.setText(deskripsi_produk);
        textHrgProduk.setText(hrg_produk);
        Picasso.get().load(foto_produk_URL).into(foto_detail_produk);
    }
}