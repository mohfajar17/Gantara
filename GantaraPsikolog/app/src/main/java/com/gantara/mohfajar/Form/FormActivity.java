package com.gantara.mohfajar.Form;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.Form;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private int position;
    private Form formPsikolog;

    private TextView editTextFIdAtlet;
    private TextView editTextFNama;
    private TextView editTextFCabangOlahraga;
    private TextView editTextFWaktuInput;
    private RadioButton radioButtonFSesiLatihan1;
    private RadioButton radioButtonFSesiLatihan2;
    private RadioButton radioButtonFSesiLatihan3;
    private RadioButton radioButtonFSesiLatihan4;
    private TextView editTextFCatatanFisik;
    private TextView editTextFKendalaMentalSkill;
    private RadioButton radioButtonFTindakLanjut1;
    private RadioButton radioButtonFTindakLanjut2;
    private RadioButton radioButtonFTindakLanjut3;
    private TextView editTextFTindakLanjutLain;
    private TextView editTextFHalPositif;
    private TextView editTextFScoringKondisiMental;
    private TextView editTextFScoringKondisiFisik;
    private TextView editTextFScoringIntensitasLatihanAtlet;
    private TextView editTextFScoringIntensitasLatihanPelatih;
    private EditText editTextFCatatanForm;

    private Button buttonFSimpanForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        formPsikolog = bundle.getParcelable("form");
        position = bundle.getInt("position");
        Toast.makeText(this, "Iki jeneng " +formPsikolog.getNama()+ " & " +formPsikolog.getIdAtlet(), Toast.LENGTH_SHORT).show();

        editTextFIdAtlet = (TextView) findViewById(R.id.editTextFIdAtlet);
        editTextFNama = (TextView) findViewById(R.id.editTextFNama);
        editTextFCabangOlahraga = (TextView) findViewById(R.id.editTextFCabangOlahraga);
        editTextFWaktuInput = (TextView) findViewById(R.id.editTextFWaktuInput);
        radioButtonFSesiLatihan1 = (RadioButton) findViewById(R.id.radioButtonFSesiLatihan1);
        radioButtonFSesiLatihan2 = (RadioButton) findViewById(R.id.radioButtonFSesiLatihan2);
        radioButtonFSesiLatihan3 = (RadioButton) findViewById(R.id.radioButtonFSesiLatihan3);
        radioButtonFSesiLatihan4 = (RadioButton) findViewById(R.id.radioButtonFSesiLatihan4);
        editTextFCatatanFisik = (TextView) findViewById(R.id.editTextFCatatanFisik);
        editTextFKendalaMentalSkill = (TextView) findViewById(R.id.editTextFKendalaMentalSkill);
        radioButtonFTindakLanjut1 = (RadioButton) findViewById(R.id.radioButtonFTindakLanjut1);
        radioButtonFTindakLanjut2 = (RadioButton) findViewById(R.id.radioButtonFTindakLanjut2);
        radioButtonFTindakLanjut3 = (RadioButton) findViewById(R.id.radioButtonFTindakLanjut3);
        editTextFTindakLanjutLain = (TextView) findViewById(R.id.editTextFTindakLanjutLain);
        editTextFHalPositif = (TextView) findViewById(R.id.editTextFHalPositif);
        editTextFScoringKondisiMental = (TextView) findViewById(R.id.editTextFScoringKondisiMental);
        editTextFScoringKondisiFisik = (TextView) findViewById(R.id.editTextFScoringKondisiFisik);
        editTextFScoringIntensitasLatihanAtlet = (TextView) findViewById(R.id.editTextFScoringIntensitasLatihanAtlet);
        editTextFScoringIntensitasLatihanPelatih = (TextView) findViewById(R.id.editTextFScoringIntensitasLatihanPelatih);
        editTextFCatatanForm = (EditText) findViewById(R.id.editTextFCatatanForm);

        buttonFSimpanForm = (Button) findViewById(R.id.buttonFSimpanForm);
        buttonFSimpanForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFCatatanForm.getText().toString().matches("")){
                    Toast.makeText(FormActivity.this,"anda belum memasukkan catatan!",Toast.LENGTH_SHORT).show();
                }
                else setCatatan();
            }
        });

        updateReadStatus();

        getIsiForm();
    }

    private void updateReadStatus() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_STATUS_FORM,
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
                        param.put("idForm", String.valueOf(formPsikolog.getIdForm()));
                        return param;
                    }
                };
                Volley.newRequestQueue(FormActivity.this).add(request);
            }
        });
    }

    private void getIsiForm() {
        editTextFIdAtlet.setText(formPsikolog.getIdAtlet());
        editTextFNama.setText(formPsikolog.getNama());
        editTextFCabangOlahraga.setText(formPsikolog.getCabangOlahraga());
        editTextFWaktuInput.setText(formPsikolog.getWaktuInput());

        switch (Integer.valueOf(formPsikolog.getSesiLatihan())){
            case 1:
                radioButtonFSesiLatihan1.setChecked(true);
                radioButtonFSesiLatihan1.setEnabled(true);
                break;
            case 2:
                radioButtonFSesiLatihan2.setChecked(true);
                radioButtonFSesiLatihan2.setEnabled(true);
                break;
            case 3:
                radioButtonFSesiLatihan3.setChecked(true);
                radioButtonFSesiLatihan3.setEnabled(true);
                break;
            case 4:
                radioButtonFSesiLatihan4.setChecked(true);
                radioButtonFSesiLatihan4.setEnabled(true);
                break;
        }

        editTextFCatatanFisik.setText(formPsikolog.getCatatanFisik());
        editTextFKendalaMentalSkill.setText(formPsikolog.getKendalaMentalSkill());

        switch (Integer.valueOf(formPsikolog.getSaranMasalah())){
            case 1:
                radioButtonFTindakLanjut1.setChecked(true);
                radioButtonFTindakLanjut1.setEnabled(true);
                break;
            case 2:
                radioButtonFTindakLanjut2.setChecked(true);
                radioButtonFTindakLanjut2.setEnabled(true);
                break;
            case 3:
                radioButtonFTindakLanjut3.setChecked(true);
                radioButtonFTindakLanjut3.setEnabled(true);
                break;
        }

        editTextFTindakLanjutLain.setText(formPsikolog.getSaranMasalahLain());
        editTextFHalPositif.setText(formPsikolog.getHalPositif());
        editTextFScoringKondisiMental.setText(String.valueOf(formPsikolog.getScoringMental())+"%");
        editTextFScoringKondisiFisik.setText(String.valueOf(formPsikolog.getScoringFisik())+"%");
        editTextFScoringIntensitasLatihanAtlet.setText(formPsikolog.getIntensitas()+"%");
        editTextFScoringIntensitasLatihanPelatih.setText(formPsikolog.getIntensitasTarget()+"%");
    }

    private void setCatatan() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_REKAM_MEDIS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int status=jsonObject.getInt("status");
                                    if(status==1){
                                        Toast.makeText(FormActivity.this, "catatan telah disimpan", Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                    } else {
                                        Toast.makeText(FormActivity.this, "catatan gagal disimpan", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(FormActivity.this, "catatan gagal disimpan, terjadi kesalahan jaringan", Toast.LENGTH_LONG).show();
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
                        param.put("idForm", String.valueOf(formPsikolog.getIdForm()));
                        param.put("catatanPsikolog", editTextFCatatanForm.getText().toString());
                        return param;
                    }
                };
                Volley.newRequestQueue(FormActivity.this).add(request);
            }
        });
    }

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
