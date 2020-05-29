package com.gantara.mohfajar.Signup;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.source.Signup.SignupDataSource;
import com.gantara.mohfajar.Data.source.Signup.SignupRemoteDataSource;

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
    public void setSignup(@NonNull InsertSignupCallBack callback, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String noTelfon, String photo) {
        signupRemoteDataSource.setSignup(callback, userName, password, nama, jenisKelamin, tempatLahir, tanggalLahir, alamat, noTelfon, photo);
    }
}
