package com.example.bahroel.spk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.bahroel.spk.activity.R;
import com.example.bahroel.spk.adapters.CostAdapter;
import com.example.bahroel.spk.adapters.GenerateAdapter;
import com.example.bahroel.spk.adapters.RealmCostAdapter;
import com.example.bahroel.spk.adapters.RealmGenerateAdapter;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class GenerateActivity extends AppCompatActivity {

    private GenerateAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recycler;
    private final String TAG = GenerateActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        recycler = (RecyclerView)findViewById(R.id.recyclerGenerate);
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        setupRecycler();

        if (!Prefs.with(getApplicationContext()).getPreLoad()) {
            setRealmData();
        }

        // refresh the realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getGenerates());
    }

    public void setRealmAdapter(RealmResults<Generate> generate) {

        RealmGenerateAdapter realmAdapter = new RealmGenerateAdapter(this.getApplicationContext(), generate, true);
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
        adapter = new GenerateAdapter(this);
        recycler.setAdapter(adapter);
    }

    private void setRealmData() {
        ArrayList<Generate> whsources = new ArrayList<>();
//        whsources.clear();

        Generate whsrc = new Generate();
        whsrc.setId(1 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang A");
        whsrc.setDstName("Pabrik A");
        whsrc.setCostValue(4);
        whsrc.setAmount("3");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(2 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(3 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(4 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(5 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(6 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        whsrc = new Generate();
        whsrc.setId(7 + System.currentTimeMillis());
        whsrc.setSrcName("Gudang B");
        whsrc.setDstName("Pabrik B");
        whsrc.setCostValue(4);
        whsrc.setAmount("6");
        whsources.add(whsrc);

        for (Generate whs : whsources) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(whs);
            realm.copyToRealmOrUpdate(whs);
            realm.commitTransaction();
        }

        Prefs.with(this).setPreLoad(true);
    }
}
