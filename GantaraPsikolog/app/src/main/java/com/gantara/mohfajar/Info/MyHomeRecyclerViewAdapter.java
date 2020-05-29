package com.gantara.mohfajar.Info;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gantara.mohfajar.Data.Info;
import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.RunTime;

import java.util.List;

public class MyHomeRecyclerViewAdapter extends RecyclerView.Adapter<MyHomeRecyclerViewAdapter.ViewHolder> {

    private List<Info> mValues;

    public MyHomeRecyclerViewAdapter(List<Info> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mJudul.setText(mValues.get(position).getJudul());
        holder.mDari.setText(mValues.get(position).getDari());
        holder.mUntuk.setText(mValues.get(position).getUntuk());
        holder.mWaktu.setText(mValues.get(position).getWaktu());
        holder.mIsiInfo.setText(mValues.get(position).getIsiInfo());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Info> infos){
        this.mValues.clear();
        this.mValues = infos;
        notifyDataSetChanged();
        RunTime.logTime();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mJudul;
        public final TextView mDari;
        public final TextView mUntuk;
        public final TextView mWaktu;
        public final TextView mIsiInfo;

        public ViewHolder(View view) {
            super(view);
            mJudul = (TextView) view.findViewById(R.id.textViewJudul);
            mDari = (TextView) view.findViewById(R.id.textViewDari);
            mUntuk = (TextView) view.findViewById(R.id.textViewUntuk);
            mWaktu = (TextView) view.findViewById(R.id.textViewWaktu);
            mIsiInfo = (TextView) view.findViewById(R.id.textViewIsi);
        }
    }
}
