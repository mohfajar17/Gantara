package com.gantara.mohfajar.DetailAtlet.Intensitas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.gantara.mohfajar.Data.Atlet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IntensitasActivity extends AppCompatActivity {
    private EditText editTextTarget;
    private TextView textViewButton;

    private Bundle bundle;

    ProgressDialog progressDialog;
    String idAtlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intensitas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Proses");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        bundle = getIntent().getExtras();
        idAtlet = bundle.getString("idAtlet");

        editTextTarget = (EditText) findViewById(R.id.editTextTarget);
        textViewButton = (TextView) findViewById(R.id.textViewButton);
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextTarget.getText().toString().matches("") || Integer.parseInt(editTextTarget.getText().toString())>100){
                    Toast.makeText(IntensitasActivity.this, "input anda salah!\nintensitas tidak boleh melebihi 100", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_INTENSITAS,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int status = jsonObject.getInt("status");
                                        if (status == 1) {
                                            progressDialog.dismiss();
                                            Toast.makeText(IntensitasActivity.this, "berhasil untuk menyimpan", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent();
                                            intent.putExtra("intensitas", editTextTarget.getText().toString());
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(IntensitasActivity.this, "gagal untuk menyimpan", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(IntensitasActivity.this, "gagal total", Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    progressDialog.dismiss();
                                    Toast.makeText(IntensitasActivity.this, "terjadi kesalahan jaringan, coba periksa jaringan internet ada", Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("idAtlet", idAtlet);
                            param.put("intensitas", String.valueOf(editTextTarget.getText()));
                            return param;
                        }
                    };
                    Volley.newRequestQueue(IntensitasActivity.this).add(request);
                }
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
}
