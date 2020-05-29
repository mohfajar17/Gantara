package com.gantara.mohfajar.TambahInfo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gantara.mohfajar.Data.source.TambahInfo.TambahInfoDataSource;
import com.gantara.mohfajar.RequestStatus;

import static com.google.common.base.Preconditions.checkNotNull;

public class TambahInfoPresenter implements TambahInfoContract.Presenter {

    private TambahInfoContract.View mTambahInfoView;

    private TambahInfoRepository mTambahInfoRepository;

    public TambahInfoPresenter(@NonNull TambahInfoRepository tambahInfoRepository, @NonNull TambahInfoContract.View tambahInfoView){
        this.mTambahInfoView = checkNotNull(tambahInfoView);
        this.mTambahInfoRepository = checkNotNull(tambahInfoRepository);

        tambahInfoView.setPresenter(this);
    }

    @Override
    public void tambahInfo(String idPsikolog, String judul, String dari, String untuk, String isiInfo) {
        mTambahInfoView.showLoading();
        mTambahInfoRepository.setTambahInfo(new TambahInfoDataSource.InsertTambahInfoCallback() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mTambahInfoView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mTambahInfoView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mTambahInfoView.hideLoading();
                mTambahInfoView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable Object data) {
                mTambahInfoView.launchHomeActivity();
            }
        }, idPsikolog, judul, dari, untuk, isiInfo);
    }

    @Override
    public void start() {

    }
}
