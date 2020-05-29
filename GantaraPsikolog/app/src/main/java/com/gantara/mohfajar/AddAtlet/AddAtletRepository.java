package com.gantara.mohfajar.AddAtlet;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.source.AddAtlet.AddAtletDataSource;
import com.gantara.mohfajar.Data.source.AddAtlet.AddAtletRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddAtletRepository implements AddAtletDataSource {

    private static AddAtletRepository INSTANCE;

    private AddAtletRemoteDataSource atletRemoteDataSource;

    public AddAtletRepository(AddAtletRemoteDataSource atletRemoteDataSource) {
        this.atletRemoteDataSource = checkNotNull(atletRemoteDataSource);
    }

    public static AddAtletRepository getInstance(@NonNull AddAtletRemoteDataSource addAtletRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new AddAtletRepository(addAtletRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getAddAtlet(@NonNull AddAtletCallBack callback, String idPsikolog) {

    }
}
