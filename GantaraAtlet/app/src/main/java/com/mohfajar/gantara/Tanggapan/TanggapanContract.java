package com.mohfajar.gantara.Tanggapan;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;
import com.mohfajar.gantara.Data.Tanggapan;

import java.util.List;

public interface TanggapanContract {
    interface View extends BaseView<Presenter> {
        void tampilkanTanggapan(List<Tanggapan> tanggapanList);
    }

    interface Presenter extends BasePresenter {
        void tanggapan(String idAtlet);
    }
}
