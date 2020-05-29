package com.mohfajar.gantara.Statistik;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Data.Statistik;
import com.mohfajar.gantara.Statistik.StatistikFragment.OnListFragmentInteractionListener;

import java.text.DecimalFormat;
import java.util.List;

public class MyStatistikRecyclerViewAdapter extends RecyclerView.Adapter<MyStatistikRecyclerViewAdapter.ViewHolder> {

    private List<Statistik> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyStatistikRecyclerViewAdapter(List<Statistik> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_statistik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        DecimalFormat twoDForm = new DecimalFormat("#.##");
        holder.textViewSsesi.setText(""+(position+1));
        holder.textViewSTanggal.setText(String.valueOf(mValues.get(position).getWaktu_input()));
        holder.textViewSKondisiMental.setText(String.valueOf(twoDForm.format(mValues.get(position).getScoring_mental())));
        holder.textViewSKondisiFisik.setText(String.valueOf(twoDForm.format(mValues.get(position).getScoring_fisik())));
        holder.textViewSIntensitas.setText(String.valueOf(mValues.get(position).getIntensitas()));
        holder.textViewSIntensitasTarget.setText(String.valueOf(mValues.get(position).getIntensitas_target()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaStatistikActivity = new Intent(holder.itemView.getContext(), StatistikActivity.class);
                bukaStatistikActivity.putExtra("statistik", mValues.get(position));
                bukaStatistikActivity.putExtra("position", position);
                holder.itemView.getContext().startActivity(bukaStatistikActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Statistik> statistiks){
        this.mValues.clear();
        this.mValues = statistiks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Statistik mItem;

        public final TextView textViewSsesi;
        public final TextView textViewSTanggal;
        public final TextView textViewSKondisiMental;
        public final TextView textViewSKondisiFisik;
        public final TextView textViewSIntensitas;
        public final TextView textViewSIntensitasTarget;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            textViewSsesi = (TextView) view.findViewById(R.id.textViewSsesi);
            textViewSTanggal = (TextView) view.findViewById(R.id.textViewSTanggal);
            textViewSKondisiMental = (TextView) view.findViewById(R.id.textViewSKondisiMental);
            textViewSKondisiFisik = (TextView) view.findViewById(R.id.textViewSKondisiFisik);
            textViewSIntensitas = (TextView) view.findViewById(R.id.textViewSIntensitas);
            textViewSIntensitasTarget = (TextView) view.findViewById(R.id.textViewSIntensitasTarget);
        }
    }
}
