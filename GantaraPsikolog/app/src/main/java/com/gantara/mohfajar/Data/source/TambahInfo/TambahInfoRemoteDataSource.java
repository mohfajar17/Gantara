package com.gantara.mohfajar.Data.source.TambahInfo;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.RequestStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahInfoRemoteDataSource implements TambahInfoDataSource{

    private static TambahInfoRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public TambahInfoRemoteDataSource(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    public static TambahInfoRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE==null){
            INSTANCE = new TambahInfoRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    @Override
    public void setTambahInfo(@NonNull final InsertTambahInfoCallback callback, final String idPsikolog, final String judul, final String dari, final String untuk, final String isiInfo) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_INSERT_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt("status");
                            if (status == 1) {
                                callback.onRequestSuccess(null);
                            } else {
                                callback.onDataNotAvailable();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onRequestFailed(RequestStatus.PARSING_FAILURE, "Gagal mendapatkan data");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onRequestFailed(RequestStatus.VOLLEY_ERROR_FAILURE, "Permintaan gagal, cek internet anda");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<>();
                param.put("idPsikolog", idPsikolog);
                param.put("judul", judul);
                param.put("dari", dari);
                param.put("untuk", untuk);
                param.put("isiInfo", isiInfo);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
