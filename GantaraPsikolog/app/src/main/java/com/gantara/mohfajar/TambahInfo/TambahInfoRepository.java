package com.gantara.mohfajar.TambahInfo;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.source.TambahInfo.TambahInfoDataSource;
import com.gantara.mohfajar.Data.source.TambahInfo.TambahInfoRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class TambahInfoRepository implements TambahInfoDataSource {

    private static TambahInfoRepository INSTANCE;
    private TambahInfoRemoteDataSource tambahInfoRemoteDataSource;

    public static TambahInfoRepository getInstance(@NonNull TambahInfoRemoteDataSource tambahInfoRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new TambahInfoRepository(tambahInfoRemoteDataSource);
        }
        return INSTANCE;
    }

    public TambahInfoRepository(TambahInfoRemoteDataSource tambahInfoRemoteDataSource) {
        this.tambahInfoRemoteDataSource = checkNotNull(tambahInfoRemoteDataSource);
    }

    @Override
    public void setTambahInfo(@NonNull InsertTambahInfoCallback callback, String idPsikolog, String judul, String dari, String untuk, String isiInfo) {
        tambahInfoRemoteDataSource.setTambahInfo(callback, idPsikolog, judul, dari, untuk, isiInfo);
    }
}
