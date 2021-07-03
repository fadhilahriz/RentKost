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

public class LihatList extends AppCompatActivity {
    private RecyclerView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<kost> listKost;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_list);

        view = findViewById(R.id.rv_main);
        view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Kost").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listKost = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : snapshot.getChildren()) {
                    kost kst = noteDataSnapshot.getValue(kost.class);
                    kst.setKode(noteDataSnapshot.getKey());
                    listKost.add(kst);
                }
                adapter = new AdapterLihatList(listKost,LihatList.this);
                view.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getDetails()+" "+error.getMessage());
            }
        });

    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, LihatList.class);
    }
}