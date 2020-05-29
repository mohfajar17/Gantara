package com.mohfajar.gantara.Data.source.Tanggapan;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;
import com.mohfajar.gantara.Data.Tanggapan;

import java.util.List;

public interface TanggapanDataSource {

    interface LoadTanggapanCallBack extends BaseDataSourceCallback<List<Tanggapan>> {

    }

    void getTanggapans(@NonNull LoadTanggapanCallBack callback, String idAtlet);
}
