package com.mohfajar.gantara.Profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ProfileFragment extends Fragment {

    TextView textViewEditProfile;
    ImageView imageViewPhoto;

    TextView textViewProfNama;
    TextView textViewProfIDAtlet;
    TextView textViewProfUsername;
    TextView textViewProfCabor;
    TextView textViewProfJenisKelamin;
    TextView textViewProfTmpLahir;
    TextView textViewProfTglLahir;
    TextView textViewProfAlamat;
    TextView textViewProfNoTelfn;

    SharedPrefManager sharedPrefManager;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewEditProfile = (TextView) view.findViewById(R.id.textViewEditBiodata);
        textViewProfNama = (TextView) view.findViewById(R.id.textViewProfNama);
        textViewProfIDAtlet = (TextView) view.findViewById(R.id.textViewProfIDAtlet);
        textViewProfUsername = (TextView) view.findViewById(R.id.textViewProfUsername);
        textViewProfCabor = (TextView) view.findViewById(R.id.textViewProfCabor);
        textViewProfJenisKelamin = (TextView) view.findViewById(R.id.textViewProfJenisKelamin);
        textViewProfTmpLahir = (TextView) view.findViewById(R.id.textViewProfTmpLahir);
        textViewProfTglLahir = (TextView) view.findViewById(R.id.textViewProfTglLahir);
        textViewProfAlamat = (TextView) view.findViewById(R.id.textViewProfAlamat);
        textViewProfNoTelfn = (TextView) view.findViewById(R.id.textViewProfNoTelfn);
        imageViewPhoto = (ImageView) view.findViewById(R.id.imageViewPhoto);

        textViewProfNama.setText(sharedPrefManager.getName());
        textViewProfIDAtlet.setText(sharedPrefManager.getAtletID());
        textViewProfUsername.setText(sharedPrefManager.getUserName());
        textViewProfCabor.setText(sharedPrefManager.getCabor());
        if(sharedPrefManager.getJenisKelamin()==1)
            textViewProfJenisKelamin.setText("Laki-laki");
        else textViewProfJenisKelamin.setText("Perempuan");
        textViewProfTmpLahir.setText(sharedPrefManager.getTanggalLahir());
        textViewProfTglLahir.setText(sharedPrefManager.getTempatLahir());
        textViewProfAlamat.setText(sharedPrefManager.getAlamat());
        textViewProfNoTelfn.setText(sharedPrefManager.getNoTlp());

        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+sharedPrefManager.getUserName()+".png").resize(700, 700).centerCrop().into(imageViewPhoto);

        textViewEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaEditProfileActivity = new Intent(getContext(), ProfileActivity.class);
                startActivityForResult(bukaEditProfileActivity, 532);
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
