package com.mohfajar.gantara.RekamMedis;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.RekamMedis.RekamMedisDataSource;
import com.mohfajar.gantara.Data.source.RekamMedis.RekamMedisRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class RekamMedisRepository implements RekamMedisDataSource {

    private static RekamMedisRepository INSTANCE;

    private RekamMedisRemoteDataSource rekamMedisRemoteDataSource;

    public RekamMedisRepository(RekamMedisRemoteDataSource rekamMedisRemoteDataSource) {
        this.rekamMedisRemoteDataSource = checkNotNull(rekamMedisRemoteDataSource);
    }

    public static RekamMedisRepository getInstance(@NonNull RekamMedisRemoteDataSource rekamMedisRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new RekamMedisRepository(rekamMedisRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getRekamMedis(@NonNull LoadRekamMedisCallBack callback, String idAtlet) {
        rekamMedisRemoteDataSource.getRekamMedis(callback, idAtlet);
    }
}
