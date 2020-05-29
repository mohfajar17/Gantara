package com.mohfajar.gantara.Tanggapan;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.Data.Tanggapan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TanggapanActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewNamaTimPsikolog;
    private TextView textViewTanggalPendampingan;
    private TextView textViewIsiTanggapan;

    private int position;
    private Tanggapan tanggapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggapan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        tanggapan = bundle.getParcelable("tanggapan");
        position = bundle.getInt("position");

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView);
        textViewNamaTimPsikolog = (TextView) findViewById(R.id.textViewNamaTimPsikolog);
        textViewTanggalPendampingan = (TextView) findViewById(R.id.textViewTanggalPendampingan);
        textViewIsiTanggapan = (TextView) findViewById(R.id.textViewIsiTanggapan);

        getContent();
        updateReadStatus();
    }

    private void getContent() {
        if(tanggapan.getSesiLatihan()==1)
            imageView.setImageResource(R.drawable.laporan1);
        else if(tanggapan.getSesiLatihan()==2)
            imageView.setImageResource(R.drawable.laporan2);
        else if(tanggapan.getSesiLatihan()==3)
            imageView.setImageResource(R.drawable.laporan3);
        else if(tanggapan.getSesiLatihan()==4)
            imageView.setImageResource(R.drawable.laporan4);
        else imageView.setImageResource(R.drawable.akun);

        textViewNamaTimPsikolog.setText("Sesi "+tanggapan.getSesiLatihan());
        textViewTanggalPendampingan.setText(tanggapan.getWaktuInput());
        textViewIsiTanggapan.setText(tanggapan.getIsiRm());
    }

    private void updateReadStatus(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_READ_REKAM_MEDIS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int status=jsonObject.getInt("status");
                                    if(status==1){
                                    } else {
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param=new HashMap<>();
                        param.put("idForm", String.valueOf(tanggapan.getIdForm()));
                        return param;
                    }
                };
                Volley.newRequestQueue(TanggapanActivity.this).add(request);
            }
        });
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
