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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahroel.spk.activity.R;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import io.realm.Realm;
import io.realm.RealmResults;

public class WarehouseDestinationAdapter extends RealmRecyclerViewAdapter<WarehouseDestination> {
    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public WarehouseDestinationAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public WarehouseDestinationAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost_destination, parent, false);
        return new WarehouseDestinationAdapter.CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final WarehouseDestination warehousedestination = getItem(position);
        // cast the generic view holder to our specific one
        final WarehouseDestinationAdapter.CardViewHolder holder = (WarehouseDestinationAdapter.CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.textName.setText("Tujuan : " + warehousedestination.getDestinationName());
        holder.textAmount.setText("Jumlah Truk : " + warehousedestination.getDestinationAmount());

        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                RealmResults<WarehouseDestination> results = realm.where(WarehouseDestination.class).findAll();

                // Get the book title to show it in toast message
                WarehouseDestination whdst = results.get(position);
                String name = whdst.getDestinationName();

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
                View content = inflater.inflate(R.layout.edit_item_destination, null);
                final EditText editName = (EditText) content.findViewById(R.id.etNamaDst);
                final EditText editAmount = (EditText) content.findViewById(R.id.etJumlahDst);

                editName.setText(warehousedestination.getDestinationName());
                editAmount.setText(warehousedestination.getDestinationAmount());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(content)
                        .setTitle("Edit Warehouse Destination")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RealmResults<WarehouseDestination> results = realm.where(WarehouseDestination.class).findAll();

                                realm.beginTransaction();
                                results.get(position).setDestinationName(editName.getText().toString());
                                results.get(position).setDestinationAmount(editAmount.getText().toString());

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

            card = (CardView) itemView.findViewById(R.id.card_whdestinations);
            textName = (TextView) itemView.findViewById(R.id.TextViewNamaDst);
            textAmount = (TextView) itemView.findViewById(R.id.TextViewJumlahDst);
        }
    }
}
