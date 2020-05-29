package com.mohfajar.gantara.RekamMedis;

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

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Data.Tanggapan;
import com.mohfajar.gantara.RunTime;
import com.mohfajar.gantara.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class RekamMedisFragment extends Fragment implements RekamMedisContract.View{

    MyRekamMedisRecyclerViewAdapter myRekamMedisRecyclerViewAdapter;

    private RekamMedisContract.Presenter mRekamMedisPresenter;
    private RecyclerView mReacyler;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public RekamMedisFragment() {
    }

    public static RekamMedisFragment newInstance(int columnCount) {
        RekamMedisFragment fragment = new RekamMedisFragment();
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

        myRekamMedisRecyclerViewAdapter = new MyRekamMedisRecyclerViewAdapter(new ArrayList<Tanggapan>(), mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekammedis_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewRekamMedis);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myRekamMedisRecyclerViewAdapter);

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

        mRekamMedisPresenter.rekamMedis(sharedPrefManager.getAtletID());

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
    public void tampilkanRekamMedis(List<Tanggapan> rekamMedisList) {
        if(myRekamMedisRecyclerViewAdapter!=null){
            myRekamMedisRecyclerViewAdapter.updateData(rekamMedisList);
        }
    }

    @Override
    public void setPresenter(RekamMedisContract.Presenter presenter) {
        this.mRekamMedisPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgessDialog.setTitle("Proses");
        mProgessDialog.setMessage("Silahkan tunggu");
        mProgessDialog.setCancelable(false);
        mProgessDialog.show();
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
        void onListFragmentInteraction(Tanggapan item);
    }
}
