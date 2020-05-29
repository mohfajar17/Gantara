package com.gantara.mohfajar.Data.source.Atlet;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gantara.mohfajar.Config;
import com.gantara.mohfajar.Data.Atlet;
import com.gantara.mohfajar.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtletRemoteDataSource implements AtletDataSource {

    private static AtletRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public AtletRemoteDataSource(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    public static AtletRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new AtletRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    @Override
    public void getAtlet(@NonNull final AtletCallBack callback, final String idPsikolog) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_GET_ATLET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt("status");
                            if(status==1){
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_DATA);
                                List<Atlet> atlets = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    atlets.add(new Atlet(jsonArray.getJSONObject(i)));
                                }
                                callback.onRequestSuccess(atlets);
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
                param.put("idPsikolog", idPsikolog);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
