package com.example.dbandeng.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dbandeng.R;
import com.example.dbandeng.modul.ModulNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class landing_AdaptorNews extends RecyclerView.Adapter<landing_AdaptorNews.myViewHolder> {
    ArrayList<ModulNews> NewsArrayList;
    public landing_AdaptorNews(ArrayList<ModulNews> NewsArrayList) {
        this.NewsArrayList = NewsArrayList;
    }

    public void onApplySearch(ArrayList<ModulNews> NewsArrayList) {
        this.NewsArrayList = NewsArrayList;
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
        holder.tanggal_buat.setText(NewsArrayList.get(position).getCreated_at());
        Picasso.get().load(NewsArrayList.get(position).getFoto_article()).fit().centerCrop().into(holder.foto_article);
        //holder.foto_article.setImageResource(R.drawable.ikan_kecil);
    }

    @Override
    public int getItemCount() {
        return NewsArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView judul_article, tanggal_buat;
        ImageView foto_article;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            judul_article=itemView.findViewById(R.id.landing_judul_news);
            tanggal_buat=itemView.findViewById(R.id.landing_tgl_news);
            foto_article=itemView.findViewById(R.id.landing_img_news);
        }
    }
}
