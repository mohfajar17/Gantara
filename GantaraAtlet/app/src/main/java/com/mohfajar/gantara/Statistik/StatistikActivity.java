package com.mohfajar.gantara.Statistik;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mohfajar.gantara.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.mohfajar.gantara.Data.Statistik;

import java.util.ArrayList;

public class StatistikActivity extends AppCompatActivity {

    private int position;
    private Statistik statistik;

    private TextView textViewTanggalData;
    private TextView textViewSesi;
    private RadarChart radarchartStatistik1;
    private RadarChart radarchartStatistik2;
    private TextView textViewSAntusiasPreLatihan;
    private TextView textViewSAntusiasPosLatihan;
    private TextView textViewSStress;
    private TextView textViewSKonsentrasi;
    private TextView textViewSKeyakinan;
    private TextView textViewSTarget;
    private TextView textViewSKomunikasi;
    private TextView textViewSMentalSkill;
    private TextView textViewSFisik;
    private TextView textViewSLelah;
    private TextView textViewSHidrasi;
    private TextView textViewSTidur;
    private TextView textViewSNutrisi;
    private TextView textViewSRecovery;
    private TextView textViewSIntensitas;
    private TextView textViewSTargetIntensitas;

    private ArrayList<RadarEntry> entries1;
    private ArrayList<RadarEntry> entries2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        statistik = bundle.getParcelable("statistik");
        position = bundle.getInt("position");

        radarchartStatistik1 = (RadarChart) findViewById(R.id.radarchartStatistik1);

        radarchartStatistik1.setWebLineWidth(1f);
        radarchartStatistik1.setWebColor(Color.DKGRAY);
        radarchartStatistik1.setWebLineWidthInner(1f);
        radarchartStatistik1.setWebColorInner(Color.DKGRAY);
        radarchartStatistik1.setWebAlpha(100);

        radarchartStatistik2 = (RadarChart) findViewById(R.id.radarchartStatistik2);

        radarchartStatistik2.setWebLineWidth(1f);
        radarchartStatistik2.setWebColor(Color.DKGRAY);
        radarchartStatistik2.setWebLineWidthInner(1f);
        radarchartStatistik2.setWebColorInner(Color.DKGRAY);
        radarchartStatistik2.setWebAlpha(100);

        // add data
        setData1();
        setData2();

        textViewTanggalData = (TextView) findViewById(R.id.textViewTanggalData);
        textViewSesi = (TextView) findViewById(R.id.textViewSesi);
        textViewSAntusiasPreLatihan = (TextView) findViewById(R.id.textViewSAntusiasPreLatihan);
        textViewSAntusiasPosLatihan = (TextView) findViewById(R.id.textViewSAntusiasPosLatihan);
        textViewSStress = (TextView) findViewById(R.id.textViewSStress);
        textViewSKonsentrasi = (TextView) findViewById(R.id.textViewSKonsentrasi);
        textViewSKeyakinan = (TextView) findViewById(R.id.textViewSKeyakinan);
        textViewSTarget = (TextView) findViewById(R.id.textViewSTarget);
        textViewSKomunikasi = (TextView) findViewById(R.id.textViewSKomunikasi);
        textViewSMentalSkill = (TextView) findViewById(R.id.textViewSMentalSkill);
        textViewSFisik = (TextView) findViewById(R.id.textViewSFisik);
        textViewSLelah = (TextView) findViewById(R.id.textViewSLelah);
        textViewSHidrasi = (TextView) findViewById(R.id.textViewSHidrasi);
        textViewSTidur = (TextView) findViewById(R.id.textViewSTidur);
        textViewSNutrisi = (TextView) findViewById(R.id.textViewSNutrisi);
        textViewSRecovery = (TextView) findViewById(R.id.textViewSRecovery);
        textViewSIntensitas = (TextView) findViewById(R.id.textViewSIntensitas);
        textViewSTargetIntensitas = (TextView) findViewById(R.id.textViewSTargetIntensitas);

        getContent();
    }

    private void getContent() {
        textViewSesi.setText(""+(position+1));
        textViewTanggalData.setText(statistik.getWaktu_input());
        textViewSAntusiasPreLatihan.setText(String.valueOf(statistik.getAntusiasme_pre_latih()));
        textViewSAntusiasPosLatihan.setText(String.valueOf(statistik.getAntusiasme_pos_latih()));
        textViewSStress.setText(String.valueOf(statistik.getStres()));
        textViewSKonsentrasi.setText(String.valueOf(statistik.getKonsentrasi()));
        textViewSKeyakinan.setText(String.valueOf(statistik.getKeyakinan()));
        textViewSTarget.setText(String.valueOf(statistik.getTarget()));
        textViewSKomunikasi.setText(String.valueOf(statistik.getKomunikasi()));
        textViewSMentalSkill.setText(String.valueOf(statistik.getMental_skill()));
        textViewSFisik.setText(String.valueOf(statistik.getFisik()));
        textViewSLelah.setText(String.valueOf(statistik.getLelah()));
        textViewSHidrasi.setText(String.valueOf(statistik.getHidrasi()));
        textViewSTidur.setText(String.valueOf(statistik.getTidur()));
        textViewSNutrisi.setText(String.valueOf(statistik.getNutrisi()));
        textViewSRecovery.setText(String.valueOf(statistik.getRecovery()));
        textViewSIntensitas.setText(String.valueOf(statistik.getIntensitas()));
        textViewSTargetIntensitas.setText(String.valueOf(statistik.getIntensitas_target()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData1() {
        entries1 = new ArrayList<RadarEntry>();
        entries1.add(new RadarEntry((float) statistik.getAntusiasme_pre_latih()));
        entries1.add(new RadarEntry((float) statistik.getAntusiasme_pos_latih()));
        entries1.add(new RadarEntry((float) statistik.getStres()));
        entries1.add(new RadarEntry((float) statistik.getKonsentrasi()));
        entries1.add(new RadarEntry((float) statistik.getKeyakinan()));
        entries1.add(new RadarEntry((float) statistik.getTarget()));
        entries1.add(new RadarEntry((float) statistik.getKomunikasi()));
        entries1.add(new RadarEntry((float) statistik.getMental_skill()));

        RadarDataSet set1 = new RadarDataSet(entries1, "lihat");
        set1.setColor(Color.rgb(26, 126, 167));
        set1.setFillColor(Color.rgb(26, 126, 167));
        set1.setDrawFilled(true);
        set1.setFillAlpha(120);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);

        radarchartStatistik1.setData(data);
        radarchartStatistik1.invalidate();

        radarchartStatistik1.animateY(1000);

        XAxis xAxis = radarchartStatistik1.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Motivasi", "Kenyamanan", "Stress", "Kosentrasi", "Keyakinan", "Target", "Komunikasi", "Mental skill"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.DKGRAY);

        YAxis yAxis = radarchartStatistik1.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        radarchartStatistik1.getLegend().setEnabled(false);
        radarchartStatistik1.getDescription().setEnabled(false);
    }

    private void setData2() {
        entries2 = new ArrayList<RadarEntry>();
        entries2.add(new RadarEntry((float) statistik.getFisik()));
        entries2.add(new RadarEntry((float) statistik.getLelah()));
        entries2.add(new RadarEntry((float) statistik.getHidrasi()));
        entries2.add(new RadarEntry((float) statistik.getTidur()));
        entries2.add(new RadarEntry((float) statistik.getNutrisi()));
        entries2.add(new RadarEntry((float) statistik.getRecovery()));

        RadarDataSet set2 = new RadarDataSet(entries2, "lihat");
        set2.setColor(Color.rgb(26, 126, 167));
        set2.setFillColor(Color.rgb(26, 126, 167));
        set2.setDrawFilled(true);
        set2.setFillAlpha(120);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);

        radarchartStatistik2.setData(data);
        radarchartStatistik2.invalidate();

        radarchartStatistik2.animateY(1000);

        XAxis xAxis = radarchartStatistik2.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Fisik", "Lelah", "Dehidrasi", "Tidur", "Nutrisi", "Recovery"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.DKGRAY);

        YAxis yAxis = radarchartStatistik2.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        radarchartStatistik2.getLegend().setEnabled(false);
        radarchartStatistik2.getDescription().setEnabled(false);
    }
}
