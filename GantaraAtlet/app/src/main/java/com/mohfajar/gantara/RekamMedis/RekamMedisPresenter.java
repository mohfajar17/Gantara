package com.mohfajar.gantara.RekamMedis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mohfajar.gantara.Data.Tanggapan;
import com.mohfajar.gantara.Data.source.RekamMedis.RekamMedisDataSource;
import com.mohfajar.gantara.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class RekamMedisPresenter implements RekamMedisContract.Presenter{

    private RekamMedisContract.View mRekamMedisView;

    private RekamMedisRepository mRekamMedisRepository;
    public RekamMedisPresenter(@NonNull RekamMedisRepository rekamMedisRepository, @NonNull RekamMedisContract.View RekamMedisView){
        this.mRekamMedisRepository = checkNotNull(rekamMedisRepository);
        this.mRekamMedisView = checkNotNull(RekamMedisView);

        mRekamMedisView.setPresenter(this);
    }

    @Override
    public void rekamMedis(String idAtlet) {
        mRekamMedisView.showLoading();
        mRekamMedisRepository.getRekamMedis(new RekamMedisDataSource.LoadRekamMedisCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mRekamMedisView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mRekamMedisView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mRekamMedisView.hideLoading();
                mRekamMedisView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable List<Tanggapan> data) {
                mRekamMedisView.hideLoading();
                mRekamMedisView.tampilkanRekamMedis(data);
            }
        }, idAtlet);
    }

    @Override
    public void start() {

    }
}
