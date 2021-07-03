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

public class AdapterLihatList extends RecyclerView.Adapter<AdapterLihatList.ViewHolder> {
    private ArrayList<kost> listKost;
    private Context context;
    private DatabaseReference databaseReference;

    public AdapterLihatList(ArrayList<kost> kosts, Context ctx) {
        listKost = kosts;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String kode,nama,telpon,identitas,tk,ts;
        kode = listKost.get(position).getKode();
        nama = listKost.get(position).getNama();
        telpon = listKost.get(position).getTelpon();
        identitas = listKost.get(position).getIdentitas();
        tk = listKost.get(position).getTipekamar();
        ts = listKost.get(position).getTipesewa();

        final String name = listKost.get(position).getNama();
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.inflate(R.menu.popmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Bundle bundle = new Bundle();
                                bundle.putString("Kunci1",kode);
                                bundle.putString("Kunci2",nama);
                                bundle.putString("Kunci3",telpon);
                                bundle.putString("Kunci4",identitas);
                                bundle.putString("Kunci5",tk);
                                bundle.putString("Kunci6",ts);

                                Intent intent = new Intent(v.getContext(),EditList.class);
                                intent.putExtras(bundle);
                                v.getContext().startActivity(intent);
                                break;
                            case R.id.mnHapus:
                                AlertDialog.Builder alrt = new AlertDialog.Builder(v.getContext());
                                alrt.setTitle("Yakin data" +nama+" akan dihapus?");
                                alrt.setMessage("Tekan 'ya' untuk menghapus?").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DeleteData(kode);
                                        Toast.makeText(v.getContext(),"Data " +nama+ " berhasil dihapus",Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(v.getContext(),MainActivity.class);
                                        v.getContext().startActivity(i);
                                    }
                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog ad = alrt.create();
                                ad.show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        holder.tvTitle.setText(name);

    }

    @Override
    public int getItemCount() {
        return listKost.size();
    }

    public void DeleteData(String kode){
        if(databaseReference != null) {
            databaseReference.child("Kost").child(kode).removeValue();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public ViewHolder(View v) {
            super(v);
            tvTitle=(TextView) v.findViewById(R.id.tv_namalist);
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }
}

