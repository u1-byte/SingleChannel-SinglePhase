package com.dicoding.picodiploma.singlechannel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class perhitungan extends AppCompatActivity implements View.OnClickListener{
    private EditText arrRate;
    private EditText serRate;
    private EditText serCost;
    private EditText waitCost;
    private EditText numSer;
    private Button Calculate;
    private TextView Result;
    private static final String HASIL = "hasil";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(HASIL, Result.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan);

        arrRate = findViewById(R.id.arrival_rate);
        serRate = findViewById(R.id.service_rate);
        Calculate = findViewById(R.id.calculate);
        serCost = findViewById(R.id.service_cost);
        waitCost = findViewById(R.id.waiting_cost);
        numSer = findViewById(R.id.number_service);
        Result = findViewById(R.id.result);
        Calculate.setOnClickListener(this);
        if (savedInstanceState != null){
            String result = savedInstanceState.getString(HASIL);
            Result.setText(result);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.calculate){
            String inputArr = arrRate.getText().toString().trim();
            String inputSer = serRate.getText().toString().trim();
            String inputSC = serCost.getText().toString().trim();
            String inputWC = waitCost.getText().toString().trim();
            String inputN = numSer.getText().toString().trim();
            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;
            if (TextUtils.isEmpty(inputArr)) {
                isEmptyFields = true;
                arrRate.setError("Field ini tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(inputSer)) {
                isEmptyFields = true;
                serRate.setError("Field ini tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(inputSC)) {
                isEmptyFields = true;
                serCost.setError("Field ini tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(inputWC)) {
                isEmptyFields = true;
                waitCost.setError("Field ini tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(inputN)) {
                isEmptyFields = true;
                numSer.setError("Field ini tidak boleh kosong!");
            }
            Double arrival = toDouble(inputArr);
            Double service = toDouble(inputSer);
            Double sc = toDouble(inputSC);
            Double wc = toDouble(inputWC);
            Double n = toDouble(inputN);
            if (arrival == null) {
                isInvalidDouble = true;
                arrRate.setError("Field ini harus berupa angka yang valid!");
            }
            if (service == null) {
                isInvalidDouble = true;
                serRate.setError("Field ini harus berupa angka yang valid!");
            }
            if (sc == null) {
                isInvalidDouble = true;
                serCost.setError("Field ini harus berupa angka yang valid!");
            }
            if (wc == null) {
                isInvalidDouble = true;
                waitCost.setError("Field ini harus berupa angka yang valid!");
            }
            if (n == null) {
                isInvalidDouble = true;
                numSer.setError("Field ini harus berupa angka yang valid!");
            }
            if (!isEmptyFields && !isInvalidDouble) {
                service = service * n;
                double L = arrival / (service - arrival);
                double Lq = arrival * arrival / (service * (service - arrival));
                double W = 1 / (service - arrival);
                double Wq = arrival / (service * (service - arrival));
                double p = arrival / service;
                double Po = 1 - p;
                double cs = sc * n;
                double cw = wc * L;
                double tc = cs + cw;

                Result.setText("Tingkat Intensitas Pelayanan = \n" + p +
                        "\n\nRata - rata jumlah unit dalam sistem = \n" + L +
                        "\n\nRata - rata jumlah unit dalam antrian = \n" + Lq +
                        "\n\nRata - rata waktu unit dalam sistem = \n" + W +
                        "\n\nRata - rata waktu tunggu unit dalam antrian = \n" + Wq +
                        "\n\nProbabilitas tidak ada unit dalam sistem = \n" + Po +
                        "\n\nTotal biaya pelayanan = \nRp. " + cs +
                        "\n\nTotal biaya tunggu = \nRp." + cw +
                        "\n\nTotal biaya yang dikeluarkan per satuan waktu = \nRp." + tc);
            }
        }
    }
    private Double toDouble(String str){
        try{
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }
}
