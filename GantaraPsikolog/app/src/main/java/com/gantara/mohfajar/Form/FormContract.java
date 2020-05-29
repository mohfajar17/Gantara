package com.gantara.mohfajar.Form;

import com.gantara.mohfajar.BasePresenter;
import com.gantara.mohfajar.BaseView;
import com.gantara.mohfajar.Data.Form;

import java.util.List;

public interface FormContract {
    interface View extends BaseView<Presenter> {
        void tampilkanForm(List<Form> formList);
    }

    interface Presenter extends BasePresenter {
        void form(String idPsikolog);
    }
}
