package com.example.bahroel.spk.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahroel.spk.activity.R;
import com.example.bahroel.spk.app.Prefs;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import io.realm.Realm;
import io.realm.RealmResults;

public class CostAdapter extends RealmRecyclerViewAdapter<Cost> {
    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

//    public Realm getReal() {
//        return realm;
//    }

    public CostAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bayar_item, parent, false);
        return new CostAdapter.CostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        this.realm = RealmController.getInstance().getRealm();

        // get the article
        final Cost cost = getItem(position);
        // cast the generic view holder to our specific one
        final CostAdapter.CostViewHolder holder = (CostAdapter.CostViewHolder) viewHolder;

        // set the title and the snippet
        holder.tvWare.setText(cost.getSourceName() + " ke " + cost.getDestinationName());
        holder.tvValue.setText(String.valueOf(cost.getCost()));
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View content = inflater.inflate(R.layout.edit_cost, null);
                final EditText editName = (EditText) content.findViewById(R.id.etCostValue);

                editName.setText(String.valueOf(cost.getCost()));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(content)
                        .setTitle("Edit Biaya")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RealmResults<Cost> results = realm.where(Cost.class).findAll();

                                realm.beginTransaction();
                                results.get(position).setCost(Integer.valueOf(editName.getText().toString()));
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

    public static class CostViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView tvWare;
        public TextView tvValue;
        public ImageView btnEdit;

        public CostViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.Linearbayar);
            tvWare = (TextView) itemView.findViewById(R.id.tvware);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
            btnEdit = (ImageView) itemView.findViewById(R.id.btnedit);
        }
    }
}
