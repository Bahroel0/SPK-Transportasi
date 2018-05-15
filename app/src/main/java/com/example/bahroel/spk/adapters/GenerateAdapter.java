package com.example.bahroel.spk.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bahroel.spk.activity.R;
import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.realm.RealmController;

import io.realm.Realm;

public class GenerateAdapter extends RealmRecyclerViewAdapter<Generate> {
    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public GenerateAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public GenerateAdapter.GenerateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generate_item, parent, false);
        return new GenerateAdapter.GenerateViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final Generate generate = getItem(position);
        // cast the generic view holder to our specific one
        final GenerateAdapter.GenerateViewHolder holder = (GenerateAdapter.GenerateViewHolder) viewHolder;

        // set the title and the snippet
        holder.textAsal.setText(generate.getSrcName());
        holder.textTujuan.setText(generate.getDstName());
        holder.textBiaya.setText(String.valueOf(generate.getCostValue()));
        holder.textJumlah.setText(generate.getAmount());
    }

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class GenerateViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView textAsal, textTujuan, textBiaya, textJumlah;

        public GenerateViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearGenerate);
            textAsal = (TextView) itemView.findViewById(R.id.tvAsalValue);
            textTujuan = (TextView) itemView.findViewById(R.id.tvTujuanValue);
            textBiaya = (TextView) itemView.findViewById(R.id.tvBiayaValue);
            textJumlah = (TextView) itemView.findViewById(R.id.tvJumlahValue);
        }
    }
}
