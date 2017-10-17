package com.tasmuk.realmtraining;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;


public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0) {
            schema.get("Person")
                    .addField("catName", String.class);
            oldVersion++;
        }
    }
}
