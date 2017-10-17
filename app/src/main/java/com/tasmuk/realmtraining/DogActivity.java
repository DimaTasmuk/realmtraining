package com.tasmuk.realmtraining;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tasmuk.realmtraining.models.Dog;

import io.realm.Realm;
import io.realm.RealmResults;


public class DogActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    Button btnSave, btnDelete;
    TextView tvDogs;
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        realm = Realm.getDefaultInstance();

        etName = (EditText) findViewById(R.id.etName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        tvDogs = (TextView) findViewById(R.id.tvDogs);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        showDogs();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                realm.executeTransactionAsync(realm1 -> {
                            String name = etName.getText().toString();
                            realm1.createObject(Dog.class, name);
                        }, () ->
                        { Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                            etName.setText("");
                            showDogs();
                        },
                        error -> Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show());
                break;
            case R.id.btnDelete:
                RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
                realm.beginTransaction();
                dogs.deleteAllFromRealm();
                realm.commitTransaction();
                tvDogs.setText("dogs...");
                break;
        }
    }

    private void showDogs() {
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        String text = "";
        for (Dog shownDog : dogs) {
            text += shownDog.toString() + "\n";
        }
        tvDogs.setText(text);
    }
}
