package br.edu.iff.pooa20181.safepass;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Start extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("SafePass.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}
