package com.mohfajar.gantara.Data.source.Statistik;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;
import com.mohfajar.gantara.Data.Statistik;

import java.util.List;

public interface StatistikDataSource {
    interface LoadStatistikCallBack extends BaseDataSourceCallback<List<Statistik>> {

    }

    void getStatistiks(@NonNull LoadStatistikCallBack callback, String idAtlet);
}
