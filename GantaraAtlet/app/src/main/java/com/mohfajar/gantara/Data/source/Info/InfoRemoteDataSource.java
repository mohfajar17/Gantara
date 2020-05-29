package com.mohfajar.gantara.Data.source.Info;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.Data.Info;
import com.mohfajar.gantara.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoRemoteDataSource implements InfoDataSource {

    private static InfoRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public static InfoRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new InfoRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    private InfoRemoteDataSource(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    @Override
    public void getInfos(@NonNull final LoadInfoCallBack callback) {
        StringRequest request = new StringRequest(Request.Method.GET, Config.DATA_URL_GET_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt(Config.KEY_STATUS);
                            if(status==1){
                                JSONArray jsonArray = jsonObject.getJSONArray(Config.KEY_DATA);
                                List<Info> info = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    info.add(new Info(jsonArray.getJSONObject(i)));
                                }
                                callback.onRequestSuccess(info);
                            } else {
                                callback.onRequestFailed(RequestStatus.GENERIC_FAILURE,"Failed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onRequestFailed(RequestStatus.PARSING_FAILURE, "Parsing data failed");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onRequestFailed(RequestStatus.VOLLEY_ERROR_FAILURE, "Request failed, check your network");
                    }
                }){
        };
        requestQueue.add(request);
    }

//    @Override
//    public void getInfo(@NonNull String infoId, @NonNull GetInfoCallback callback) {
//
//    }
}