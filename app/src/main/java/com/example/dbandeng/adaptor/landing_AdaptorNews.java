package com.example.dbandeng.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dbandeng.DetailBerita;
import com.example.dbandeng.DetailProduk;
import com.example.dbandeng.R;
import com.example.dbandeng.modul.ModulNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class landing_AdaptorNews extends RecyclerView.Adapter<landing_AdaptorNews.myViewHolder> {
    ArrayList<ModulNews> NewsArrayList;
    int limit;
    boolean isLimited = false;
    public landing_AdaptorNews(ArrayList<ModulNews> NewsArrayList) {
        this.NewsArrayList = NewsArrayList;
        this.limit = -1;
    }

    public void setLimit(int limit){
        this.isLimited = true;
        this.limit = limit;
    }

    public void onApplySearch(ArrayList<ModulNews> filteredNews) {
        NewsArrayList = filteredNews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public landing_AdaptorNews.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerview_news,parent,false);
        return new landing_AdaptorNews.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull landing_AdaptorNews.myViewHolder holder, int position) {
        holder.judul_article.setText(NewsArrayList.get(position).getJdlArticle());
        String[] part = NewsArrayList.get(position).getCreated_at().split("T");
        String tanggal = part[0];
        holder.tanggal_buat.setText(tanggal);
        Picasso.get().load(NewsArrayList.get(position).getFoto_article()).fit().centerCrop().into(holder.foto_article);
        holder.card_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), DetailBerita.class);
                intent.putExtra("id_news", NewsArrayList.get(position).getId());
                intent.putExtra("foto_news", NewsArrayList.get(position).getFoto_article());
                intent.putExtra("jdl_news", NewsArrayList.get(position).getJdlArticle());
                intent.putExtra("isi_news", NewsArrayList.get(position).getIsiArticle());
                intent.putExtra("created_at", NewsArrayList.get(position).getCreated_at());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(isLimited){
            return Math.min(NewsArrayList.size(), limit);
        }else{
            return NewsArrayList.size();
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView judul_article, tanggal_buat;
        ImageView foto_article;
        CardView card_news;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            judul_article=itemView.findViewById(R.id.landing_judul_news);
            tanggal_buat=itemView.findViewById(R.id.landing_tgl_news);
            foto_article=itemView.findViewById(R.id.landing_img_news);
            card_news=itemView.findViewById(R.id.CardNews);
        }
    }
}
