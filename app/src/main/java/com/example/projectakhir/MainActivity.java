package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity { //class mainactivity
    ImageView list,daftar; //pendefinisian imageview
    Button about; //pendefinisian about

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //menghubungkan activity dengan layout

        list = findViewById(R.id.imageView); //mengakses imageview dari layout
        daftar = findViewById(R.id.imageView2); //mengakses imageview dari layout
        about = findViewById(R.id.btabout); //mengakses button dari layout

        list.setOnClickListener(new View.OnClickListener() { //fungsi membaca klik
            @Override
            public void onClick(View v) {
                startActivity(LihatList.getActIntent(MainActivity.this)); //menjalankan activity lihatlist
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() { //fungsi membaca klik
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LihatDaftar.class); //pendeklarasian intent untuk memanggil fungsi act lihatdaftar
                startActivity(intent);  //menjalankan act yang telah dipanggil lewat intent
            }
        });
        about.setOnClickListener(new View.OnClickListener() { //fungsi membaca klik
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LihatKost.class); //pendeklarasian intent untuk memanggil fungsi act lihatkost
                startActivity(i); //menjalankan act yang telah dipanggil lewat intent
            }
        });

    }
}