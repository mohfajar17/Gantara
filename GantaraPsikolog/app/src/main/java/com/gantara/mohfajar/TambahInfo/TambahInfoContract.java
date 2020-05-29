package com.gantara.mohfajar.TambahInfo;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;

public interface TambahInfoContract {
    interface View extends BaseView<Presenter> {
        void launchHomeActivity();
    }

    interface Presenter extends BasePresenter {
        void tambahInfo(String idPsikolog, String judul, String dari, String untuk, String isiInfo);
    }
}
