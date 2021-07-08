package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectakhir.database.kost;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditList extends AppCompatActivity { //Class editlist
    private TextView key; //pendefinisian textview
    private EditText edNama, edTelpon, edIdentitas, edTk, edTs; //pendefinisian edittext
    private Button btnEdit; //pendefinisian button
    private DatabaseReference database; //pendefinisian databasereference
    String kode,nama,telpon,identitas,tk,ts; //pendeklarasian variabel dgn tipedata string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list); //menghubungkan activity dengan layout
        key = findViewById(R.id.tvkey); //mengakses textview dari layout
        edNama = findViewById(R.id.editNama); //mengakses edittext dari layout
        edTelpon = findViewById(R.id.editTelpon); //mengakses edittext dari layout
        edIdentitas = findViewById(R.id.editIdentitas); //mengakses edittext dari layout
        edTk = findViewById(R.id.editTipeKamar); //mengakses edittext dari layout
        edTs = findViewById(R.id.editTipeSewa); //mengakses edittext dari layout
        btnEdit = findViewById(R.id.btnEdt); //mengakses button dari layout

        database = FirebaseDatabase.getInstance().getReference(); //memberikan value untuk database reference

        Bundle bundle = this.getIntent().getExtras(); //mendapat akses data yang telah dikirim
        kode = bundle.getString("Kunci1"); //mengambil value dari kode
        nama = bundle.getString("Kunci2"); //mengambil value dari nama
        telpon = bundle.getString("Kunci3"); //mengambil value dari telpon
        identitas = bundle.getString("Kunci4"); //mengambil value dari identitas
        tk = bundle.getString("Kunci5"); //mengambil value dari tk
        ts = bundle.getString("Kunci6"); //mengambil value dari ts

        key.setText(kode); //menampilkan text ke textview
        edNama.setText(nama); //menampilkan text ke edittext
        edTelpon.setText(telpon); //menampilkan text ke edittext
        edIdentitas.setText(identitas); //menampilkan text ke edittext
        edTk.setText(tk); //menampilkan text ke edittext
        edTs.setText(ts); //menampilkan text ke edittext

        btnEdit.setOnClickListener(new View.OnClickListener() { //fungsi membaca klik pada button
            @Override
            public void onClick(View v) { //pembuatan method onclick
                editList(new kost(edNama.getText().toString(),edTelpon.getText().toString(),edIdentitas.getText().toString()
                ,edTk.getText().toString(),edTs.getText().toString())); //mengambil value
            }
        });
    }

    public void editList(kost kst)
    {
        database.child("Kost").child(kode).setValue(kst).addOnSuccessListener(this, new OnSuccessListener<Void>() { //pembuatan proses agar mengetahui data berhasil di-commit ke database
            @Override
            public void onSuccess(Void aVoid) { //pembuatan method onsuccess
                Toast.makeText(EditList.this, "Data Berhasil diUpdate",Toast.LENGTH_LONG).show(); //menampilkan toast
                finish(); //mengakhiri activity
            }
        });
    }
}