package com.nicholas.dbandeng;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.squareup.picasso.Picasso;

public class DetailBerita extends AppCompatActivity {
    TextView txtJdlArticle, txtIsiArticle, txtCreatedAt;
    ImageView foto_news;
    Toolbar DetailBeritaToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        DetailBeritaToolbar = findViewById(R.id.toolbar_detail_berita);
        setSupportActionBar(DetailBeritaToolbar);

        String titledtlnews = "DETAIL ARTICLE";

        SpannableString spannableString = new SpannableString(titledtlnews);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, titledtlnews.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, titledtlnews.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        txtJdlArticle = findViewById(R.id.judul_berita);
        txtIsiArticle = findViewById(R.id.isi_berita);
        txtCreatedAt = findViewById(R.id.created_news);
        foto_news = findViewById(R.id.thumbnail_berita);
        String jdl_article = getIntent().getStringExtra("jdl_news");
        String isi_article = getIntent().getStringExtra("isi_news");
        String created_berita = getIntent().getStringExtra("created_at");
        String imageURL = getIntent().getStringExtra("foto_news");
        String[] parts = created_berita.split("T");

        // Bagian pertama adalah tanggal
        String dateOnly = parts[0];
        txtJdlArticle.setText(jdl_article);
        txtIsiArticle.setText(isi_article);
        txtCreatedAt.setText(dateOnly);
        Picasso.get().load(imageURL).into(foto_news);
    }
}