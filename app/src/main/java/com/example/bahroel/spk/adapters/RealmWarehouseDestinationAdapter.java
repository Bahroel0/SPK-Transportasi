package com.example.bahroel.spk.adapters;

import android.content.Context;

import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;

import io.realm.RealmResults;

public class RealmWarehouseDestinationAdapter extends RealmModelAdapter<WarehouseDestination>{
    public RealmWarehouseDestinationAdapter(Context context, RealmResults<WarehouseDestination> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
