package com.mohfajar.gantara.Signup;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.Signup.SignupDataSource;
import com.mohfajar.gantara.Data.source.Signup.SignupRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignupRepository implements SignupDataSource {

    private static SignupRepository INSTANCE;
    private SignupRemoteDataSource signupRemoteDataSource;

    public static SignupRepository getInstance(@NonNull SignupRemoteDataSource signupRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new SignupRepository(signupRemoteDataSource);
        }
        return INSTANCE;
    }

    public SignupRepository(@NonNull SignupRemoteDataSource signupRemoteDataSource) {
        this.signupRemoteDataSource = checkNotNull(signupRemoteDataSource);
    }

    @Override
    public void setSignup(@NonNull InsertSignupCallBack callback, String idPsikolog, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String asalKota, String noTelfon, String cabor, String photo) {
        signupRemoteDataSource.setSignup(callback, idPsikolog, userName, password, nama, jenisKelamin, tempatLahir, tanggalLahir, alamat, asalKota, noTelfon, cabor, photo);
    }
}
