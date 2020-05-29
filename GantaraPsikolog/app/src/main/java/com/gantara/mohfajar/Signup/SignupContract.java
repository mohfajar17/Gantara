package com.gantara.mohfajar.Signup;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;

public interface SignupContract {
    interface View extends BaseView<Presenter> {
        void launchHomeActivity();
    }

    interface Presenter extends BasePresenter {
        void signup(String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String noTelfon, String photo);
    }
}
