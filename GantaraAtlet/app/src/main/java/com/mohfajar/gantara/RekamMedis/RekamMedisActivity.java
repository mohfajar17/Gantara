package com.mohfajar.gantara.RekamMedis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Data.Tanggapan;

public class RekamMedisActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewTanggalPendampingan;
    private TextView textViewSesiPendampingan;
    private TextView textViewKondisiPermasalahanFisik;
    private TextView textViewKendalaMentalSkill;
    private TextView textViewCatatanPsikolog;

    private int position;
    private Tanggapan rekamMedis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        rekamMedis = bundle.getParcelable("rekamMedis");
        position = bundle.getInt("position");

        imageView = (ImageView) findViewById(R.id.imageView);
        textViewTanggalPendampingan = (TextView) findViewById(R.id.textViewTanggalPendampingan);
        textViewSesiPendampingan = (TextView) findViewById(R.id.textViewSesiPendampingan);
        textViewKondisiPermasalahanFisik = (TextView) findViewById(R.id.textViewKondisiPermasalahanFisik);
        textViewKendalaMentalSkill = (TextView) findViewById(R.id.textViewKendalaMentalSkill);
        textViewCatatanPsikolog = (TextView) findViewById(R.id.textViewCatatanPsikolog);

        getContent();
    }

    private void getContent() {
        if(rekamMedis.getSesiLatihan()==1)
            imageView.setImageResource(R.drawable.laporan1);
        else if(rekamMedis.getSesiLatihan()==2)
            imageView.setImageResource(R.drawable.laporan2);
        else if(rekamMedis.getSesiLatihan()==3)
            imageView.setImageResource(R.drawable.laporan3);
        else if(rekamMedis.getSesiLatihan()==4)
            imageView.setImageResource(R.drawable.laporan4);
        else imageView.setImageResource(R.drawable.akun);

        textViewTanggalPendampingan.setText(rekamMedis.getWaktuInput());
        textViewSesiPendampingan.setText("Sesi "+rekamMedis.getSesiLatihan());
        textViewKondisiPermasalahanFisik.setText(rekamMedis.getCatatanFisik());
        textViewKendalaMentalSkill.setText(rekamMedis.getKendalaMentalSkill());
        textViewCatatanPsikolog.setText(rekamMedis.getIsiRm());
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }
}
