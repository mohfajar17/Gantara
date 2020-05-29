package com.mohfajar.gantara.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.InstanceIdResult;
import com.mohfajar.gantara.API;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.DownloadImageTask;
import com.mohfajar.gantara.MainActivity;
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.SharedPrefManager;
import com.mohfajar.gantara.Signup.SignActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    private EditText editTextUserName;
    private EditText editTextPassword;

    private Button btnMasuk;
    private Button btnDaftar;
    private ProgressDialog progressDialog;
    private String token;

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName= (EditText) findViewById(R.id.editTextUsername);

        btnMasuk = (Button) findViewById(R.id.buttonMasuk);
        btnMasuk.setOnClickListener(this);
        btnDaftar = (Button) findViewById(R.id.buttonDaftar);
        btnDaftar.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Proses");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        sharedPrefManager = SharedPrefManager.getInstance(this);

        if(sharedPrefManager.isLoggedIn()){
            Intent bukaMainActivity = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(bukaMainActivity);
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivity.this,new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonMasuk:
                loginAtlet();
                break;
            case R.id.buttonDaftar:
                Intent bukaVerifikasiActivity = new Intent(this, SignActivity.class);
                startActivity(bukaVerifikasiActivity);
                break;
        }
    }

    private void loginAtlet() {
        progressDialog.show();

        final String password = editTextPassword.getText().toString();
        final String userName = editTextUserName.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status=jsonObject.getInt("status");
                    if(status==1){
                        JSONObject jsonData = jsonObject.getJSONObject(API.PARAM_DATA);

                        String id_atlet = jsonData.getString(API.PARAM_ID_ATLET);
                        String id_psikolog = jsonData.getString(API.PARAM_ID_PSIKOLOG);
                        String name = jsonData.getString(API.PARAM_NAMA);
                        String cabor = jsonData.getString(API.PARAM_CABANG_OLAHRAGA);
                        int jenis_kelamin = Integer.valueOf(jsonData.getString(API.PARAM_JENIS_KELAMIN));
                        String tempat_lahir = jsonData.getString(API.PARAM_TEMPAT_LAHIR);
                        String tanggal_lahir = jsonData.getString(API.PARAM_TANGGAL_LAHIR);
                        String alamat = jsonData.getString(API.PARAM_ALAMAT);
                        String kota_asal = jsonData.getString(API.PARAM_KOTA_ASAL);
                        String no_telp = jsonData.getString(API.PARAM_NO_TELP);
                        String user_name = jsonData.getString(API.PARAM_USER_NAME);
                        String photo = jsonData.getString(API.PARAM_PHOTO_PROFILE);
                        float intensitas_target = (float) jsonData.getDouble(API.PARAM_INTENSITAS_TARGET);

                        sharedPrefManager.loginAtlet(id_atlet, id_psikolog, name, cabor, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, kota_asal, no_telp, user_name, photo, intensitas_target);

                        new DownloadImageTask(LoginActivity.this).execute(Config.DATA_URL_PHOTO_PROFIL+"/"+sharedPrefManager.getPhoto());

                        progressDialog.dismiss();

                        Intent bukaMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        startActivityForResult(bukaMainActivity, 25);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "username dan password anda salah", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "terjadi kesalahan jaringan, periksa jaringan internet anda", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<>();
                param.put("userName", userName);
                param.put("pass", password);
                param.put("token", token);
                return param;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
