package com.gantara.mohfajar.Data.source.Atlet;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.Data.BaseDataSourceCallback;

import java.util.List;

public interface AtletDataSource {
    interface AtletCallBack extends BaseDataSourceCallback<List<Atlet>> {

    }

    void getAtlet(@NonNull AtletCallBack callback, String idPsikolog);
}
