package com.gantara.mohfajar.DetailAtlet.Statistik;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;
import com.gantara.mohfajar.Data.Statistik;

import java.util.List;

public interface StatistikContract {
    interface View extends BaseView<Presenter> {
        void tampilkanStatistik(List<Statistik> statistikList);
    }

    interface Presenter extends BasePresenter {
        void statistik(String idAtlet);
    }
}
