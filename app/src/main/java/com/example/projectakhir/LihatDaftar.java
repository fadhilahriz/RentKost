package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectakhir.database.kost;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LihatDaftar extends AppCompatActivity {
    private EditText edNama, edTelpon, edIdentitas, edTk, edTs;
    private Button btnorder;
    private DatabaseReference database;
    String nm, tlp, id, tk, ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_daftar);

        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);
        edIdentitas = findViewById(R.id.editIdentitas);
        edTk = findViewById(R.id.editTipeKamar);
        edTs = findViewById(R.id.editTipeSewa);
        btnorder = findViewById(R.id.btnOk);

        database = FirebaseDatabase.getInstance().getReference();
        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edNama.getText().toString().isEmpty()) && !(edTelpon.getText().toString().isEmpty())
                && !(edIdentitas.getText().toString().isEmpty()) && !(edTk.getText().toString().isEmpty()) && !(edTs.getText().toString().isEmpty()))
                {
                    nm = edNama.getText().toString();
                    tlp = edTelpon.getText().toString();
                    id = edIdentitas.getText().toString();
                    tk = edTk.getText().toString();
                    ts = edTs.getText().toString();
                    submitKost(new kost(nm,tlp,id,tk,ts));
                }
                else
                    Toast.makeText(LihatDaftar.this, "Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitKost(kost kst)
    {
        database.child("Kost").push().setValue(kst).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                edNama.setText("");
                edTelpon.setText("");
                edIdentitas.setText("");
                edTk.setText("");
                edTs.setText("");
                Toast.makeText(LihatDaftar.this, "Data sukses ditambahkan",Toast.LENGTH_SHORT).show();
            }
        });
    }
}