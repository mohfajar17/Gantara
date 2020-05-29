package com.mohfajar.gantara.Data;

import android.support.annotation.Nullable;

import com.mohfajar.gantara.RequestStatus;

public interface BaseDataSourceCallback<T> {
    void onRequestFailed(RequestStatus code, String message);

    void onDataNotAvailable();

    void onRequestSuccess(@Nullable T data);

}
