package com.example.projectakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectakhir.database.kost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatList extends AppCompatActivity { //class lihatlist
    private RecyclerView view; //pendefinisian recyclerview
    private RecyclerView.Adapter adapter; //pendefinisian recyclerview.adapter
    private RecyclerView.LayoutManager layoutManager; //pendefinisian recyclerview.layoutmanager
    private ArrayList<kost> listKost; //Pendefinisian arraylist dengan nama listkost
    private DatabaseReference databaseReference; //pendeklarasian databasereference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_list); //menghubungkan activity dengan layout

        view = findViewById(R.id.rv_main); //mengakses recyclerview dari layout
        view.setHasFixedSize(true); //memastikan bahwa perubahan ukuran RecyclerView konstan
        layoutManager = new LinearLayoutManager(this); //pendeklarasian linierlayoutmanager
        view.setLayoutManager(layoutManager); //membuat recyclerview

        databaseReference = FirebaseDatabase.getInstance().getReference(); //memberikan value untuk database reference
        databaseReference.child("Kost").addValueEventListener(new ValueEventListener() { //menambahkan valueeventlistener ke database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listKost = new ArrayList<>(); //pendeklarasian arraylist
                for (DataSnapshot noteDataSnapshot : snapshot.getChildren()) {

                    kost kst = noteDataSnapshot.getValue(kost.class); //mapping data pada datasnapshot ke dalam object kst
                    kst.setKode(noteDataSnapshot.getKey()); //menyimpan primary key pada object kst untuk edit dan hapus
                    listKost.add(kst); //menambahkan object kst yang sdh dimapping ke arraylist
                }
                adapter = new AdapterLihatList(listKost,LihatList.this); //inisiasi adapter ke dalam bentuk arraylist
                view.setAdapter(adapter); //mengeset adapter ke dalam recyclerview
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getDetails()+" "+error.getMessage()); //memanggil error dan ngeprint errornya
            }
        });

    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, LihatList.class); //mengembalikan nilai intent
    }
}