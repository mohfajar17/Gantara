package com.mohfajar.gantara.Data.source.Form;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;

public interface FormDataSource {
    interface InsertFormCallBack extends BaseDataSourceCallback<Object>{

    }

    void setForm(@NonNull InsertFormCallBack callback, String idAtlet, String idPsikolog, String sesiLatihan, String antusiasmePreLatihan, String antusiasmePosLatihan, String fisik, String catatanFisik, String stres, String konsentrasi, String keyakinan, String target, String lelah, String komunikasi, String intensitas, String hidrasi, String tidur, String nutrisi, String recovery, String recoveryLain, String mentalSkill, String mentalSkillLain, String kendalaMentalSkill, String saranMasalah, String saranMasalahLain, String halPositif, String scoringMental, String scoringFisik, String intensitasTarget);
}