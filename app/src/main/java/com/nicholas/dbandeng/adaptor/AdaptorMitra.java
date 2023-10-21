package com.nicholas.dbandeng.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nicholas.dbandeng.R;
import com.nicholas.dbandeng.detail_mitra;
import com.nicholas.dbandeng.edit_produk;
import com.nicholas.dbandeng.modul.ModulMitra;
import com.nicholas.dbandeng.modul.ModulMitraLP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptorMitra extends RecyclerView.Adapter<AdaptorMitra.myViewHolder> {

    ArrayList<ModulMitraLP> MitraArrayList;
    public AdaptorMitra(ArrayList<ModulMitraLP> MitraArrayList) {
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
        holder.nama_mitra.setText(MitraArrayList.get(position).getNamaMitra());
        Picasso.get().load(MitraArrayList.get(position).getFoto_mitra()).resize(300, 300).into(holder.foto_mitra);
        holder.btnMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), detail_mitra.class);
                intent.putExtra("id_mitra", MitraArrayList.get(position).getId());
                intent.putExtra("foto_mitra", MitraArrayList.get(position).getFoto_mitra());
                intent.putExtra("nama_mitra", MitraArrayList.get(position).getNamaMitra());
                intent.putExtra("no_telp", MitraArrayList.get(position).getNo_hp());
                intent.putExtra("alamat", MitraArrayList.get(position).getAlamat());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MitraArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nama_mitra;
        ImageView foto_mitra;
        Button btnMitra;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_mitra=itemView.findViewById(R.id.nama_mitra_home);
            foto_mitra=itemView.findViewById(R.id.foto_mitra_home);
            btnMitra=itemView.findViewById(R.id.mitra_home_btn);
        }
    }
}
