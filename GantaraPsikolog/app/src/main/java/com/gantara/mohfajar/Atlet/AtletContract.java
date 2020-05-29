package com.gantara.mohfajar.Atlet;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;
import com.gantara.mohfajar.Data.Atlet;

import java.util.List;

public interface AtletContract {
    interface View extends BaseView<Presenter> {
        void tampilkanAtlet(List<Atlet> atlets);
    }

    interface Presenter extends BasePresenter {
        void atlet(String idPsikolog);
    }
}
