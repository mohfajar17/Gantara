package com.mohfajar.gantara.Statistik;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.source.Statistik.StatistikDataSource;
import com.mohfajar.gantara.Data.source.Statistik.StatistikRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class StatistikRepository implements StatistikDataSource {

    private static StatistikRepository INSTANCE;

    private StatistikRemoteDataSource statistikRemoteDataSource;

    public StatistikRepository(StatistikRemoteDataSource statistikRemoteDataSource) {
        this.statistikRemoteDataSource = checkNotNull(statistikRemoteDataSource);
    }

    public static StatistikRepository getInstance(@NonNull StatistikRemoteDataSource statistikRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new StatistikRepository(statistikRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getStatistiks(@NonNull LoadStatistikCallBack callback, String idAtlet) {
        statistikRemoteDataSource.getStatistiks(callback, idAtlet);
    }
}