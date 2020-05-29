package com.gantara.mohfajar.Data.source.RekamMedis;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.RekamMedis;
import com.gantara.mohfajar.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekamMedisRemoteDataSource implements RekamMedisDataSource {

    public static RekamMedisRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public static RekamMedisRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new RekamMedisRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    public RekamMedisRemoteDataSource(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void getRekamMedis(@NonNull final LoadRekamMedisCallBack callback, final String idAtlet) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_GET_REKAM_MEDIS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt("status");
                            if(status==1){
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_DATA);
                                List<RekamMedis> rekamMedis = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    rekamMedis.add(new RekamMedis(jsonArray.getJSONObject(i)));
                                }
                                callback.onRequestSuccess(rekamMedis);
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