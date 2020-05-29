package com.gantara.mohfajar.Atlet;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.AddAtlet.AddAtletActivity;
import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.RunTime;
import com.gantara.mohfajar.SharedPrefManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AtletFragment extends Fragment implements AtletContract.View {

    ArrayList<Atlet> daftarAtlets;

    MyAtletRecyclerViewAdapter myAtletRecyclerViewAdapter;

    private AtletContract.Presenter mAtletPresenter;
    private RecyclerView mReacyler;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

//    private FloatingActionButton fabTambahAtlet;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public AtletFragment() {
    }

    public static AtletFragment newInstance(int columnCount) {
        AtletFragment fragment = new AtletFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daftarAtlets = new ArrayList<>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        this.mProgessDialog = new ProgressDialog(getActivity());

        myAtletRecyclerViewAdapter = new MyAtletRecyclerViewAdapter(new ArrayList<Atlet>(), mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atlet_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewAtlet);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

//        fabTambahAtlet = (FloatingActionButton) view.findViewById(R.id.fabTambahAtlet);
//        fabTambahAtlet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent bukaTambahAtletActivity = new Intent(AtletFragment.this.getActivity(), AddAtletActivity.class);
//                startActivity(bukaTambahAtletActivity);
//            }
//        });

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myAtletRecyclerViewAdapter);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }

        RunTime.logTime();

        mAtletPresenter.atlet(sharedPrefManager.getPsikologID());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void tampilkanAtlet(List<Atlet> atlets) {
        if(myAtletRecyclerViewAdapter!=null){
            myAtletRecyclerViewAdapter.updateData(atlets);
        }
    }

    @Override
    public void setPresenter(AtletContract.Presenter presenter) {
        this.mAtletPresenter = presenter;
    }

    @Override
    public void showLoading() {
        if(mProgessDialog!=null){
            mProgessDialog.setTitle("Proses");
            mProgessDialog.setMessage("Silahkan tunggu");
            mProgessDialog.setCancelable(false);
            mProgessDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(mProgessDialog!=null && mProgessDialog.isShowing()){
            mProgessDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Attention")
                .setMessage(message)
                .show();
    }

    @Override
    public void onDataNotAvailable() {
        showMessage(getString(R.string.message_no_data));
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Atlet item);
    }
}