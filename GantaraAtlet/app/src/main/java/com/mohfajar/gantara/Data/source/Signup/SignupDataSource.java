package com.mohfajar.gantara.Data.source.Signup;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;

public interface SignupDataSource {
    interface InsertSignupCallBack extends BaseDataSourceCallback<Object> {

    }

    void setSignup(@NonNull InsertSignupCallBack callback, String idPsikolog, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String asalKota, String noTelfon, String cabor, String photo);
}
