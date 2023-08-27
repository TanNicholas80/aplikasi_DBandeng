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
import com.example.dbandeng.modul.ModulMitra;

import java.util.ArrayList;

public class AdaptorMitra extends RecyclerView.Adapter<AdaptorMitra.myViewHolder> {

    ArrayList<ModulMitra> MitraArrayList;
    public AdaptorMitra(ArrayList<ModulMitra> MitraArrayList) {
        this.MitraArrayList = MitraArrayList;
    }

    @NonNull
    @Override
    public AdaptorMitra.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.layout_recycler_mitra,parent,false);
        return new AdaptorMitra.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptorMitra.myViewHolder holder, int position) {
        holder.nama_mitra.setText(MitraArrayList.get(position).getNama_mitra());
        holder.foto_mitra.setImageResource(R.drawable.juwana);
    }

    @Override
    public int getItemCount() {
        return MitraArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nama_mitra;
        ImageView foto_mitra;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_mitra=itemView.findViewById(R.id.nama_mitra_home);
            foto_mitra=itemView.findViewById(R.id.foto_mitra_home);
        }
    }
}
