package com.gantara.mohfajar.Data.source.Signup;

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

public class SignupRemoteDataSource implements SignupDataSource{

    private static SignupRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public SignupRemoteDataSource(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public static SignupRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new SignupRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    @Override
    public void setSignup(@NonNull final InsertSignupCallBack callback, final String userName, final String password, final String nama, final String jenisKelamin, final String tempatLahir, final String tanggalLahir, final String alamat, final String noTelfon, final String photo) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_INSERT_SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status=jsonObject.getInt("status");
                            if(status==1){
                                callback.onRequestSuccess(null);
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
                param.put("nama", nama);
                param.put("gander", jenisKelamin);
                param.put("tempatLahir", tempatLahir);
                param.put("tanggalLahir", tanggalLahir);
                param.put("alamat", alamat);
                param.put("noTelfon", noTelfon);
                param.put("userName", userName);
                param.put("pass", password);
                param.put("photo", photo);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
