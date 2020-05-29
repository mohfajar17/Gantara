package com.gantara.mohfajar;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void onDataNotAvailable();
}
