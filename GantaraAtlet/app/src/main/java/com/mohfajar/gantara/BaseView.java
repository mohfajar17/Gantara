package com.mohfajar.gantara;

import android.content.Intent;
import android.os.Bundle;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void onDataNotAvailable();
}
