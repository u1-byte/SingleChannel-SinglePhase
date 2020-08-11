package com.dicoding.picodiploma.singlechannel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPenjelasan = findViewById(R.id.desc);
        btnPenjelasan.setOnClickListener(this);

        Button btnRumus = findViewById(R.id.rumus);
        btnRumus.setOnClickListener(this);

        Button btnHitung = findViewById(R.id.hitung);
        btnHitung.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.desc:
                Intent Penjelasan = new Intent(MainActivity.this, deskripsi.class);
                startActivity(Penjelasan);
                break;

            case R.id.rumus:
                Intent Rumus = new Intent(MainActivity.this, rumus.class);
                startActivity(Rumus);
                break;

            case R.id.hitung:
                Intent Perhitungan = new Intent(MainActivity.this, perhitungan.class);
                startActivity(Perhitungan);
                break;
        }

    }
}
