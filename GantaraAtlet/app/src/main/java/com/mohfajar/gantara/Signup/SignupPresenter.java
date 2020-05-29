package com.mohfajar.gantara.Signup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mohfajar.gantara.Data.source.Signup.SignupDataSource;
import com.mohfajar.gantara.RequestStatus;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignupPresenter implements SignupContract.Presenter {

    private SignupContract.View mSignupView;

    private SignupRepository mSignupRepostory;

    public SignupPresenter(@NonNull SignupRepository signupRepository, @NonNull SignupContract.View signupView) {
        this.mSignupView = checkNotNull(signupView);
        this.mSignupRepostory = checkNotNull(signupRepository);

        signupView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void signup(String idPsikolog, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String asalKota, String noTelfon, String cabor, String photo) {
        mSignupView.showLoading();
        mSignupRepostory.setSignup(new SignupDataSource.InsertSignupCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mSignupView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                        default:
                            mSignupView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mSignupView.hideLoading();
                mSignupView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable Object data) {
                mSignupView.launchHomeActivity();
            }
        }, idPsikolog, userName, password, nama, jenisKelamin, tempatLahir, tanggalLahir, alamat, asalKota, noTelfon, cabor, photo);
    }
}
