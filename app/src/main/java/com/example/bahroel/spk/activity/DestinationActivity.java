package com.example.bahroel.spk.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.bahroel.spk.adapters.RealmWarehouseDestinationAdapter;
import com.example.bahroel.spk.adapters.RealmWarehouseSourceAdapter;
import com.example.bahroel.spk.adapters.ViewPagerDestinationAdapter;
import com.example.bahroel.spk.adapters.ViewPagerSourceAdapter;
import com.example.bahroel.spk.adapters.WarehouseDestinationAdapter;
import com.example.bahroel.spk.adapters.WarehouseSourceAdapter;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class DestinationActivity extends AppCompatActivity {

    private WarehouseDestinationAdapter adapter;
    private Realm realm;
    private FloatingActionButton fabmain;
    private LayoutInflater inflater;
    private RecyclerView recycler;
    ViewFlipper viewFlipper;

    ViewPager viewPager;
    int images[] = {R.drawable.tujuan1, R.drawable.tujuan2,R.drawable.tujuan3};
    ViewPagerDestinationAdapter viewpagerdestinationadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewFlipper = (ViewFlipper)findViewById(R.id.viewflipperDesti);
        for(int image:images){
            flipperImage(image);
        }

        viewpagerdestinationadapter = new ViewPagerDestinationAdapter(DestinationActivity.this, images);

        fabmain = (FloatingActionButton) findViewById(R.id.fabDst);
        recycler = (RecyclerView) findViewById(R.id.recyclerDst);

        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        setupRecycler();

        // refresh the realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getwhdestinations());

        Toast.makeText(this, "Klik untuk edit data, tekan untuk hapus", Toast.LENGTH_LONG).show();
        fabmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                inflater = DestinationActivity.this.getLayoutInflater();
                View content = inflater.inflate(R.layout.edit_item_destination, null);
                final EditText editName = (EditText) content.findViewById(R.id.etNamaDst);
                final EditText editAmount = (EditText) content.findViewById(R.id.etJumlahDst);
                editAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder = new AlertDialog.Builder(DestinationActivity.this);
                builder.setView(content)
                        .setTitle("Tambahkan Data Tujuan")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                WarehouseDestination warehouseDestination = new WarehouseDestination();
                                //book.setId(RealmController.getInstance().getBooks().size() + 1);
                                warehouseDestination.setId(RealmController.getInstance().getwhdestinations().size() + System.currentTimeMillis());
                                warehouseDestination.setDestinationName(editName.getText().toString());
                                warehouseDestination.setDestinationAmount(editAmount.getText().toString());

                                if (editName.getText() == null || editName.getText().toString().isEmpty() || editName.getText().toString().equals(" ") || editAmount.getText().toString().isEmpty()) {
                                    Toast.makeText(DestinationActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Persist your data easily
                                    realm.beginTransaction();
                                    realm.copyToRealm(warehouseDestination);
                                    realm.commitTransaction();
                                    adapter.notifyDataSetChanged();

                                    // scroll the recycler view to bottom
                                    recycler.scrollToPosition(RealmController.getInstance().getwhdestinations().size() - 1);

                                    RealmController.with(DestinationActivity.this).refresh();

                                    ArrayList<Cost> costArrayList = new ArrayList<>();
                                    RealmResults<WarehouseSource> warehouseSources = RealmController.with(DestinationActivity.this).getwhsources();

                                    for(int i=0; i<warehouseSources.size(); i++){
                                        Cost cost = new Cost();
                                        cost.setId(RealmController.getInstance().getCostObject().size()+i+1+ System.currentTimeMillis());
                                        cost.setSourceName(warehouseSources.get(i).getSourceName());
                                        cost.setDestinationName(editName.getText().toString());
                                        cost.setCost(0);
                                        cost.setIsset(false);
                                        costArrayList.add(cost);
                                    }

                                    for (Cost cost1 : costArrayList) {
                                        // Persist your data easily
                                        realm.beginTransaction();
                                        realm.copyToRealm(cost1);
                                        realm.copyToRealmOrUpdate(cost1);
                                        realm.commitTransaction();
                                    }

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void setRealmAdapter(RealmResults<WarehouseDestination> warehousedestinations) {

        RealmWarehouseDestinationAdapter realmAdapter = new RealmWarehouseDestinationAdapter(this.getApplicationContext(), warehousedestinations, true);
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
        adapter = new WarehouseDestinationAdapter(this);
        recycler.setAdapter(adapter);
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }


}
