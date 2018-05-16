package com.example.bahroel.spk.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    CardView cvSource, cvDestination, cvCost, cvGenerate;
    Button btnClearData;
    TextView tvinputdataasal, tvinputdatatujuan, tvBiayaa, tvgenerate;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.realm = RealmController.with(this).getRealm();
        if (!Prefs.with(getApplicationContext()).getPreLoad()) {
            setRealmData();
        }

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        tvinputdataasal = (TextView)findViewById(R.id.tvinputdataasal);
        tvinputdatatujuan = (TextView)findViewById(R.id.tvinputdatatujuan);
        tvBiayaa = (TextView)findViewById(R.id.tvbiayaaaaaa);
        tvgenerate = (TextView)findViewById(R.id.tvgeneratedata);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/berlinsans.ttf");
        tvinputdataasal.setTypeface(custom_font);
        tvinputdatatujuan.setTypeface(custom_font);
        tvBiayaa.setTypeface(custom_font);
        tvgenerate.setTypeface(custom_font);

        cvSource = (CardView) findViewById(R.id.clickSource);
        cvDestination = (CardView) findViewById(R.id.clickDestination);
        btnClearData = (Button) findViewById(R.id.clearData);
        cvCost = (CardView)findViewById(R.id.clickCost);
        cvGenerate = (CardView) findViewById(R.id.clickGenerate);

        cvCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cvsrc = new Intent(getApplicationContext(), CostActivity.class);
                cvsrc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cvsrc);
            }
        });

        cvGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cvgrt = new Intent(getApplicationContext(), GenerateActivity.class);
                cvgrt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cvgrt);
            }
        });

        cvSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cvsrc = new Intent(getApplicationContext(), HomeActivity.class);
                cvsrc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cvsrc);
            }
        });

        cvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cvdst = new Intent(getApplicationContext(), DestinationActivity.class);
                cvdst.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cvdst);
            }
        });

        btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmController.getInstance().clearAll();
                Toast.makeText(getApplicationContext(), "Success to delete all data ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setRealmData() {
        ArrayList<WarehouseSource> whsources = new ArrayList<>();
        WarehouseSource whsrc = new WarehouseSource();
        whsrc.setId(1 + System.currentTimeMillis());
        whsrc.setSourceName("Gudang A");
        whsrc.setSourceAmount("4");
        whsources.add(whsrc);

        whsrc = new WarehouseSource();
        whsrc.setId(2 + System.currentTimeMillis());
        whsrc.setSourceName("Gudang B");
        whsrc.setSourceAmount("5");
        whsources.add(whsrc);

        whsrc = new WarehouseSource();
        whsrc.setId(3 + System.currentTimeMillis());
        whsrc.setSourceName("Gudang C");
        whsrc.setSourceAmount("5");
        whsources.add(whsrc);


        for (WarehouseSource whs : whsources) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(whs);
            realm.commitTransaction();
        }








        ArrayList<Generate> generates = new ArrayList<>();
        Generate generate = new Generate();
        generate.setId(1 + System.currentTimeMillis());
        generate.setSrcName("Gudang A");
        generate.setDstName("Pabrik A");
        generate.setCostValue(4);
        generate.setAmount("3");
        generates.add(generate);

        generate = new Generate();
        generate.setId(2 + System.currentTimeMillis());
        generate.setSrcName("Gudang A");
        generate.setDstName("Pabrik A");
        generate.setCostValue(4);
        generate.setAmount("3");
        generates.add(generate);



        for (Generate generat : generates) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(generat);
            realm.copyToRealmOrUpdate(generat);
            realm.commitTransaction();
        }



        ArrayList<Cost> costArrayList = new ArrayList<>();
        Cost cost = new Cost();
        cost.setId(1 + System.currentTimeMillis());
        cost.setSourceName("PT SAWIT");
        cost.setDestinationName("Pabrik A");
        cost.setCost(5);
        costArrayList.add(cost);
//
        cost = new Cost();
        cost.setId(2 + System.currentTimeMillis());
        cost.setSourceName("PT SAWITasdasd");
        cost.setDestinationName("Pabrik b");
        cost.setCost(5);
        costArrayList.add(cost);

//
//
        for (Cost cost1 : costArrayList) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(cost1);
            realm.copyToRealmOrUpdate(cost1);
            realm.commitTransaction();
        }


        ArrayList<WarehouseDestination> whdestination = new ArrayList<>();

//        whdestination.clear();
        WarehouseDestination whdst = new WarehouseDestination();
        whdst.setId(1 + System.currentTimeMillis());
        whdst.setDestinationName("PT SAWIT");
        whdst.setDestinationAmount("4");
        whdestination.add(whdst);

        whdst = new WarehouseDestination();
        whdst.setId(2 + System.currentTimeMillis());
        whdst.setDestinationName("PT KELAPA MUDA");
        whdst.setDestinationAmount("5");
        whdestination.add(whdst);

        whdst = new WarehouseDestination();
        whdst.setId(3 + System.currentTimeMillis());
        whdst.setDestinationName("PT PLN");
        whdst.setDestinationAmount("6");
        whdestination.add(whdst);

        whdst = new WarehouseDestination();
        whdst.setId(4 + System.currentTimeMillis());
        whdst.setDestinationName("PT KELOPO");
        whdst.setDestinationAmount("7");
        whdestination.add(whdst);

        whdst = new WarehouseDestination();
        whdst.setId(5 + System.currentTimeMillis());
        whdst.setDestinationName("PT SEJAHTERA");
        whdst.setDestinationAmount("8");
        whdestination.add(whdst);


        for (WarehouseDestination whd : whdestination) {
            // Persist your data easily
            realm.beginTransaction();
            realm.copyToRealm(whd);
            realm.commitTransaction();
        }

        Prefs.with(this).setPreLoad(true);
    }
}
