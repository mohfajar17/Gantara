package com.mohfajar.gantara.Form;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mohfajar.gantara.Data.source.Form.FormDataSource;
import com.mohfajar.gantara.RequestStatus;

import static com.google.common.base.Preconditions.checkNotNull;

public class FormPresenter implements FormContract.Presenter{

    private FormContract.View mFormView;
    private FormRepository mformRepository;

    public FormPresenter(@NonNull FormRepository formRepository, @NonNull FormContract.View formView){
        this.mFormView = checkNotNull(formView);
        this.mformRepository = checkNotNull(formRepository);

        formView.setPresenter(this);
    }

    @Override
    public void form(String idAtlet, String idPsikolog, String sesiLatihan, String antusiasmePreLatihan, String antusiasmePosLatihan, String fisik, String catatanFisik, String stres, String konsentrasi, String keyakinan, String target, String lelah, String komunikasi, String intensitas, String hidrasi, String tidur, String nutrisi, String recovery, String recoveryLain, String mentalSkill, String mentalSkillLain, String kendalaMentalSkill, String saranMasalah, String saranMasalahLain, String halPositif, String scoringMental, String scoringFisik, String intensitasTarget) {
        mFormView.showLoading();
        mformRepository.setForm(new FormDataSource.InsertFormCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mFormView.hideLoading();
                switch (code) {
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mFormView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mFormView.hideLoading();
                mFormView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable Object data) {
                mFormView.hideLoading();
                mFormView.launchHomeActivity();
            }
        }, idAtlet, idPsikolog, sesiLatihan, antusiasmePreLatihan, antusiasmePosLatihan, fisik, catatanFisik, stres, konsentrasi, keyakinan, target, lelah, komunikasi, intensitas, hidrasi, tidur, nutrisi, recovery, recoveryLain, mentalSkill, mentalSkillLain, kendalaMentalSkill, saranMasalah, saranMasalahLain, halPositif, scoringMental, scoringFisik, intensitasTarget);
    }

    @Override
    public void start() {

    }
}
