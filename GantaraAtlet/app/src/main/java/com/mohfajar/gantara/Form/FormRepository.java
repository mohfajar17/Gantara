package com.mohfajar.gantara.Form;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.Form.FormDataSource;
import com.mohfajar.gantara.Data.source.Form.FormRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class FormRepository implements FormDataSource {

    private static FormRepository INSTANCE;
    private FormRemoteDataSource formRemoteDataSource;

    public static FormRepository getInstance(@NonNull FormRemoteDataSource formRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new FormRepository(formRemoteDataSource);
        }
        return INSTANCE;
    }

    public FormRepository(@NonNull FormRemoteDataSource formRemoteDataSource) {
        this.formRemoteDataSource = checkNotNull(formRemoteDataSource);
    }

    @Override
    public void setForm(@NonNull InsertFormCallBack callback, String idAtlet, String idPsikolog, String sesiLatihan, String antusiasmePreLatihan, String antusiasmePosLatihan, String fisik, String catatanFisik, String stres, String konsentrasi, String keyakinan, String target, String lelah, String komunikasi, String intensitas, String hidrasi, String tidur, String nutrisi, String recovery, String recoveryLain, String mentalSkill, String mentalSkillLain, String kendalaMentalSkill, String saranMasalah, String saranMasalahLain, String halPositif, String scoringMental, String scoringFisik, String intensitasTarget) {
        formRemoteDataSource.setForm(callback, idAtlet, idPsikolog, sesiLatihan, antusiasmePreLatihan, antusiasmePosLatihan, fisik, catatanFisik, stres, konsentrasi, keyakinan, target, lelah, komunikasi, intensitas, hidrasi, tidur, nutrisi, recovery, recoveryLain, mentalSkill, mentalSkillLain, kendalaMentalSkill, saranMasalah, saranMasalahLain, halPositif, scoringMental, scoringFisik, intensitasTarget);
    }
}