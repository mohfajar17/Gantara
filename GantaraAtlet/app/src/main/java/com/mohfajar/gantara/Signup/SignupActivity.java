package com.mohfajar.gantara.Signup;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohfajar.gantara.Base64;
import com.mohfajar.gantara.Injection;
import com.mohfajar.gantara.Login.LoginActivity;
import com.example.mohfajar.gantara.R;
import com.squareup.picasso.Picasso;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class SignupActivity extends AppCompatActivity implements SignupContract.View{

    private boolean doubleBackToExitPressedOnce = false;

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextKonfirmasiPassword;
    private EditText editTextNama;
    private Spinner spinnerJenisKelamin;
    private EditText editTextTempatLahir;
    private EditText editTextTanggalLahir;
    private EditText editTextAlamat;
    private EditText editTextAsalKota;
    private EditText editTextTelefon;
    private EditText editTextIdPsikolog;
    private Spinner spinnerCabangOlahraga;
    private ImageView imageViewPilihFoto;
    private String stringPilihFoto;
    private CheckBox checkBoxKonfirmasi;

    private SignupContract.Presenter mSignupPresenter;

    private ProgressDialog mProgessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUserName = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextKonfirmasiPassword = (EditText) findViewById(R.id.editTextKonfirmasiPassword);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextTempatLahir = (EditText) findViewById(R.id.editTextTempatLahir);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextAsalKota = (EditText) findViewById(R.id.editTextAsalKota);
        editTextTelefon = (EditText) findViewById(R.id.editTextTelefon);
        editTextIdPsikolog= (EditText) findViewById(R.id.editTextIdPsikolog);
        spinnerCabangOlahraga = (Spinner) findViewById(R.id.spinnerCabangOlahraga);
        spinnerJenisKelamin = (Spinner) findViewById(R.id.spinnerJenisKelamin);
        checkBoxKonfirmasi = (CheckBox) findViewById(R.id.checkBoxKonfirmasi);
        Button buttonDaftar = (Button) findViewById(R.id.buttonDaftar);
        buttonDaftar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if(checkBoxKonfirmasi.isChecked())
                    insertDaftar();
                else Toast.makeText(SignupActivity.this, "jika anda sudah yakin, check konfirmasi kebenaran data", Toast.LENGTH_SHORT).show();
            }
        });

        editTextTanggalLahir = (EditText) findViewById(R.id.editTextTanggalLahir);
        editTextTanggalLahir.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                setDate();
            }
        });

        imageViewPilihFoto = (ImageView) findViewById(R.id.imageViewPilihFoto);
        imageViewPilihFoto.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(3)
                        .singlePhoto(true)
                        .hintOfPick("Silahkan pilih gambar")
                        .build();
                GalleryActivity.openActivity(SignupActivity.this,10,config);
            }
        });

        mProgessDialog = new ProgressDialog(this);

        new SignupPresenter(Injection.provideSignupRepository(this),this);
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

    private void setDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextTanggalLahir.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && data!=null && requestCode == 10){
            String compressed = ((List<String>)data.getSerializableExtra(GalleryActivity.PHOTOS)).get(0);
            //imageBase64 = fileHandler.saveTempImage(compressed);
            Uri uri = Uri.fromFile(new File(compressed));
            Picasso.get()
                    .load(uri)
                    .resize(50, 50)
                    .centerCrop()
                    .into(imageViewPilihFoto);
            Bitmap bm = BitmapFactory.decodeFile(compressed);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            try {
                stringPilihFoto = Base64.encodeBytes(byteArrayImage, Base64.NO_OPTIONS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void insertDaftar() {
        final String idPsikolog = editTextIdPsikolog.getText().toString();
        final String nama = editTextNama.getText().toString();
        final String cabangOlahraga = spinnerCabangOlahraga.getSelectedItem().toString();
        final String jenisKelamin;
        final String tempatLahir = editTextTempatLahir.getText().toString();
        final String tanggalLahir = editTextTanggalLahir.getText().toString();
        final String alamat = editTextAlamat.getText().toString();
        final String asalKota = editTextAsalKota.getText().toString();
        final String noTelefon = editTextTelefon.getText().toString();
        final String userName = editTextUserName.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String photoProfile = stringPilihFoto;

        if (spinnerJenisKelamin.getSelectedItemPosition() == 1)
            jenisKelamin = "1";
        else if (spinnerJenisKelamin.getSelectedItemPosition() == 2)
            jenisKelamin = "0";
        else jenisKelamin = "2";

        if (idPsikolog.matches("") || nama.matches("") || cabangOlahraga.matches("") || jenisKelamin.matches("2") || tempatLahir.matches("") || tanggalLahir.matches("") || alamat.matches("") || asalKota.matches("") || noTelefon.matches("") || userName.matches("") || password.matches("") || photoProfile.matches("")) {
            Toast.makeText(SignupActivity.this, "data anda belum lengkap", Toast.LENGTH_LONG).show();
        } else if (!editTextKonfirmasiPassword.getText().toString().matches(editTextPassword.getText().toString())){
            Toast.makeText(SignupActivity.this, "konfirmasi password anda salah", Toast.LENGTH_LONG).show();
        }else {
            mSignupPresenter.signup(idPsikolog, userName, password, nama, jenisKelamin, tempatLahir, tanggalLahir, alamat, asalKota, noTelefon, cabangOlahraga, photoProfile);
        }
    }

    @Override
    public void setPresenter(SignupContract.Presenter presenter) {
        this.mSignupPresenter = presenter;
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
        new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage(message)
                .show();
    }

    @Override
    public void onDataNotAvailable() {
        showMessage(getString(R.string.message_no_data));
    }

    @Override
    public void launchHomeActivity() {
        Intent bukaLoginActivity = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(bukaLoginActivity);
        finish();
    }
}