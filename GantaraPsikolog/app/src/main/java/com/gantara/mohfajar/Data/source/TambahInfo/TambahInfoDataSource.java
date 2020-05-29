package com.gantara.mohfajar.Data.source.TambahInfo;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;

public interface TambahInfoDataSource {
    interface InsertTambahInfoCallback extends BaseDataSourceCallback<Object>{

    }

    void setTambahInfo(@NonNull InsertTambahInfoCallback callback, String idPsikolog, String judul, String dari, String untuk, String isiInfo);
}
