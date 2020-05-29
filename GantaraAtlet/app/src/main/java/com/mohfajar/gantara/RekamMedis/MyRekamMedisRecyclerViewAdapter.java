package com.mohfajar.gantara.RekamMedis;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Data.Tanggapan;
import com.mohfajar.gantara.RekamMedis.RekamMedisFragment.OnListFragmentInteractionListener;
import com.mohfajar.gantara.RunTime;

import java.util.List;

public class MyRekamMedisRecyclerViewAdapter extends RecyclerView.Adapter<MyRekamMedisRecyclerViewAdapter.ViewHolder> {

    private List<Tanggapan> mValues;

    private OnListFragmentInteractionListener mListener;

    public MyRekamMedisRecyclerViewAdapter(List<Tanggapan> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rekammedis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(mValues.get(position).getSesiLatihan()==1)
            holder.imageViewTJenisLaporan.setImageResource(R.drawable.laporan1);
        else if(mValues.get(position).getSesiLatihan()==2)
            holder.imageViewTJenisLaporan.setImageResource(R.drawable.laporan2);
        else if(mValues.get(position).getSesiLatihan()==3)
            holder.imageViewTJenisLaporan.setImageResource(R.drawable.laporan3);
        else if(mValues.get(position).getSesiLatihan()==4)
            holder.imageViewTJenisLaporan.setImageResource(R.drawable.laporan4);
        else holder.imageViewTJenisLaporan.setImageResource(R.drawable.akun);

        holder.textViewTTanggal.setText(mValues.get(position).getWaktuInput());
        holder.textViewTSesi.setText(" / Sesi "+String.valueOf(mValues.get(position).getSesiLatihan()));
        holder.textViewTIsi.setText(mValues.get(position).getIsiRm());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaRekamMedisActivity = new Intent(holder.itemView.getContext(), RekamMedisActivity.class);
                bukaRekamMedisActivity.putExtra("rekamMedis", mValues.get(position));
                bukaRekamMedisActivity.putExtra("position", position);
                holder.itemView.getContext().startActivity(bukaRekamMedisActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Tanggapan> rekamMedis){
        this.mValues.clear();
        this.mValues = rekamMedis;
        notifyDataSetChanged();
        RunTime.logTime();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Tanggapan mItem;

        public final ImageView imageViewTJenisLaporan;
        public final TextView textViewTTanggal;
        public final TextView textViewTSesi;
        public final TextView textViewTIsi;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewTJenisLaporan = (ImageView) view.findViewById(R.id.imageCircleTJenisLaporan);
            textViewTTanggal = (TextView) view.findViewById(R.id.textViewTTanggal);
            textViewTSesi = (TextView) view.findViewById(R.id.textViewTSesi);
            textViewTIsi = (TextView) view.findViewById(R.id.textViewTIsi);
        }
    }
}
