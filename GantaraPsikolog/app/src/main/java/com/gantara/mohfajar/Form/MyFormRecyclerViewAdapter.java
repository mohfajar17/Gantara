package com.gantara.mohfajar.Form;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.Form;
import com.gantara.mohfajar.Form.FormFragment.OnListFragmentInteractionListener;
import com.gantara.mohfajar.RunTime;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFormRecyclerViewAdapter extends RecyclerView.Adapter<MyFormRecyclerViewAdapter.ViewHolder> {

    private List<Form> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyFormRecyclerViewAdapter(List<Form> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_form, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+mValues.get(position).getUserName()+".png").resize(100, 100).centerCrop().into(holder.imageViewCProfile);
        if(Integer.valueOf(mValues.get(position).getIdAtlet())%2==0){
            holder.imageViewCProfile.setImageResource(R.drawable.akun_man);
        }
        else {
            holder.imageViewCProfile.setImageResource(R.drawable.akun_woman);
        }

        holder.textViewCNama.setText(mValues.get(position).getNama());
        holder.textViewCCabangOlahraga.setText(mValues.get(position).getCabangOlahraga());
        holder.textViewCWaktu.setText(mValues.get(position).getWaktuInput());
        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+mValues.get(position).getUserName()+".png").resize(300, 300).centerCrop().into(holder.imageViewCProfile);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaFormActivity = new Intent(holder.itemView.getContext(), FormActivity.class);
                bukaFormActivity.putExtra("form", mValues.get(position));
                bukaFormActivity.putExtra("position", position);
                holder.itemView.getContext().startActivity(bukaFormActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Form> forms){
        this.mValues.clear();
        this.mValues = forms;
        notifyDataSetChanged();
        RunTime.logTime();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Form mItem;

        public final ImageView imageViewCProfile;
        public final TextView textViewCNama;
        public final TextView textViewCCabangOlahraga;
        public final TextView textViewCWaktu;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewCProfile = (ImageView) view.findViewById(R.id.imageCircleCProfile);
            textViewCNama = (TextView) view.findViewById(R.id.textViewCNama);
            textViewCCabangOlahraga = (TextView) view.findViewById(R.id.textViewCCabangOlahraga);
            textViewCWaktu = (TextView) view.findViewById(R.id.textViewCWaktu);
        }
    }
}
