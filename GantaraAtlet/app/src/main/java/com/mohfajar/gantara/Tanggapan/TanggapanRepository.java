package com.mohfajar.gantara.Tanggapan;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.Tanggapan.TanggapanDataSource;
import com.mohfajar.gantara.Data.source.Tanggapan.TanggapanRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class TanggapanRepository implements TanggapanDataSource {

    private static TanggapanRepository INSTANCE;

    private TanggapanRemoteDataSource tanggapanRemoteDataSource;

    public TanggapanRepository(TanggapanRemoteDataSource tanggapanRemoteDataSource) {
        this.tanggapanRemoteDataSource = checkNotNull(tanggapanRemoteDataSource);
    }

    public static TanggapanRepository getInstance(@NonNull TanggapanRemoteDataSource tanggapanRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new TanggapanRepository(tanggapanRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTanggapans(@NonNull LoadTanggapanCallBack callback, String idAtlet) {
        tanggapanRemoteDataSource.getTanggapans(callback, idAtlet);
    }
}