package com.nicholas.dbandeng.adaptor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.nicholas.dbandeng.InterfaceDbandeng;
import com.nicholas.dbandeng.R;
import com.nicholas.dbandeng.edit_produk;
import com.nicholas.dbandeng.koneksiAPI;
import com.nicholas.dbandeng.modul.ModulProdukNew;
import com.nicholas.dbandeng.response.deleteProdukRes;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class CRUD_AdaptorProduk extends RecyclerView.Adapter<CRUD_AdaptorProduk.myViewHolder> {
    ArrayList<ModulProdukNew> produkArrayList;
    private String authToken;
    public CRUD_AdaptorProduk(ArrayList<ModulProdukNew> produkArrayList, String authToken) {
        this.produkArrayList = produkArrayList;
        this.authToken = authToken;
    }
    @NonNull
    @Override
    public CRUD_AdaptorProduk.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.format_crud_produk,parent,false);
        return new CRUD_AdaptorProduk.myViewHolder(view);
    }

    public void onApplySearch(ArrayList<ModulProdukNew> searchList) {
        produkArrayList = searchList;
        Log.d("test1234",searchList.toString() );
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CRUD_AdaptorProduk.myViewHolder holder, int position) {
        holder.nama_produk.setText(produkArrayList.get(position).getNmProduk());
        holder.keterangan_produk.setText("Ukuran : " + produkArrayList.get(position).getBeratProduk());
        holder.stok_produk.setText("Stok : "+produkArrayList.get(position).getStok());
        holder.harga_produk.setText("Rp." + produkArrayList.get(position).getHrgProduk());
        Picasso.get().load(produkArrayList.get(position).getFoto_produk()).resize(300, 300).into(holder.foto_produk);
        holder.tblEditProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), edit_produk.class);
                intent.putExtra("id_produk", produkArrayList.get(position).getId());
                intent.putExtra("nama_produk", produkArrayList.get(position).getNmProduk());
                intent.putExtra("berat_produk", produkArrayList.get(position).getBeratProduk());
                intent.putExtra("stok_produk", produkArrayList.get(position).getStok());
                intent.putExtra("harga_produk", produkArrayList.get(position).getHrgProduk());
                intent.putExtra("foto_produk", produkArrayList.get(position).getFoto_produk());
                intent.putExtra("link_produk", produkArrayList.get(position).getLink());
                intent.putExtra("dsk_produk", produkArrayList.get(position).getDskProduk());

                // Start activity dengan intent
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.tblDeleteProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog deletePopUp = new Dialog(holder.itemView.getContext());
                deletePopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
                deletePopUp.setCancelable(false);
                deletePopUp.setContentView(R.layout.layout_popup_delete);
                deletePopUp.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                deletePopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button btnYaDel = deletePopUp.findViewById(R.id.btnYadelete);
                Button btnTidakDel = deletePopUp.findViewById(R.id.btnTidakdelete);

                btnYaDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InterfaceDbandeng interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng.class);
                        Context context = v.getContext();
                        int position = holder.getAdapterPosition();
                        String idProduk = produkArrayList.get(position).getId();
                        Call<deleteProdukRes> deleteProduk = interfaceDbandeng.deleteProduk("Bearer " + authToken, idProduk);
                        deleteProduk.enqueue(new Callback<deleteProdukRes>() {
                            @Override
                            public void onResponse(Call<deleteProdukRes> call, Response<deleteProdukRes> response) {
                                if(response.isSuccessful()) {
                                    produkArrayList.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(context, "Berhasil Menghapus Produk", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<deleteProdukRes> call, Throwable t) {
                                Toast.makeText(context, "Gagal Menghapus Produk", Toast.LENGTH_LONG).show();
                            }
                        });
                        deletePopUp.dismiss();
                    }
                });

                btnTidakDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletePopUp.dismiss();
                    }
                });
                deletePopUp.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, keterangan_produk, stok_produk, harga_produk;
        ImageView foto_produk;
//        CardView card_produk;
        ImageButton tblEditProduk, tblDeleteProduk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            keterangan_produk=itemView.findViewById(R.id.crud_keteranganProduk);
            stok_produk=itemView.findViewById(R.id.crud_stokProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
            harga_produk=itemView.findViewById(R.id.crud_hargaProduk);
//            card_produk=itemView.findViewById(R.id.card_CRUD_Product);
            tblEditProduk=itemView.findViewById(R.id.crud_btnEdit);
            tblDeleteProduk=itemView.findViewById(R.id.crud_btnHapus);
        }
    }
}
