package com.mohfajar.gantara.RekamMedis;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;
import com.mohfajar.gantara.Data.Tanggapan;

import java.util.List;

public interface RekamMedisContract {
    interface View extends BaseView<Presenter> {
        void tampilkanRekamMedis(List<Tanggapan> rekamMedisList);
    }

    interface Presenter extends BasePresenter {
        void rekamMedis(String idAtlet);
    }
}
