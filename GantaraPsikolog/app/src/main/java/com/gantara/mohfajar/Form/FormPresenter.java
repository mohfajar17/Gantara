package com.gantara.mohfajar.Form;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gantara.mohfajar.Data.Form;
import com.gantara.mohfajar.Data.source.Form.FormDataSource;
import com.gantara.mohfajar.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FormPresenter implements FormContract.Presenter{

    private FormContract.View mFormView;

    private FormRepository mFormRepository;

    public FormPresenter(@NonNull FormRepository formRepository, @NonNull FormContract.View formView){
        this.mFormRepository = checkNotNull(formRepository);
        this.mFormView = checkNotNull(formView);

        formView.setPresenter(this);
    }

    @Override
    public void form(String idPsikolog) {
        mFormView.showLoading();
        mFormRepository.getForm(new FormDataSource.FormCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mFormView.hideLoading();
                switch (code){
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
            public void onRequestSuccess(@Nullable List<Form> data) {
                mFormView.hideLoading();
                mFormView.tampilkanForm(data);
            }
        }, idPsikolog);

    }

    @Override
    public void start() {

    }
}
