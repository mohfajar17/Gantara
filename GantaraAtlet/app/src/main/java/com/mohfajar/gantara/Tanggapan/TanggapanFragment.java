package com.mohfajar.gantara.Tanggapan;

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
import com.mohfajar.gantara.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class TanggapanFragment extends Fragment implements TanggapanContract.View{

    MyTanggapanRecyclerViewAdapter myTanggapanRecyclerViewAdapter;

    private TanggapanContract.Presenter mTanggapanPresenter;
    private RecyclerView mReacyler;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public TanggapanFragment() {
    }

    public static TanggapanFragment newInstance(int columnCount) {
        TanggapanFragment fragment = new TanggapanFragment();
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

        myTanggapanRecyclerViewAdapter = new MyTanggapanRecyclerViewAdapter(new ArrayList<Tanggapan>(), mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tanggapan_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewTanggapan);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myTanggapanRecyclerViewAdapter);

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

        mTanggapanPresenter.tanggapan(sharedPrefManager.getAtletID());

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
    public void tampilkanTanggapan(List<Tanggapan> tanggapanList) {
        if(myTanggapanRecyclerViewAdapter!=null){
            myTanggapanRecyclerViewAdapter.updateData(tanggapanList);
        }
    }

    @Override
    public void setPresenter(TanggapanContract.Presenter presenter) {
        this.mTanggapanPresenter = presenter;
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
        void onListFragmentInteraction(Tanggapan item);
    }
}
