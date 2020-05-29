package com.mohfajar.gantara.Data.source.Tanggapan;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mohfajar.gantara.API;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.Data.Tanggapan;
import com.mohfajar.gantara.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TanggapanRemoteDataSource implements TanggapanDataSource {

    public static TanggapanRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public static TanggapanRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new TanggapanRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    public TanggapanRemoteDataSource(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void getTanggapans(@NonNull final LoadTanggapanCallBack callback, final String idAtlet) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_GET_TANGGAPAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt("status");
                            if(status==1){
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_DATA);
                                List<Tanggapan> tanggapan = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    tanggapan.add(new Tanggapan(jsonArray.getJSONObject(i)));
                                }
                                callback.onRequestSuccess(tanggapan);
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
