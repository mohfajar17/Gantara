package com.gantara.mohfajar;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gantara.mohfajar.AddAtlet.AddAtletRepository;
import com.gantara.mohfajar.Atlet.AtletRepository;
import com.gantara.mohfajar.Data.source.AddAtlet.AddAtletRemoteDataSource;
import com.gantara.mohfajar.Data.source.Atlet.AtletRemoteDataSource;
import com.gantara.mohfajar.Data.source.Form.FormRemoteDataSource;
import com.gantara.mohfajar.Data.source.Info.InfoRemoteDataSource;
import com.gantara.mohfajar.Data.source.RekamMedis.RekamMedisRemoteDataSource;
import com.gantara.mohfajar.Data.source.Signup.SignupRemoteDataSource;
import com.gantara.mohfajar.Data.source.Statistik.StatistikRemoteDataSource;
import com.gantara.mohfajar.Data.source.TambahInfo.TambahInfoRemoteDataSource;
import com.gantara.mohfajar.DetailAtlet.RekamMedis.RekamMedisRepository;
import com.gantara.mohfajar.DetailAtlet.Statistik.StatistikRepository;
import com.gantara.mohfajar.Form.FormRepository;
import com.gantara.mohfajar.Info.HomeRepository;
import com.gantara.mohfajar.Signup.SignupRepository;
import com.gantara.mohfajar.TambahInfo.TambahInfoRepository;

public class Injection {
    public static HomeRepository provideHomeRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return HomeRepository.getInstance(InfoRemoteDataSource.getInstance(requestQueue));
    }
    public static SignupRepository provideSignupRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return SignupRepository.getInstance(SignupRemoteDataSource.getInstance(requestQueue));
    }
    public static TambahInfoRepository provideTambahInfoRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return TambahInfoRepository.getInstance(TambahInfoRemoteDataSource.getInstance(requestQueue));
    }
    public static FormRepository provideFormRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return FormRepository.getInstance(FormRemoteDataSource.getInstance(requestQueue));
    }
    public static AtletRepository provideAtletRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return AtletRepository.getInstance(AtletRemoteDataSource.getInstance(requestQueue));
    }
    public static AddAtletRepository provideAddAtletRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return AddAtletRepository.getInstance(AddAtletRemoteDataSource.getInstance(requestQueue));
    }
    public static RekamMedisRepository provideRekamMedisRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return RekamMedisRepository.getInstance(RekamMedisRemoteDataSource.getInstance(requestQueue));
    }
    public static StatistikRepository provideStatistikRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return StatistikRepository.getInstance(StatistikRemoteDataSource.getInstance(requestQueue));
    }
}
