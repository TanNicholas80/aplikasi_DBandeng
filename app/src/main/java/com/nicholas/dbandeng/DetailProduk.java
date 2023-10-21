package com.nicholas.dbandeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import com.nicholas.dbandeng.modul.ModulProduk;
import com.nicholas.dbandeng.response.GetSpesifikProduct;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduk extends AppCompatActivity {
    TextView textNmMitra, textNmProduk, textHrgProduk, textDskProduk, textStkProduk, textBeratProduk;
    ImageView foto_detail_produk;
    ModulProduk modulProduk;
    InterfaceDbandeng interfaceDbandeng;
    Button btn_link_shopee;
    String idProduk;
    Toolbar DetailProdukToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        DetailProdukToolbar = findViewById(R.id.toolbar_detail_produk);
        setSupportActionBar(DetailProdukToolbar);

        String titledtlproduk = "DETAIL PRODUK";

        SpannableString spannableString = new SpannableString(titledtlproduk);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, titledtlproduk.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, titledtlproduk.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        textNmMitra = findViewById(R.id.nama_mitra);
        textNmProduk = findViewById(R.id.nama_produk);
        textHrgProduk = findViewById(R.id.harga_produk);
        textDskProduk = findViewById(R.id.deskripsi_produk);
        textStkProduk = findViewById(R.id.stokProduk);
        textBeratProduk = findViewById(R.id.beratProduk);
        btn_link_shopee = findViewById(R.id.btn_link_produk);
        foto_detail_produk = findViewById(R.id.thumbnail_produk);
        String nama_mitra = getIntent().getStringExtra("nama_mitra");
        String nama_produk = getIntent().getStringExtra("nama_produk");
        String foto_produk_URL = getIntent().getStringExtra("foto_produk");
        String deskripsi_produk = getIntent().getStringExtra("desk_produk");
        String hrg_produk = getIntent().getStringExtra("harga_produk");
        String stok_produk = getIntent().getStringExtra("stok_produk");
        String berat_produk = getIntent().getStringExtra("berat_produk");
        String link_produk = getIntent().getStringExtra("link_produk");

        textNmMitra.setText(nama_mitra);
        textNmProduk.setText(nama_produk);
        textDskProduk.setText(deskripsi_produk);
        textHrgProduk.setText(hrg_produk);
        textStkProduk.setText(stok_produk);
        textBeratProduk.setText(berat_produk);
        Picasso.get().load(foto_produk_URL).into(foto_detail_produk);
        btn_link_shopee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link_produk));

                startActivity(intent);
            }
        });
    }
}