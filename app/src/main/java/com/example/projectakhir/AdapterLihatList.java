package com.example.projectakhir;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectakhir.database.kost;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLihatList extends RecyclerView.Adapter<AdapterLihatList.ViewHolder> { //pembuatan class AdapterLihatList yang extend ke RecyclerView.Adapter
    private ArrayList<kost> listKost; //Pendefinisian arraylist dengan nama listkost
    private Context context; //Pendefinisian context dengan nama context
    private DatabaseReference databaseReference; //Pendefinisian DatabaseReference dengan nama databaseReference

    public AdapterLihatList(ArrayList<kost> kosts, Context ctx) {
        listKost = kosts; //inisiasi data dan variabel
        context = ctx; //inisiasi data dan variabel
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false); //pendeklarasian layoutinflater agar layout itrm_list dapat tampil dilayout activity ini
        ViewHolder vh = new ViewHolder(view); //membuat viewholder baru
        return vh; //mengembalikan nilai vh
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String kode,nama,telpon,identitas,tk,ts; //pendeklarasian variabel dengan tipe data string
        kode = listKost.get(position).getKode(); //variabel kode untuk menyimpan kode
        nama = listKost.get(position).getNama(); //variabel nama untuk menyimpan nama
        telpon = listKost.get(position).getTelpon(); //variabel telpon untuk menyimpan telpon
        identitas = listKost.get(position).getIdentitas(); //variabel identitas untuk menyimpan identitas
        tk = listKost.get(position).getTipekamar(); //variabel tk untuk menyimpan tipekamar
        ts = listKost.get(position).getTipesewa(); //variabel ts untuk menyimpan tipesewa

        final String name = listKost.get(position).getNama();
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() { //pembuatan setonlongclick
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v); //pendeklarasian popupmenu
                popupMenu.inflate(R.menu.popmenu); //menghubungkan popupmenu dengan layout menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { //pembuatan event setonmenuitemclick
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){ //pembuatan switch-case
                            case R.id.mnEdit:
                                Bundle bundle = new Bundle(); //pendeklarasian bundle
                                bundle.putString("Kunci1",kode); //mengirimkan value dari kode
                                bundle.putString("Kunci2",nama); //mengirimkan value dari nama
                                bundle.putString("Kunci3",telpon); //mengirimkan value dari telpon
                                bundle.putString("Kunci4",identitas); //mengirimkan value dari identitas
                                bundle.putString("Kunci5",tk); //mengirimkan value dari tk
                                bundle.putString("Kunci6",ts); //mengirimkan value dari ts

                                Intent intent = new Intent(v.getContext(),EditList.class); //pendeklarasian intent untuk memanggil fungsi act editlist
                                intent.putExtras(bundle); //mengirimkan bundle
                                v.getContext().startActivity(intent); //memulai activity yang telah dideklarasikan dari intent
                                break;
                            case R.id.mnHapus:
                                AlertDialog.Builder alrt = new AlertDialog.Builder(v.getContext()); //pendeklarasian alertdialog
                                alrt.setTitle("Yakin data" +nama+" akan dihapus?"); //pembuatan title
                                alrt.setMessage("Tekan 'ya' untuk menghapus?").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() { //pembuatan message
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { //pembuatan method onclick
                                        DeleteData(kode); //memanggil method deletedata
                                        Toast.makeText(v.getContext(),"Data " +nama+ " berhasil dihapus",Toast.LENGTH_LONG).show(); //pembuatan toast untuk memunculkan text
                                        Intent i = new Intent(v.getContext(),MainActivity.class); //pendeklarasian intent  untuk memanggil fungsi act mainactivity
                                        v.getContext().startActivity(i); //memulai activity yang telah dideklarasikan dari intent
                                    }
                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() { //menampilkan opsi tidak yang akan menutup dialog
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { //pembuatan method onclick
                                        dialog.cancel(); //menjalankan dialog cancel
                                    }
                                });
                                AlertDialog ad = alrt.create(); //membuat alert dialog dari builder
                                ad.show(); //menampilkan alertdialog
                                break;
                        }
                        return true; //mengembalikan nilai true
                    }
                });
                popupMenu.show(); //menampilkan popupmenu
                return true; //mengembalikan nilai true
            }
        });
        holder.tvTitle.setText(name); //menampilkan teks

    }

    @Override
    public int getItemCount() {
        return listKost.size();
    }

    public void DeleteData(String kode){ //membuat method deletedata
        if(databaseReference != null) {
            databaseReference.child("Kost").child(kode).removeValue(); //menjalankan fungsi removeValue untuk menghapus data dari database
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{ //pembuatan class viewholder yang extend ke RecyclerView.viewHolder
        TextView tvTitle; //pendefinisian textview
        public ViewHolder(View v) {
            super(v); //mengeksekusi konstruktor
            tvTitle=(TextView) v.findViewById(R.id.tv_namalist); //mengakses textview dari layout
            databaseReference = FirebaseDatabase.getInstance().getReference(); //memberikan value untuk database reference
        }
    }
}

