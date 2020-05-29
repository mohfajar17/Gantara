package com.mohfajar.gantara.Info;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;
import com.mohfajar.gantara.Data.Info;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter>{
        void tampilkanInfo(List<Info> infoList);
    }

    interface Presenter extends BasePresenter{

    }


}
