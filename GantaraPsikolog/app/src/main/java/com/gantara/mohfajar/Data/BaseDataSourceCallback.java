package com.gantara.mohfajar.Data;

import android.support.annotation.Nullable;

import com.gantara.mohfajar.RequestStatus;

public interface BaseDataSourceCallback<T> {
    void onRequestFailed(RequestStatus code, String message);

    void onDataNotAvailable();

    void onRequestSuccess(@Nullable T data);

}
