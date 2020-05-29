package com.gantara.mohfajar.Atlet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.Data.source.Atlet.AtletDataSource;
import com.gantara.mohfajar.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class AtletPresenter implements AtletContract.Presenter {

    private AtletContract.View mAtletView;

    private AtletRepository mAtletRepository;

    public AtletPresenter(@NonNull AtletRepository atletRepository, @NonNull AtletContract.View atletView){
        this.mAtletRepository = checkNotNull(atletRepository);
        this.mAtletView = checkNotNull(atletView);

        atletView.setPresenter(this);
    }

    @Override
    public void atlet(String idPsikolog) {
        mAtletView.showLoading();
        mAtletRepository.getAtlet(new AtletDataSource.AtletCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mAtletView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mAtletView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mAtletView.hideLoading();
                mAtletView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable List<Atlet> data) {
                mAtletView.hideLoading();
                mAtletView.tampilkanAtlet(data);
            }
        }, idPsikolog);
    }

    @Override
    public void start() {
    }
}
