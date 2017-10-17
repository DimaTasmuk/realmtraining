package com.tasmuk.realmtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tasmuk.realmtraining.retrofit.RetrofitActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPersons, btnDogs, btnRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPersons = (Button) findViewById(R.id.btnPersons);
        btnDogs = (Button) findViewById(R.id.btnDogs);
        btnRetrofit = (Button) findViewById(R.id.btnRetrofit);

        btnPersons.setOnClickListener(this);
        btnDogs.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPersons:
                startActivity(new Intent(getApplicationContext(), PersonActivity.class));
                break;
            case R.id.btnDogs:
                startActivity(new Intent(getApplicationContext(), DogActivity.class));
                break;
            case R.id.btnRetrofit:
                startActivity(new Intent(getApplicationContext(), RetrofitActivity.class));
                break;
        }
    }
}
