package com.gantara.mohfajar.DetailAtlet.Biodata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.Atlet;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class BiodataFragment extends Fragment {

    private Atlet atlet;
    private int position;

    private ImageView imageViewFoto;
    private TextView textViewIDAtlet;
    private TextView textViewNama;
    private TextView textViewCabangOlahraga;
    private TextView textViewJenisKelamin;
    private TextView textViewTempatLahir;
    private TextView textViewTanggalLahir;
    private TextView textViewAsalKota;
    private TextView textViewNomorTelefon;
    private TextView textViewEmail;

    public BiodataFragment() {
        // Required empty public constructor
    }

    public static BiodataFragment newInstance(String param1, String param2) {
        BiodataFragment fragment = new BiodataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getActivity().getIntent().getExtras();
        atlet = bundle.getParcelable("atlet");
        position = bundle.getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biodata, container, false);
        imageViewFoto = (CircleImageView) view.findViewById(R.id.imageViewBDAFoto);
        textViewIDAtlet = (TextView) view.findViewById(R.id.textViewBDAIDAtlet);
        textViewNama = (TextView) view.findViewById(R.id.textViewBDANama);
        textViewCabangOlahraga = (TextView) view.findViewById(R.id.textViewBDACabangOlahraga);
        textViewJenisKelamin = (TextView) view.findViewById(R.id.textViewBDAJenisKelamin);
        textViewTempatLahir = (TextView) view.findViewById(R.id.textViewBDATempatLahir);
        textViewTanggalLahir = (TextView) view.findViewById(R.id.textViewBDATanggalLahir);
        textViewAsalKota = (TextView) view.findViewById(R.id.textViewBDAAsalKota);
        textViewNomorTelefon = (TextView) view.findViewById(R.id.textViewBDANomorTelefon);
        textViewEmail = (TextView) view.findViewById(R.id.textViewBDAEmail);

        getContent();

        return view;
    }

    private void getContent() {
        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+atlet.getUserName()+".png").resize(700, 700).centerCrop().into(imageViewFoto);
        textViewIDAtlet.setText(atlet.getIdAtlet());
        textViewNama.setText(atlet.getNama());
        textViewCabangOlahraga.setText(atlet.getCabangOlahraga());
        if(Integer.valueOf(atlet.getJenisKelamin())==1)
            textViewJenisKelamin.setText("Laki-laki");
        else if(Integer.valueOf(atlet.getJenisKelamin())==0)
            textViewJenisKelamin.setText("Perempuan");
        textViewTempatLahir.setText(atlet.getTempatLahir());
        textViewTanggalLahir.setText(atlet.getTanggalLahir());
        textViewAsalKota.setText(atlet.getKotaAsal());
        textViewNomorTelefon.setText(atlet.getNoTelfon());
        textViewEmail.setText(atlet.getUserName());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
