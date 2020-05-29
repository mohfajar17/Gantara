package com.gantara.mohfajar.DetailAtlet.Intensitas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Data.Atlet;

import static android.app.Activity.RESULT_OK;


public class IntensitasFragment extends Fragment {

    private Atlet atlet;
    private int position;

    private TextView textViewIntensitasTarget;
    private TextView textViewButton;

    public IntensitasFragment() {
    }

    public static IntensitasFragment newInstance(String param1, String param2) {
        IntensitasFragment fragment = new IntensitasFragment();
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
        View view = inflater.inflate(R.layout.fragment_intensitas, container, false);

        textViewIntensitasTarget = (TextView) view.findViewById(R.id.textViewIntensitasTarget);
        textViewIntensitasTarget.setText(atlet.getIntensitasTarget());

        textViewButton = (TextView) view.findViewById(R.id.textViewButton);
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaEditTarget = new Intent(getActivity(), IntensitasActivity.class);
                bukaEditTarget.putExtra("idAtlet", atlet.getIdAtlet());
                bukaEditTarget.putExtra("target", atlet.getIntensitasTarget());
                startActivityForResult(bukaEditTarget, 134);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && data!=null && requestCode == 134) {
            String intensitas = data.getStringExtra("intensitas");
            textViewIntensitasTarget.setText(intensitas);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
