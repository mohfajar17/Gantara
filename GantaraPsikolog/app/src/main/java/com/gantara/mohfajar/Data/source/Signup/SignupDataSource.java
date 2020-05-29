package com.gantara.mohfajar.Data.source.Signup;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;

public interface SignupDataSource {
    interface InsertSignupCallBack extends BaseDataSourceCallback<Object> {

    }

    void setSignup(@NonNull InsertSignupCallBack callback, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String noTelfon, String photo);
}
