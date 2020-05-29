package com.gantara.mohfajar.TambahInfo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Injection;
import com.gantara.mohfajar.MainActivity;
import com.gantara.mohfajar.RunTime;
import com.gantara.mohfajar.SharedPrefManager;
import com.gantara.mohfajar.Signup.SignupActivity;

public class TambahInfoActivity extends AppCompatActivity implements TambahInfoContract.View{

    private ProgressDialog mProgessDialog;
    private EditText editTextJudul;
    private EditText editTextDari;
    private EditText editTextUntuk;
    private EditText editTextIsiInfo;
    private TambahInfoContract.Presenter mTambahInfoPresenter;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_info);
        editTextJudul = (EditText) findViewById(R.id.editTextJudul);
        editTextDari = (EditText) findViewById(R.id.editTextDari);
        editTextUntuk = (EditText) findViewById(R.id.editTextUntuk);
        editTextIsiInfo = (EditText) findViewById(R.id.editTextIsiInfo);
        Button buttonSimpanInfo = (Button) findViewById(R.id.buttonSimpanInfo);
        buttonSimpanInfo.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                InsertInfo();
            }
        });
        sharedPrefManager = SharedPrefManager.getInstance(this);
        mProgessDialog = new ProgressDialog(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new TambahInfoPresenter(Injection.provideTambahInfoRepository(this), this);
    }

    private void InsertInfo() {
        final String idPsikolog = sharedPrefManager.getPsikologID();
        final String judul = editTextJudul.getText().toString();
        final String dari = editTextDari.getText().toString();
        final String untuk = editTextUntuk.getText().toString();
        final String isiInfo = editTextIsiInfo.getText().toString();
        RunTime.logTime();
        mTambahInfoPresenter.tambahInfo(idPsikolog, judul, dari, untuk, isiInfo);
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
    public void launchHomeActivity() {
        RunTime.logTime();
        onBackPressed();
    }

    @Override
    public void setPresenter(TambahInfoContract.Presenter presenter) {
        this.mTambahInfoPresenter = presenter;
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
}
