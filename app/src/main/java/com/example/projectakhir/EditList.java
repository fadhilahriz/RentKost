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

public class EditList extends AppCompatActivity {
    private TextView key;
    private EditText edNama, edTelpon, edIdentitas, edTk, edTs;
    private Button btnEdit;
    private DatabaseReference database;
    String kode,nama,telpon,identitas,tk,ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        key = findViewById(R.id.tvkey);
        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);
        edIdentitas = findViewById(R.id.editIdentitas);
        edTk = findViewById(R.id.editTipeKamar);
        edTs = findViewById(R.id.editTipeSewa);
        btnEdit = findViewById(R.id.btnEdt);

        database = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("Kunci1");
        nama = bundle.getString("Kunci2");
        telpon = bundle.getString("Kunci3");
        identitas = bundle.getString("Kunci4");
        tk = bundle.getString("Kunci5");
        ts = bundle.getString("Kunci6");

        key.setText(kode);
        edNama.setText(nama);
        edTelpon.setText(telpon);
        edIdentitas.setText(identitas);
        edTk.setText(tk);
        edTs.setText(ts);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editList(new kost(edNama.getText().toString(),edTelpon.getText().toString(),edIdentitas.getText().toString()
                ,edTk.getText().toString(),edTs.getText().toString()));
            }
        });
    }

    public void editList(kost kst)
    {
        database.child("Kost").child(kode).setValue(kst).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditList.this, "Data Berhasil diUpdate",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}