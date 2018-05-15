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
        public Button btnEdit;

        public CostViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.Linearbayar);
            tvWare = (TextView) itemView.findViewById(R.id.tvware);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
            btnEdit = (Button)itemView.findViewById(R.id.btnedit);
        }
    }
}
