package com.mohfajar.gantara.Data.source.Statistik;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.Data.Statistik;
import com.mohfajar.gantara.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatistikRemoteDataSource implements StatistikDataSource {

    public static StatistikRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public static StatistikRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new StatistikRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    public StatistikRemoteDataSource(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void getStatistiks(@NonNull final LoadStatistikCallBack callback, final String idAtlet) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_GET_STATISTIK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt("status");
                            if(status==1){
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_DATA);
                                List<Statistik> statistik = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    statistik.add(new Statistik(jsonArray.getJSONObject(i)));
                                }
                                callback.onRequestSuccess(statistik);
                            } else {
                                callback.onDataNotAvailable();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            callback.onRequestFailed(RequestStatus.PARSING_FAILURE, "Gagal mendapatkan data");
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onRequestFailed(RequestStatus.VOLLEY_ERROR_FAILURE, "Permintaan gagal, cek internet anda");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<>();
                param.put("idAtlet", idAtlet);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
