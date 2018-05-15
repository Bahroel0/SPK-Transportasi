package com.example.bahroel.spk.adapters;

import android.content.Context;

import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseSource;

import io.realm.RealmResults;

public class RealmCostAdapter extends RealmModelAdapter<Cost>{
    public RealmCostAdapter(Context context, RealmResults<Cost> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
