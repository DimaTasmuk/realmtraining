package com.tasmuk.realmtraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tasmuk.realmtraining.models.Dog;
import com.tasmuk.realmtraining.models.Person;

import io.realm.Realm;
import io.realm.RealmResults;


public class PersonActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    EditText etName, etAge;
    Spinner spDogs;
    Button btnSave, btnDelete;
    TextView tvPersons;
    Realm realm;
    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        realm = Realm.getDefaultInstance();

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        spDogs = (Spinner) findViewById(R.id.spDogs);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        tvPersons = (TextView) findViewById(R.id.tvPersons);

        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        ArrayAdapter<Dog> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dogs);
        spDogs.setAdapter(adapter);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        spDogs.setOnItemSelectedListener(this);
        showPersons();
    }

    @Override
    public void onClick(View v) {
        RealmResults<Person> persons = realm.where(Person.class).findAll();
        switch (v.getId()) {
            case R.id.btnSave:
//                realm.executeTransactionAsync(realm1 -> {
//                    String name = etName.getText().toString();
//                    Integer age = !etAge.getText().toString().isEmpty() ? Integer.parseInt(etAge.getText().toString()) : 0;
//                    Person person = realm1.createObject(Person.class, name);
//                    person.setAge(age);
//                    person.setDog(dog);
//                },
//                        () -> { Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
//                            etName.setText("");
//                            showPersons();
//                        },
//                        error -> Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

                realm.beginTransaction();
                String name = etName.getText().toString();
                Integer age = !etAge.getText().toString().isEmpty() ? Integer.parseInt(etAge.getText().toString()) : 0;
                Person person = realm.createObject(Person.class, name);
                person.setAge(age);
                person.setDog(dog);
                showPersons();
                realm.commitTransaction();

                break;
            case R.id.btnDelete:
                realm.beginTransaction();
                persons.deleteAllFromRealm();
                realm.commitTransaction();
                tvPersons.setText("persons...");
                break;
        }
    }

    private void showPersons() {
        RealmResults<Person> persons = realm.where(Person.class).findAll();
        String text = "";
        for (Person shownPerson : persons) {
            text += shownPerson.toString() + "\n";
        }
        tvPersons.setText(text);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dog = (Dog) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
