package com.tasmuk.realmtraining;

import android.app.Application;

import io.realm.Realm;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .schemaVersion(1)
//                .migration(new MyMigration())
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.deleteRealm(config);
//        Realm.setDefaultConfiguration(config);
//        Toast.makeText(this, Realm.getDefaultInstance().getPath(), Toast.LENGTH_SHORT).show();
    }
}
