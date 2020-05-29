package com.gantara.mohfajar.Info;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;
import com.gantara.mohfajar.Data.Info;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void tampilkanInfo(List<Info> infoList);
    }

    interface Presenter extends BasePresenter {

    }


}
