package com.gantara.mohfajar.Info;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.Info;
import com.gantara.mohfajar.Data.source.Info.InfoDataSource;
import com.gantara.mohfajar.RequestStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomePresenter implements HomeContract.Presenter{

    private HomeContract.View mHomeView;

    private HomeRepository mHomeRepository;

    public HomePresenter(@NonNull HomeRepository homeRepository, @NonNull HomeContract.View homeView) {
        this.mHomeView = checkNotNull(homeView);
        this.mHomeRepository = checkNotNull(homeRepository);

        homeView.setPresenter(this);
    }

    @Override
    public void start() {
        loadInfos();
    }

    private void loadInfos(){
        mHomeView.showLoading();
        mHomeRepository.getInfos(new InfoDataSource.LoadInfoCallBack() {

            @Override
            public void onRequestSuccess(List<Info> data) {
                mHomeView.hideLoading();
                mHomeView.tampilkanInfo(data);
            }

            @Override
            public void onRequestFailed(RequestStatus code, String message) {
                mHomeView.hideLoading();
                switch (code){
                    case GENERIC_FAILURE:
                    case PARSING_FAILURE:
                    case VOLLEY_ERROR_FAILURE:
                        default:
                            mHomeView.showMessage(message);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mHomeView.hideLoading();
                mHomeView.onDataNotAvailable();
            }
        });
    }
}
