package com.gantara.mohfajar.AddAtlet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Data.Atlet;

import java.util.List;

public class MyAddAtletRecyclerViewAdapter extends RecyclerView.Adapter<MyAddAtletRecyclerViewAdapter.ViewHolder> {

    private List<Atlet> mValues;
    private AddAtletFragment.OnListFragmentInteractionListener mListener;

    public MyAddAtletRecyclerViewAdapter(List<Atlet> items, AddAtletFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_add_atlet_main, parent, false);
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

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            imageViewDAProfile = (ImageView) view.findViewById(R.id.imageCircleADAProfile);
            textViewDANama = (TextView) view.findViewById(R.id.textViewADANama);
            textViewDACabangOlahraga = (TextView) view.findViewById(R.id.textViewADACabangOlahraga);
        }

    }
}