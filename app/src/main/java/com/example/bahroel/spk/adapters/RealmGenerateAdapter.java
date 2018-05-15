package com.example.bahroel.spk.adapters;

import android.content.Context;

import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.model.WarehouseSource;

import io.realm.RealmResults;

public class RealmGenerateAdapter extends RealmModelAdapter<Generate> {
    public RealmGenerateAdapter(Context context, RealmResults<Generate> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
