package com.example.bahroel.spk.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahroel.spk.activity.R;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import io.realm.Realm;
import io.realm.RealmResults;

public class WarehouseSourceAdapter extends RealmRecyclerViewAdapter<WarehouseSource>{
    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public WarehouseSourceAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final WarehouseSource warehousesource = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.textName.setText(warehousesource.getSourceName());
        holder.textAmount.setText("Jumlah Truk : "+warehousesource.getSourceAmount());

        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                RealmResults<WarehouseSource> results = realm.where(WarehouseSource.class).findAll();

                // Get the book title to show it in toast message
                WarehouseSource whsrc = results.get(position);
                String name = whsrc.getSourceName();

                // All changes to data must happen in a transaction
                realm.beginTransaction();

                // remove single match
                results.remove(position);
                realm.commitTransaction();

                if (results.size() == 0) {
                    Prefs.with(context).setPreLoad(false);
                }

                notifyDataSetChanged();

                Toast.makeText(context, name + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        //update single match from realm
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View content = inflater.inflate(R.layout.edit_item, null);
                final EditText editName = (EditText) content.findViewById(R.id.etNamaSrc);
                final EditText editAmount = (EditText) content.findViewById(R.id.etJumlahSrc);

                editName.setText(warehousesource.getSourceName());
                editAmount.setText(warehousesource.getSourceAmount());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(content)
                        .setTitle("Edit Warehouse Source")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RealmResults<WarehouseSource> results = realm.where(WarehouseSource.class).findAll();

                                realm.beginTransaction();
                                results.get(position).setSourceName(editName.getText().toString());
                                results.get(position).setSourceAmount(editAmount.getText().toString());

                                realm.commitTransaction();

                                notifyDataSetChanged();
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

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView textName;
        public TextView textAmount;

        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_whsource);
            textName = (TextView) itemView.findViewById(R.id.TextViewNamaSrc);
            textAmount = (TextView) itemView.findViewById(R.id.TextViewJumlahSrc);
        }
    }


}
