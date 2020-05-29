package com.gantara.mohfajar.Data.source.Statistik;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;
import com.gantara.mohfajar.Data.Statistik;

import java.util.List;

public interface StatistikDataSource {
    interface LoadStatistikCallBack extends BaseDataSourceCallback<List<Statistik>> {

    }

    void getStatistiks(@NonNull LoadStatistikCallBack callback, String idAtlet);
}
