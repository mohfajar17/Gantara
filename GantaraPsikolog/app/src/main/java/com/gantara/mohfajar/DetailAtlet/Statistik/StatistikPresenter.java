package com.gantara.mohfajar.DetailAtlet.Statistik;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gantara.mohfajar.Data.Statistik;
import com.gantara.mohfajar.Data.source.Statistik.StatistikDataSource;
import com.gantara.mohfajar.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class StatistikPresenter implements StatistikContract.Presenter {

    private StatistikContract.View mStatistikView;
    private StatistikRepository mSatistikRepository;

    public StatistikPresenter(@NonNull StatistikRepository statistikRepository, @NonNull StatistikContract.View statistikView){
        this.mSatistikRepository = checkNotNull(statistikRepository);
        this.mStatistikView = checkNotNull(statistikView);

        statistikView.setPresenter(this);
    }

    @Override
    public void statistik(String idAtlet) {
        mStatistikView.showLoading();
        mSatistikRepository.getStatistiks(new StatistikDataSource.LoadStatistikCallBack() {
            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mStatistikView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                    default:
                        mStatistikView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mStatistikView.hideLoading();
                mStatistikView.onDataNotAvailable();
            }

            @Override
            public void onRequestSuccess(@Nullable List<Statistik> data) {
                mStatistikView.hideLoading();
                mStatistikView.tampilkanStatistik(data);
            }
        }, idAtlet);
    }

    @Override
    public void start() {

    }
}
