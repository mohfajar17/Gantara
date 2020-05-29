package com.gantara.mohfajar.Form;

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
import com.gantara.mohfajar.Data.Form;
import com.gantara.mohfajar.RunTime;
import com.gantara.mohfajar.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class FormFragment extends Fragment implements FormContract.View {

    MyFormRecyclerViewAdapter myFormRecyclerViewAdapter;

    private FormContract.Presenter mFormPresenter;
    private RecyclerView mReacyler;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public FormFragment() {
    }

    public static FormFragment newInstance(int columnCount) {
        FormFragment fragment = new FormFragment();
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

        myFormRecyclerViewAdapter = new MyFormRecyclerViewAdapter(new ArrayList<Form>(), mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewForm);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myFormRecyclerViewAdapter);

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

        mFormPresenter.form(sharedPrefManager.getPsikologID());

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
    public void tampilkanForm(List<Form> formList) {
        if(myFormRecyclerViewAdapter!=null){
            myFormRecyclerViewAdapter.updateData(formList);
        }
    }

    @Override
    public void setPresenter(FormContract.Presenter presenter) {
        this.mFormPresenter = presenter;
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
        void onListFragmentInteraction(Form item);
    }
}
