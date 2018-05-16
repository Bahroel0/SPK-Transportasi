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

import com.example.bahroel.spk.realm.RealmController;

public class MainActivity extends AppCompatActivity {


    CardView cvSource, cvDestination, cvCost, cvGenerate;
    Button btnClearData;
    TextView tvinputdataasal, tvinputdatatujuan, tvBiayaa, tvgenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
