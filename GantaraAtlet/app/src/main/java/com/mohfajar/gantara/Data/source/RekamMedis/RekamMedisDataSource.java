package com.mohfajar.gantara.Data.source.RekamMedis;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;
import com.mohfajar.gantara.Data.Tanggapan;

import java.util.List;

public interface RekamMedisDataSource {
    interface LoadRekamMedisCallBack extends BaseDataSourceCallback<List<Tanggapan>> {

    }

    void getRekamMedis(@NonNull LoadRekamMedisCallBack callback, String idAtlet);
}
