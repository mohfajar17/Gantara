package com.mohfajar.gantara.Tanggapan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mohfajar.gantara.Data.Tanggapan;
import com.mohfajar.gantara.Data.source.Tanggapan.TanggapanDataSource;
import com.mohfajar.gantara.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TanggapanPresenter implements TanggapanContract.Presenter {

    private TanggapanContract.View mTanggapanView;

    private TanggapanRepository mTanggapanRepository;

    public TanggapanPresenter(@NonNull TanggapanRepository tanggapanRepository, @NonNull TanggapanContract.View tanggapanView){
        this.mTanggapanRepository = checkNotNull(tanggapanRepository);
        this.mTanggapanView = checkNotNull(tanggapanView);

        tanggapanView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void tanggapan(String idAtlet) {
        mTanggapanView.showLoading();
        mTanggapanRepository.getTanggapans(new TanggapanDataSource.LoadTanggapanCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mTanggapanView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mTanggapanView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mTanggapanView.hideLoading();
                mTanggapanView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable List<Tanggapan> data) {
                mTanggapanView.hideLoading();
                mTanggapanView.tampilkanTanggapan(data);
            }
        }, idAtlet);
    }
}
