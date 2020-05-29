package com.gantara.mohfajar.AddAtlet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.Data.source.AddAtlet.AddAtletDataSource;
import com.gantara.mohfajar.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddAtletPresenter implements AddAtletContract.Presenter{

    private AddAtletContract.View mAddAtletView;

    private AddAtletRepository mAddAtletRepository;

    public AddAtletPresenter(@NonNull AddAtletRepository addAtletRepository, @NonNull AddAtletContract.View addAtletView){
        this.mAddAtletRepository = checkNotNull(addAtletRepository);
        this.mAddAtletView = checkNotNull(addAtletView);

        addAtletView.setPresenter(this);
    }

    @Override
    public void atlet(String idPsikolog) {
        mAddAtletView.showLoading();
        mAddAtletRepository.getAddAtlet(new AddAtletDataSource.AddAtletCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mAddAtletView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mAddAtletView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mAddAtletView.hideLoading();
                mAddAtletView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable List<Atlet> data) {
                mAddAtletView.hideLoading();
                mAddAtletView.tampilkanAtlet(data);
            }
        }, idPsikolog);
    }

    @Override
    public void start() {

    }
}
