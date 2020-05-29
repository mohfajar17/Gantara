package com.gantara.mohfajar.Data.source.AddAtlet;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.Data.BaseDataSourceCallback;

import java.util.List;

public interface AddAtletDataSource {
    interface AddAtletCallBack extends BaseDataSourceCallback<List<Atlet>> {

    }

    void getAddAtlet(@NonNull AddAtletCallBack callback, String idPsikolog);
}
