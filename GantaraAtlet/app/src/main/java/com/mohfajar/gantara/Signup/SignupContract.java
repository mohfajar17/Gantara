package com.mohfajar.gantara.Signup;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;

public interface SignupContract {
    interface View extends BaseView<Presenter> {
        void launchHomeActivity();
    }

    interface Presenter extends BasePresenter {
        void signup(String idPsikolog, String userName, String password, String nama, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String asalKota, String noTelfon, String cabor, String photo);
    }
}
