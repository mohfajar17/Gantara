package com.gantara.mohfajar.Atlet;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Atlet.AtletFragment.OnListFragmentInteractionListener;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.DetailAtlet.DetailAtletActivity;
import com.gantara.mohfajar.RunTime;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAtletRecyclerViewAdapter extends RecyclerView.Adapter<MyAtletRecyclerViewAdapter.ViewHolder> {

    private List<Atlet> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyAtletRecyclerViewAdapter(List<Atlet> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_atlet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+mValues.get(position).getUserName()+".png").resize(100, 100).centerCrop().into(holder.imageViewCProfile);
        if(Integer.valueOf(mValues.get(position).getIdAtlet())%2==0){
            holder.imageViewDAProfile.setImageResource(R.drawable.akun_man);
        }
        else {
            holder.imageViewDAProfile.setImageResource(R.drawable.akun_woman);
        }

        holder.textViewDANama.setText(mValues.get(position).getNama());
        holder.textViewDACabangOlahraga.setText(mValues.get(position).getCabangOlahraga());
        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+mValues.get(position).getUserName()+".png").resize(300, 300).centerCrop().into(holder.imageViewDAProfile);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaDetailAtletActivity = new Intent(holder.itemView.getContext(), DetailAtletActivity.class);
                bukaDetailAtletActivity.putExtra("atlet", mValues.get(position));
                bukaDetailAtletActivity.putExtra("position", position);
                holder.itemView.getContext().startActivity(bukaDetailAtletActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Atlet> atlets){
        this.mValues.clear();
        this.mValues = atlets;
        notifyDataSetChanged();
        RunTime.logTime();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Atlet mItem;

        public final ImageView imageViewDAProfile;
        public final TextView textViewDANama;
        public final TextView textViewDACabangOlahraga;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewDAProfile = (ImageView) view.findViewById(R.id.imageCircleDAProfile);
            textViewDANama = (TextView) view.findViewById(R.id.textViewDANama);
            textViewDACabangOlahraga = (TextView) view.findViewById(R.id.textViewDACabangOlahraga);
        }

    }
}
