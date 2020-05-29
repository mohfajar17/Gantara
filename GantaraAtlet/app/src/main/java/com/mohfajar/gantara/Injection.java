package com.mohfajar.gantara;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mohfajar.gantara.Data.Statistik;
import com.mohfajar.gantara.Data.source.Form.FormRemoteDataSource;
import com.mohfajar.gantara.Data.source.Info.InfoRemoteDataSource;
import com.mohfajar.gantara.Data.source.RekamMedis.RekamMedisRemoteDataSource;
import com.mohfajar.gantara.Data.source.Signup.SignupRemoteDataSource;
import com.mohfajar.gantara.Data.source.Statistik.StatistikRemoteDataSource;
import com.mohfajar.gantara.Data.source.Tanggapan.TanggapanRemoteDataSource;
import com.mohfajar.gantara.Form.FormRepository;
import com.mohfajar.gantara.Info.HomeRepository;
import com.mohfajar.gantara.RekamMedis.RekamMedisRepository;
import com.mohfajar.gantara.Signup.SignupRepository;
import com.mohfajar.gantara.Statistik.StatistikRepository;
import com.mohfajar.gantara.Tanggapan.TanggapanRepository;

public class Injection {
    public static HomeRepository provideHomeRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return HomeRepository.getInstance(InfoRemoteDataSource.getInstance(requestQueue));
    }
    public static SignupRepository provideSignupRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return SignupRepository.getInstance(SignupRemoteDataSource.getInstance(requestQueue));
    }
    public static FormRepository provideFormRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return FormRepository.getInstance(FormRemoteDataSource.getInstance(requestQueue));
    }
    public static TanggapanRepository provideTanggapanRepository(@NonNull Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        return TanggapanRepository.getInstance(TanggapanRemoteDataSource.getInstance(requestQueue));
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
