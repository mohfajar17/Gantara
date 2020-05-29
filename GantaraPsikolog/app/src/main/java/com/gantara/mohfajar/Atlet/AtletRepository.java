package com.gantara.mohfajar.Atlet;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.source.Atlet.AtletDataSource;
import com.gantara.mohfajar.Data.source.Atlet.AtletRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class AtletRepository implements AtletDataSource {

    private static AtletRepository INSTANCE;

    private AtletRemoteDataSource atletRemoteDataSource;

    public AtletRepository(AtletRemoteDataSource atletRemoteDataSource) {
        this.atletRemoteDataSource = checkNotNull(atletRemoteDataSource);
    }

    public static AtletRepository getInstance(@NonNull AtletRemoteDataSource atletRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new AtletRepository(atletRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getAtlet(@NonNull AtletCallBack callback, String idPsikolog) {
        atletRemoteDataSource.getAtlet(callback, idPsikolog);
    }
}