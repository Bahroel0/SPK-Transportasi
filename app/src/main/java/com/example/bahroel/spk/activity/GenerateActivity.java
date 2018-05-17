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
import com.example.bahroel.spk.generate.TransportationProblem;
import com.example.bahroel.spk.generate.Variable;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.model.WarehouseDestination;
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
    TextView totalValue;

    private final String TAG = GenerateActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        totalValue = (TextView) findViewById(R.id.tvTotalValue);

        recycler = (RecyclerView)findViewById(R.id.recyclerGenerate);
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        setupRecycler();

        // refresh the realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getGenerates());

        // proses generate
        RealmResults<WarehouseSource> sources = RealmController.with(GenerateActivity.this).getwhsources();
        RealmResults<WarehouseDestination> destinations = RealmController.with(GenerateActivity.this).getwhdestinations();
        RealmResults<Cost> costs = RealmController.with(GenerateActivity.this).getCostObject();

        TransportationProblem trans = new TransportationProblem(sources,destinations,costs);

        ArrayList<Variable> variables = trans.leastCostRule();
        ArrayList<Generate> generateArrayList = new ArrayList<>();

        for(int j=0; j< variables.size(); j++){
            Generate generate = new Generate();
            generate.setId(RealmController.getInstance().getGenerates().size()+1+j + System.currentTimeMillis());
            generate.setSrcName(variables.get(j).getSource().getSourceName());
            generate.setDstName(variables.get(j).getDesti().getDestinationName());
            generate.setCostValue(variables.get(j).getBiaya().getCost());
            generate.setAmount(String.valueOf(variables.get(j).getJumlah()));
            generateArrayList.add(generate);

        }

        for (Generate generate1 : generateArrayList) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(generate1);
            realm.copyToRealmOrUpdate(generate1);
            realm.commitTransaction();
        }

        totalValue.setText(String.valueOf(trans.getResultGenerate()));



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

}
