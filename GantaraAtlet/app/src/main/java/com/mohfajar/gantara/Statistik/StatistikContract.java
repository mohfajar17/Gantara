package com.mohfajar.gantara.Statistik;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;
import com.mohfajar.gantara.Data.Statistik;

import java.util.List;

public interface StatistikContract {
    interface View extends BaseView<Presenter> {
        void tampilkanStatistik(List<Statistik> statistikList);
    }

    interface Presenter extends BasePresenter {
        void statistik(String idAtlet);
    }
}
