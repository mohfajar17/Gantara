package com.gantara.mohfajar.Profile;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.SharedPrefManager;
import com.squareup.picasso.Picasso;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    //initial
    Button buttonSimpan;

    SharedPrefManager sharedPrefManager;

    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextKonfirmasiPassword;
    EditText editTextNama;
    EditText editTextJenisKelamin;
    EditText editTextTempatLahir;
    EditText editTextTanggalLahir;
    EditText editTextAlamat;
    EditText editTextTelefon;

    ProgressDialog progressDialog;

    RelativeLayout gantiFoto;
    ImageView imageViewGantiFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPrefManager = SharedPrefManager.getInstance(this);

        //tampilkan button kembali
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Proses");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        imageViewGantiFoto = (ImageView) findViewById(R.id.imageViewGantiFoto);
        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+sharedPrefManager.getUserName()+".png").resize(700, 700).centerCrop().into(imageViewGantiFoto);

        gantiFoto = (RelativeLayout) findViewById(R.id.gantiFoto);
//        gantiFoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GalleryConfig config = new GalleryConfig.Build()
//                        .limitPickPhoto(1)
//                        .singlePhoto(true)
//                        .hintOfPick("Silahkan pilih gambar")
//                        .build();
//                GalleryActivity.openActivity(ProfileActivity.this,10,config);
//            }
//        });

        editTextEmail = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail.setText(sharedPrefManager.getUserName());
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextKonfirmasiPassword = (EditText) findViewById(R.id.editTextKonfirmasiPassword);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextNama.setText(sharedPrefManager.getName());
        editTextJenisKelamin = (EditText) findViewById(R.id.editTextJenisKelamin);
        if(sharedPrefManager.getJenisKelamin()==1)
            editTextJenisKelamin.setText("Laki-laki");
        else editTextJenisKelamin.setText("Perempuan");
        editTextTempatLahir = (EditText) findViewById(R.id.editTextTempatLahir);
        editTextTempatLahir.setText(sharedPrefManager.getTempatLahir());
        editTextTanggalLahir = (EditText) findViewById(R.id.editTextTanggalLahir);
        editTextTanggalLahir.setText(sharedPrefManager.getTanggalLahir());
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextAlamat.setText(sharedPrefManager.getAlamat());
        editTextTelefon = (EditText) findViewById(R.id.editTextTelefon);
        editTextTelefon.setText(sharedPrefManager.getNoTlp());

        //show dialog
        buttonSimpan = (Button) findViewById(R.id.buttonSimpanProfile);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextEmail.getText().toString().matches("")
                        || editTextPassword.getText().toString().matches("")
                        || editTextKonfirmasiPassword.getText().toString().matches("")
                        || editTextAlamat.getText().toString().matches("")
                        || editTextTelefon.getText().toString().matches("")){
                    Toast.makeText(ProfileActivity.this, "data anda belum lengkap!", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_PROFILE,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int status=jsonObject.getInt("status");
                                        if(status==1){
                                            sharedPrefManager.loadDataPsikolog(sharedPrefManager.getPsikologID(), editTextAlamat.getText().toString(), editTextTelefon.getText().toString(), editTextEmail.getText().toString());
                                            progressDialog.dismiss();
                                            Toast.makeText(ProfileActivity.this, "perubahan berhasil disimpan", Toast.LENGTH_LONG).show();
                                            onBackPressed();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(ProfileActivity.this, "konfirmasi password anda salah", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(ProfileActivity.this, "gagal total", Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    progressDialog.dismiss();
                                    Toast.makeText(ProfileActivity.this, "terjadi kesalahan jaringan, coba periksa jaringan internet ada", Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param=new HashMap<>();
                            param.put("idPsikolog", sharedPrefManager.getPsikologID());
                            param.put("userName", editTextEmail.getText().toString());
                            param.put("password", editTextPassword.getText().toString());
                            param.put("passKonfirm", editTextKonfirmasiPassword.getText().toString());
                            param.put("alamat", editTextAlamat.getText().toString());
                            param.put("telp", editTextTelefon.getText().toString());
                            return param;
                        }
                    };
                    Volley.newRequestQueue(ProfileActivity.this).add(request);
                }
            }
        });
    }
}
