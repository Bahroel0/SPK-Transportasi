package com.example.bahroel.spk.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.bahroel.spk.adapters.RealmWarehouseDestinationAdapter;
import com.example.bahroel.spk.adapters.RealmWarehouseSourceAdapter;
import com.example.bahroel.spk.adapters.ViewPagerDestinationAdapter;
import com.example.bahroel.spk.adapters.ViewPagerSourceAdapter;
import com.example.bahroel.spk.adapters.WarehouseDestinationAdapter;
import com.example.bahroel.spk.adapters.WarehouseSourceAdapter;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {
    private WarehouseSourceAdapter adapter;
    private Realm realm;
    ViewFlipper viewFlipper;
    private FloatingActionButton fabmain;
    private LayoutInflater inflater;
    private RecyclerView recycler;
    private LinearLayout linearLayout;

    ViewPager viewPager;
    int images[] = {R.drawable.asal1, R.drawable.asal2,R.drawable.asal3};
    ViewPagerSourceAdapter viewpagersourceadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipperSource);
        for(int image:images){
            flipperImage(image);
        }

        linearLayout = (LinearLayout)findViewById(R.id.linearLayoutbahroel);

        viewpagersourceadapter = new ViewPagerSourceAdapter(HomeActivity.this, images);
//        viewPager.setAdapter(viewpagersourceadapter);

        fabmain = (FloatingActionButton) findViewById(R.id.fabmain);
        recycler = (RecyclerView) findViewById(R.id.recyclerSrc);

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
        setRealmAdapter(RealmController.with(this).getwhsources());

        Toast.makeText(this, "Press card item for edit, long press to remove item", Toast.LENGTH_LONG).show();
        fabmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                inflater = HomeActivity.this.getLayoutInflater();
                View content = inflater.inflate(R.layout.edit_item, null);
                final EditText editName = (EditText) content.findViewById(R.id.etNamaSrc);
                final EditText editAmount = (EditText) content.findViewById(R.id.etJumlahSrc);

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setView(content)
                        .setTitle("Add Warehouse Source")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                WarehouseSource warehouseSource = new WarehouseSource();
                                //book.setId(RealmController.getInstance().getBooks().size() + 1);
                                warehouseSource.setId(RealmController.getInstance().getwhdestinations().size() + System.currentTimeMillis());
                                warehouseSource.setSourceName(editName.getText().toString());
                                warehouseSource.setSourceAmount(editAmount.getText().toString());

                                if (editName.getText() == null || editName.getText().toString().equals("") || editName.getText().toString().equals(" ")) {
                                    Toast.makeText(HomeActivity.this, "Entry not saved, missing name", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Persist your data easily
                                    realm.beginTransaction();
                                    realm.copyToRealm(warehouseSource);
                                    realm.commitTransaction();

                                    adapter.notifyDataSetChanged();

                                    // scroll the recycler view to bottom
                                    recycler.scrollToPosition(RealmController.getInstance().getwhsources().size() - 1);
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

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }

    public void setRealmAdapter(RealmResults<WarehouseSource> warehousesources) {

        RealmWarehouseSourceAdapter realmAdapter = new RealmWarehouseSourceAdapter(this.getApplicationContext(), warehousesources, true);
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
        adapter = new WarehouseSourceAdapter(this);
        recycler.setAdapter(adapter);
    }

    private void setRealmData() {

        ArrayList<WarehouseSource> whsources = new ArrayList<>();
//        whsources.clear();

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

        Prefs.with(this).setPreLoad(true);

    }

//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//        if (viewHolder instanceof WarehouseSourceAdapter.CardViewHolder) {
//
//            RealmResults<WarehouseSource> results = realm.where(WarehouseSource.class).findAll();
//            final WarehouseSource whsrc = results.get(position);
//            String name = whsrc.getSourceName();
//            realm.beginTransaction();
//            results.remove(position);
//            realm.commitTransaction();
//
//            final int deletedIndex = viewHolder.getAdapterPosition();
//            adapter.removeItem(viewHolder.getAdapterPosition());
//
//            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar.make(linearLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    // undo is selected, restore the deleted item
//                    adapter.restoreItem(whsrc, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();
//
//        }
//    }
}
