package com.gantara.mohfajar.AddAtlet;

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

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class AddAtletFragment extends Fragment implements AddAtletContract.View {

    MyAddAtletRecyclerViewAdapter myAddAtletRecyclerViewAdapter;

    private AddAtletContract.Presenter mAddAtletPresenter;
    private RecyclerView mReacyler;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public AddAtletFragment() {
    }

    public static AddAtletFragment newInstance(int columnCount) {
        AddAtletFragment fragment = new AddAtletFragment();
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

        myAddAtletRecyclerViewAdapter = new MyAddAtletRecyclerViewAdapter(new ArrayList<Atlet>(), mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_atlet_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewAddAtlet);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myAddAtletRecyclerViewAdapter);

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

        mAddAtletPresenter.atlet(sharedPrefManager.getPsikologID());

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
        if(myAddAtletRecyclerViewAdapter!=null){
            myAddAtletRecyclerViewAdapter.updateData(atlets);
        }
    }

    @Override
    public void setPresenter(AddAtletContract.Presenter presenter) {
        this.mAddAtletPresenter = presenter;
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
