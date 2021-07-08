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

public class LihatDaftar extends AppCompatActivity { //class lihatdaftar
    private EditText edNama, edTelpon, edIdentitas, edTk, edTs; //pendefinisian edittext
    private Button btnorder; //pendefinisian button
    private DatabaseReference database; //pendefinisian databasereference
    String nm, tlp, id, tk, ts; //pendeklarasian variabel dengan tipedata string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_daftar); //menghubungkan activity dengan layout

        edNama = findViewById(R.id.editNama); //mrngakses edittext dari layout
        edTelpon = findViewById(R.id.editTelpon); //mrngakses edittext dari layout
        edIdentitas = findViewById(R.id.editIdentitas); //mrngakses edittext dari layout
        edTk = findViewById(R.id.editTipeKamar); //mrngakses edittext dari layout
        edTs = findViewById(R.id.editTipeSewa); //mrngakses edittext dari layout
        btnorder = findViewById(R.id.btnOk); //mengakses button dari layout

        database = FirebaseDatabase.getInstance().getReference(); //memberikan value untuk database reference
        btnorder.setOnClickListener(new View.OnClickListener() { //fungsi membaca klik pada button
            @Override
            public void onClick(View v) { //pembuatan method onclick
                if (!(edNama.getText().toString().isEmpty()) && !(edTelpon.getText().toString().isEmpty())
                && !(edIdentitas.getText().toString().isEmpty()) && !(edTk.getText().toString().isEmpty()) && !(edTs.getText().toString().isEmpty())) //fungsi if
                {
                    nm = edNama.getText().toString(); //mengambil value dari ednama
                    tlp = edTelpon.getText().toString(); //mengambil value dari edtelpon
                    id = edIdentitas.getText().toString(); //mengambil value dari edidentitas
                    tk = edTk.getText().toString(); //mengambil value dari edtk
                    ts = edTs.getText().toString(); //mengambil value dari edts
                    submitKost(new kost(nm,tlp,id,tk,ts)); //menjalankan method submitkost
                }
                else //fungsi else
                    Toast.makeText(LihatDaftar.this, "Data tidak boleh kosong",Toast.LENGTH_SHORT).show(); //menampilkan toast
            }
        });
    }

    private void submitKost(kost kst) //pembuatan method submitkost
    {
        database.child("Kost").push().setValue(kst).addOnSuccessListener(this, new OnSuccessListener<Void>() { //ngepush dan pembuatan proses agar mengetahui data berhasil di-commit ke database
            @Override
            public void onSuccess(Void aVoid) {
                edNama.setText(""); //menampilkan text
                edTelpon.setText(""); //menampilkan text
                edIdentitas.setText(""); //menampilkan text
                edTk.setText(""); //menampilkan text
                edTs.setText(""); //menampikan text
                Toast.makeText(LihatDaftar.this, "Data sukses ditambahkan",Toast.LENGTH_SHORT).show(); //menampilkan toast
            }
        });
    }
}