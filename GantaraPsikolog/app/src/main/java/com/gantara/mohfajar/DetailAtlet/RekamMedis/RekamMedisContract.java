package com.gantara.mohfajar.DetailAtlet.RekamMedis;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;
import com.gantara.mohfajar.Data.RekamMedis;

import java.util.List;

public interface RekamMedisContract {
    interface View extends BaseView<Presenter> {
        void tampilkanRekamMedis(List<RekamMedis> rekamMedisList);
    }

    interface Presenter extends BasePresenter {
        void rekamMedis(String idAtlet);
    }
}
