package com.mohfajar.gantara.Statistik;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohfajar.gantara.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.mohfajar.gantara.Data.Statistik;
import com.mohfajar.gantara.RunTime;
import com.mohfajar.gantara.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class StatistikFragment extends Fragment implements StatistikContract.View, OnChartGestureListener, OnChartValueSelectedListener {

    MyStatistikRecyclerViewAdapter myStatistikRecyclerViewAdapter;

    private StatistikContract.Presenter mStatistikPresenter;
    private RecyclerView mReacyler;

    private List<Statistik> statistiks;

    private SharedPrefManager sharedPrefManager;

    private ProgressDialog mProgessDialog;

    public LineChart linechartStatistik;
    LineDataSet mental;
    LineDataSet fisik;
    LineDataSet intensitasAtlet;
    LineDataSet intensitasTarget;

    public ArrayList<Entry> yMental;
    public ArrayList<Entry> yFisik;
    public ArrayList<Entry> yIntensitasAtlet;
    public ArrayList<Entry> yIntensitasTarget;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public StatistikFragment() {
    }

    public static StatistikFragment newInstance(int columnCount) {
        StatistikFragment fragment = new StatistikFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        statistiks = new ArrayList<>();
        yMental = new ArrayList<>();
        yFisik = new ArrayList<>();
        yIntensitasAtlet = new ArrayList<>();
        yIntensitasTarget = new ArrayList<>();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        this.mProgessDialog = new ProgressDialog(getActivity());

        statistiks = new ArrayList<Statistik>();
        myStatistikRecyclerViewAdapter = new MyStatistikRecyclerViewAdapter(statistiks, mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistik_list, container, false);

        mReacyler = view.findViewById(R.id.recyclerViewlistStatistik);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        mReacyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReacyler.setAdapter(myStatistikRecyclerViewAdapter);

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

        mStatistikPresenter.statistik(sharedPrefManager.getAtletID());

        //linechart
        linechartStatistik = (LineChart) view.findViewById(R.id.linechartSStatistik);
        linechartStatistik.setOnChartGestureListener(this);
        linechartStatistik.setOnChartValueSelectedListener(this);

        return view;
    }

    private void loadStatistik() {
        for(int i=0;i<statistiks.size();i++){
            yMental.add(new Entry(i+1, (float) statistiks.get(i).getScoring_mental()));
            yFisik.add(new Entry(i+1, (float) statistiks.get(i).getScoring_fisik()));
            yIntensitasAtlet.add(new Entry(i+1, (float) statistiks.get(i).getIntensitas()));
            yIntensitasTarget.add(new Entry(i+1, (float) statistiks.get(i).getIntensitas_target()));
        }

        setData();
        // get the legend (only possible after setting data)
        Legend l = linechartStatistik.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData() {

        // create a dataset and give it a type
        mental = new LineDataSet(yMental, "Kondisi mental");
        mental.setDrawValues(!mental.isDrawValuesEnabled());
        fisik = new LineDataSet(yFisik, "Kondisi fisik");
        fisik.setDrawValues(!fisik.isDrawValuesEnabled());
        intensitasAtlet = new LineDataSet(yIntensitasAtlet, "Intensitas");
        intensitasAtlet.setDrawValues(!intensitasAtlet.isDrawValuesEnabled());
        intensitasTarget = new LineDataSet(yIntensitasTarget, "Intensitas target");
        intensitasTarget.setDrawValues(!intensitasTarget.isDrawValuesEnabled());

//         set the line to be drawn like this "- - - - - -"
        mental.setColor(Color.MAGENTA);
        mental.setCircleColor(Color.MAGENTA);
        mental.setLineWidth(1f);
        mental.setCircleRadius(3f);
        mental.setDrawCircleHole(false);
        mental.setValueTextSize(9f);

        fisik.setColor(Color.RED);
        fisik.setCircleColor(Color.RED);
        fisik.setLineWidth(1f);
        fisik.setCircleRadius(3f);
        fisik.setDrawCircleHole(false);
        fisik.setValueTextSize(9f);

        intensitasAtlet.setColor(Color.BLUE);
        intensitasAtlet.setCircleColor(Color.BLUE);
        intensitasAtlet.setLineWidth(1f);
        intensitasAtlet.setCircleRadius(3f);
        intensitasAtlet.setDrawCircleHole(false);
        intensitasAtlet.setValueTextSize(9f);

        intensitasTarget.setColor(Color.rgb(0, 210, 255));
        intensitasTarget.setCircleColor(Color.rgb(0, 210, 255));
        intensitasTarget.setLineWidth(1f);
        intensitasTarget.setCircleRadius(3f);
        intensitasTarget.setDrawCircleHole(false);
        intensitasTarget.setValueTextSize(9f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        dataSets.add(mental);
        dataSets.add(fisik);
        dataSets.add(intensitasAtlet);
        dataSets.add(intensitasTarget);

        LineData data = new LineData(dataSets);

        linechartStatistik.setData(data);

        linechartStatistik.notifyDataSetChanged();
        linechartStatistik.animateXY(1000, 2000);
        linechartStatistik.setPinchZoom(true);
        linechartStatistik.getDescription().setEnabled(false);

        MyMarkerView mv = new MyMarkerView(this.getActivity(), R.layout.costom_marker_view);
        mv.setChartView(linechartStatistik);
        linechartStatistik.setMarker(mv);

        RunTime.logTime();
    }

    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            linechartStatistik.highlightValues(null);
    }

    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + linechartStatistik.getLowestVisibleX() + ", high: " + linechartStatistik.getHighestVisibleX());

        Log.i("MIN MAX", "xmin: " + linechartStatistik.getXChartMin() + ", xmax: " + linechartStatistik.getXChartMax() + ", ymin: " + linechartStatistik.getYChartMin() + ", ymax: " + linechartStatistik.getYChartMax());
    }

    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("sesi 1");
        xVals.add("sesi 2");
        xVals.add("sesi 3");
        xVals.add("sesi 4");
        xVals.add("sesi 5");
        xVals.add("sesi 6");
        xVals.add("sesi 7");

        return xVals;
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
    public void tampilkanStatistik(List<Statistik> statistikList) {
        if(myStatistikRecyclerViewAdapter!=null){
            this.statistiks.clear();
            this.statistiks.addAll(statistikList);
            myStatistikRecyclerViewAdapter.notifyDataSetChanged();

            loadStatistik();
        }
    }

    @Override
    public void setPresenter(StatistikContract.Presenter presenter) {
        this.mStatistikPresenter = presenter;
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
        void onListFragmentInteraction(Statistik item);
    }
}
