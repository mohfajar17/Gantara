package com.gantara.mohfajar.Data.source.RekamMedis;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;
import com.gantara.mohfajar.Data.RekamMedis;

import java.util.List;

public interface RekamMedisDataSource {
    interface LoadRekamMedisCallBack extends BaseDataSourceCallback<List<RekamMedis>> {

    }

    void getRekamMedis(@NonNull LoadRekamMedisCallBack callback, String idAtlet);
}
