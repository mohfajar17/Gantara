package com.mohfajar.gantara.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Base64;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.DownloadImageTask;
import com.mohfajar.gantara.SharedPrefManager;
import com.squareup.picasso.Picasso;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    EditText editTextAsalKota;
    EditText editTextTelefon;
    EditText editTextCabangOlahraga;
    EditText editTextIdPsikolog;

    ProgressDialog progressDialog;

    String stringPilihFoto;
    String photo;

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
        gantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(1)
                        .singlePhoto(true)
                        .hintOfPick("Silahkan pilih gambar")
                        .build();
                GalleryActivity.openActivity(ProfileActivity.this,10,config);
            }
        });

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
        editTextAsalKota = (EditText) findViewById(R.id.editTextAsalKota);
        editTextAsalKota.setText(sharedPrefManager.getKotaAsal());
        editTextTelefon = (EditText) findViewById(R.id.editTextTelefon);
        editTextTelefon.setText(sharedPrefManager.getNoTlp());
        editTextCabangOlahraga = (EditText) findViewById(R.id.editTextCabangOlahraga);
        editTextCabangOlahraga.setText(sharedPrefManager.getCabor());
        editTextIdPsikolog = (EditText) findViewById(R.id.editTextIdPsikolog);
        editTextIdPsikolog.setText(sharedPrefManager.getPsikologID());

        //show dialog
        buttonSimpan = (Button) findViewById(R.id.buttonSimpanProfile);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextEmail.getText().toString().matches("")
                        || editTextPassword.getText().toString().matches("")
                        || editTextKonfirmasiPassword.getText().toString().matches("")
                        || editTextAlamat.getText().toString().matches("")
                        || editTextAsalKota.getText().toString().matches("")
                        || editTextTelefon.getText().toString().matches("")
                        || editTextCabangOlahraga.getText().toString().matches("")
                        || editTextIdPsikolog.getText().toString().matches("")){
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
                                            sharedPrefManager.loadDataAtlet(editTextIdPsikolog.getText().toString(), editTextCabangOlahraga.getText().toString(), editTextAlamat.getText().toString(), editTextAsalKota.getText().toString(), editTextTelefon.getText().toString(), editTextEmail.getText().toString());
                                            progressDialog.dismiss();
                                            Toast.makeText(ProfileActivity.this, "perubahan berhasil disimpan", Toast.LENGTH_LONG).show();
                                            onBackPressed();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(ProfileActivity.this, "konfirmasi password anda salah", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
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
                            param.put("idAtlet", sharedPrefManager.getAtletID());
                            param.put("idPsikolog", editTextIdPsikolog.getText().toString());
                            param.put("userName", editTextEmail.getText().toString());
                            param.put("pass", editTextPassword.getText().toString());
                            param.put("passKonf", editTextKonfirmasiPassword.getText().toString());
                            param.put("alamat", editTextAlamat.getText().toString());
                            param.put("kotaAsal", editTextAsalKota.getText().toString());
                            param.put("noTelfon", editTextTelefon.getText().toString());
                            param.put("cabor", editTextCabangOlahraga.getText().toString());
                            return param;
                        }
                    };
                    Volley.newRequestQueue(ProfileActivity.this).add(request);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && data!=null && requestCode == 10){
            String compressed = ((List<String>)data.getSerializableExtra(GalleryActivity.PHOTOS)).get(0);

            //imageBase64 = fileHandler.saveTempImage(compressed);
            Uri uri = Uri.fromFile(new File(compressed));
            Picasso.get()
                    .load(uri)
                    .resize(50, 50)
                    .centerCrop()
                    .into(imageViewGantiFoto);
            Bitmap bm = BitmapFactory.decodeFile(compressed);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            try {
                progressDialog.show();

                stringPilihFoto = Base64.encodeBytes(byteArrayImage, Base64.NO_OPTIONS);

                StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_UPDATE_PHOTO_PROFILE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int status=jsonObject.getInt("status");
                                    if(status==1){
                                        //new DownloadImageTask(ProfileActivity.this).execute(Config.DATA_URL_PROFIL_PHOTO+"/"+sharedPrefManager.getPhoto());
                                        progressDialog.dismiss();
                                        Toast.makeText(ProfileActivity.this, "success upload foto", Toast.LENGTH_LONG).show();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(ProfileActivity.this, "gagal upload foto", Toast.LENGTH_LONG).show();
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
                        param.put("idAtlet", sharedPrefManager.getAtletID());
                        param.put("photoProfile", stringPilihFoto);
                        return param;
                    }
                };
                Volley.newRequestQueue(ProfileActivity.this).add(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
