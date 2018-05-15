package com.example.bahroel.spk.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.bahroel.spk.adapters.CostAdapter;
import com.example.bahroel.spk.adapters.RealmCostAdapter;
import com.example.bahroel.spk.adapters.RealmWarehouseSourceAdapter;
import com.example.bahroel.spk.adapters.WarehouseSourceAdapter;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class CostActivity extends AppCompatActivity {

    private CostAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recycler;
    private final String TAG = CostActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        recycler = (RecyclerView)findViewById(R.id.recyclerbayar);
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        setupRecycler();

        if (!Prefs.with(this).getPreLoad()) {
            setRealmData();
        }

        // refresh the realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getCostObject());


    }

    public void setRealmAdapter(RealmResults<Cost> cost) {

        RealmCostAdapter realmAdapter = new RealmCostAdapter(this.getApplicationContext(), cost, true);
        // Set the data and tell the RecyclerView to draw
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler.setHasFixedSize(true);

        // use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // create an empty adapter and add it to the recycler view
        adapter = new CostAdapter(this);
        recycler.setAdapter(adapter);
    }

    private void setRealmData() {


        String[] source = new String[RealmController.getInstance().getwhsources().size()];
        String[] destination = new String[RealmController.getInstance().getwhdestinations().size()];


        ArrayList<Cost> costs = new ArrayList<>();

        for(int i=0; i<source.length; i++){
            for(int j=0; j<destination.length;j++){
                Cost cost = new Cost();
                cost.setId(1 + System.currentTimeMillis());
                cost.setCost(0);
                cost.setSourceName(source[i]);
                cost.setDestinationName(destination[j]);
                costs.add(cost);
            }
        }

        Log.d(TAG,"nilai costs : " + costs.toString());


        for (Cost whs : costs) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(whs);
            realm.commitTransaction();
        }

        Prefs.with(this).setPreLoad(true);

    }
}