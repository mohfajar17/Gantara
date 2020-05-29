package com.mohfajar.gantara.Info;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.Info.InfoDataSource;
import com.mohfajar.gantara.Data.source.Info.InfoRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


public class HomeRepository implements InfoDataSource {

    private static HomeRepository INSTANCE;

    private InfoRemoteDataSource infoRemoteDataSource;

    public static HomeRepository getInstance(@NonNull InfoRemoteDataSource infoRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new HomeRepository(infoRemoteDataSource);
        }
        return INSTANCE;
    }

    private HomeRepository(@NonNull InfoRemoteDataSource infoRemoteDataSource){
        this.infoRemoteDataSource = checkNotNull(infoRemoteDataSource);
    }

    @Override
    public void getInfos(@NonNull LoadInfoCallBack callback) {
        infoRemoteDataSource.getInfos(callback);
    }

//    @Override
//    public void getInfo(@NonNull String infoId, @NonNull GetInfoCallback callback) {
//
//    }
}