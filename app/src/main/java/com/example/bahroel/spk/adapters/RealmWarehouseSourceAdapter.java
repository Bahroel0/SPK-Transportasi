package com.example.bahroel.spk.adapters;

import android.content.Context;

import com.example.bahroel.spk.model.WarehouseSource;

import io.realm.RealmResults;

public class RealmWarehouseSourceAdapter extends RealmModelAdapter<WarehouseSource> {
    public RealmWarehouseSourceAdapter(Context context, RealmResults<WarehouseSource> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
