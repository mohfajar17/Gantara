package com.mohfajar.gantara.Form;

import com.mohfajar.gantara.BasePresenter;
import com.mohfajar.gantara.BaseView;

public interface FormContract {
    interface View extends BaseView<FormContract.Presenter>{
        void launchHomeActivity();
    }

    interface Presenter extends BasePresenter {
        void form(String idAtlet, String idPsikolog, String sesiLatihan, String antusiasmePreLatihan, String antusiasmePosLatihan, String fisik, String catatanFisik, String stres, String konsentrasi, String keyakinan, String target, String lelah, String komunikasi, String intensitas, String hidrasi, String tidur, String nutrisi, String recovery, String recoveryLain, String mentalSkill, String mentalSkillLain, String kendalaMentalSkill, String saranMasalah, String saranMasalahLain, String halPositif, String scoringMental, String scoringFisik, String intensitasTarget);
    }
}
