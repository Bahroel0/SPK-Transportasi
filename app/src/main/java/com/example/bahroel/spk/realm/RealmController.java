package com.example.bahroel.spk.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(WarehouseSource.class);
        realm.clear(WarehouseDestination.class);
        realm.clear(Cost.class);
        realm.commitTransaction();
    }

    //find all objects in the WarehouseSource.class
    public RealmResults<WarehouseSource> getwhsources() {

        return realm.where(WarehouseSource.class).findAll();
    }

//    //find ssource from name
//    public RealmResults<WarehouseSource> getwhsourcesNAme() {
//
//        return realm.where(WarehouseSource.class).findAll();
//    }

    //find all objects in the WarehouseDestination.class
    public RealmResults<WarehouseDestination> getwhdestinations() {

        return realm.where(WarehouseDestination.class).findAll();
    }

    //find all objects in the WarehouseDestination.class
    public RealmResults<Cost> getCostObject() {

        return realm.where(Cost.class).findAll();
    }

    //query a single item with the given id
    public WarehouseSource getwhsource(String id) {

        return realm.where(WarehouseSource.class).equalTo("id", id).findFirst();
    }

    //query a single item with the given id
    public WarehouseDestination getwhdestination(String id) {

        return realm.where(WarehouseDestination.class).equalTo("id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean haswhsources() {

        return !realm.allObjects(WarehouseSource.class).isEmpty();
    }

    //check if Book.class is empty
    public boolean haswhdestinations() {

        return !realm.allObjects(WarehouseDestination.class).isEmpty();
    }

    //query example
    public RealmResults<WarehouseSource> queryedwhsources() {

        return realm.where(WarehouseSource.class)
                .contains("SourceName", "Author 0")
                .or()
                .contains("SourceAmount", "Realm")
                .findAll();

    }

    //query example
    public RealmResults<WarehouseDestination> queryedwhdestinations() {

        return realm.where(WarehouseDestination.class)
                .contains("DestinationName", "Author 0")
                .or()
                .contains("DestinationAmount", "Realm")
                .findAll();

    }
}
