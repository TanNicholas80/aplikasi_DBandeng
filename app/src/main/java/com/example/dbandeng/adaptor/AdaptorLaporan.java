package com.example.dbandeng.adaptor;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbandeng.CRUD_Product;
import com.example.dbandeng.DetailProduk;
import com.example.dbandeng.InterfaceDbandeng;
import com.example.dbandeng.R;
import com.example.dbandeng.interfaceAPI.InterfaceNodeJs;
import com.example.dbandeng.koneksi.KoneksiNodeJs;
import com.example.dbandeng.koneksiAPI;
import com.example.dbandeng.modul.ModulLaporan;
import com.example.dbandeng.utils.WriteFileDownload;
import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulProduk;
import com.example.dbandeng.modul.ModulProdukNew;
import com.example.dbandeng.response.GetProductResponse;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaptorLaporan extends RecyclerView.Adapter<AdaptorLaporan.myViewHolder> {
    ArrayList<ModulLaporan> laporanArrayList;
    Context context;
    String TAG = "stream";
    int limit;
    boolean isLimited = false;
    public AdaptorLaporan(ArrayList<ModulLaporan> laporanArrayList) {
        this.laporanArrayList = laporanArrayList;
        this.limit = -1;
    }

    public void setLimit(int limit){
        this.isLimited = true;
        this.limit = limit;
    }

    public void onApplySearch(ArrayList<ModulLaporan> filteredProduct) {
        laporanArrayList = filteredProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptorLaporan.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_laporan,parent,false);
        return new AdaptorLaporan.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptorLaporan.myViewHolder holder, int position) {
        position = holder.getLayoutPosition();
        int finalPosition = position;
        ModulLaporan laporan = laporanArrayList.get(position);
        int totalJumlah = laporan.getKecil_count() + laporan.getSedang_count() + laporan.getBesar_count();
        String tanggalRaw = laporanArrayList.get(finalPosition).getTanggalRaw();
        holder.tanggal.setText(laporanArrayList.get(position).getTanggal());
        holder.totalBandeng.setText(totalJumlah+" ekor");
        holder.totalBerat.setText(laporanArrayList.get(position).getTotal_berat() +" gram");
        holder.totalHarga.setText("Rp. "+laporanArrayList.get(position).getTotal_harga());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SharedPreferences preferences = context.getSharedPreferences("my_preferences", context.MODE_PRIVATE);
                String idMitra = preferences.getString("id_mitra", null);
                DownloadManager downloadManager = (DownloadManager) v.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://dbandeng.luthficode.my.id/laporan?id="+idMitra+"&date="+tanggalRaw);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                String fileName = "dbandeng_" +tanggalRaw +"_"+new Random().nextInt(999) + ".pdf";
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                long downloadID = downloadManager.enqueue(request);

                File downloadLoc = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + fileName);

                NotificationManager notificationManager = (NotificationManager) v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext(), "1")
                        .setSmallIcon(R.drawable.icon_download)
                        .setContentTitle("Laporan Telah Diunduh")
                        .setContentText("Laporan "+ fileName + " sudah tersedia, silahkan klik notifikasi ini untuk membuka.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(getPendingIntent(v.getContext(), downloadLoc))
                        .setAutoCancel(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("1", "dbandeng", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }
                Log.d("dowwnloadd", builder.toString());
                notificationManager.notify(1, builder.build());
            }

            private PendingIntent getPendingIntent(Context context, File location) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.d("dowwnloadd", location.getPath());
                Uri pdfURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", location);
                intent.setDataAndType(pdfURI, "application/pdf");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE);
            }
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Laporan "+tanggalRaw+ ".pdf" + " sedang diunduh", Toast.LENGTH_LONG).show();
//                SharedPreferences preferences = context.getSharedPreferences("my_preferences", context.MODE_PRIVATE);
//                String idMitra = preferences.getString("id_mitra", null);
//                InterfaceNodeJs interfaceNodeJs = KoneksiNodeJs.Koneksi().create(InterfaceNodeJs.class);
//                Call<ResponseBody> getLaporan = interfaceNodeJs.downloaadFilePDF(idMitra,tanggalRaw);
//                getLaporan.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()) {
//                            Log.d(TAG, "server contacted and has file");
//
//                            boolean writtenToDisk = new WriteFileDownload().writeResponseBodyToDisk(response.body(),tanggalRaw);
//
//                            Log.d(TAG, "file download was a success? " + writtenToDisk);
//                            if(writtenToDisk){
//                                Toast.makeText(view.getContext(),"Laporan "+tanggalRaw+ ".pdf" + " berhasil diunduh", Toast.LENGTH_LONG).show();
//                            }else{
//                                Toast.makeText(view.getContext(),"Laporan "+tanggalRaw+ ".pdf" + " gagal diunduh", Toast.LENGTH_LONG).show();
//                            }
//
//                        } else {
//                            Log.d(TAG, "server contact failed");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(view.getContext(),"gagal get produk" + t.getMessage(), Toast.LENGTH_LONG);
//                        Log.d("crud_produk", "error" + t.getMessage());
//                    }
//                });
//            }
        });
    }

    @Override
    public int getItemCount() {
        if(isLimited){
            return Math.min(laporanArrayList.size(), limit);
        }else{
            return laporanArrayList.size();
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView tanggal, totalBandeng, totalBerat, totalHarga;
        MaterialButton button;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            tanggal = itemView.findViewById(R.id.laporanTanggal);
            totalBandeng = itemView.findViewById(R.id.laporanJumlah);
            totalBerat = itemView.findViewById(R.id.laporanBerat);
            totalHarga = itemView.findViewById(R.id.laporanHarga);
            button = itemView.findViewById(R.id.downloadItem);
        }
    }
}

