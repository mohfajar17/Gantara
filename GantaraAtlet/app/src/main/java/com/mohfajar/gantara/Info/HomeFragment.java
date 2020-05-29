package com.mohfajar.gantara.Info;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohfajar.gantara.Data.Info;
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View{

    MyHomeRecyclerViewAdapter myHomeRecyclerViewAdapter;
    private HomeContract.Presenter mHomePresenter;
    private RecyclerView mReacyler;
    private SharedPrefManager sharedPrefManager;
    private ProgressDialog mProgessDialog;
    private TextView textViewIntensitasTarget;
    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    public HomeFragment() {
    }

    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        this.mProgessDialog = new ProgressDialog(getActivity());
        myHomeRecyclerViewAdapter = new MyHomeRecyclerViewAdapter(new ArrayList<Info>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        mReacyler = view.findViewById(R.id.recyclerViewInfo);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myHomeRecyclerViewAdapter);
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
        textViewIntensitasTarget = (TextView) view.findViewById(R.id.textViewIntensitasTarget);
        textViewIntensitasTarget.setText(String.valueOf(sharedPrefManager.getIntensitasTarget()));
        mHomePresenter.start();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void tampilkanInfo(List<Info> infoList) {
        if(myHomeRecyclerViewAdapter!=null){
            myHomeRecyclerViewAdapter.updateData(infoList);
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mHomePresenter = presenter;
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
}
