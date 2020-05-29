package com.mohfajar.gantara.Data.source.Form;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.RequestStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormRemoteDataSource implements FormDataSource{

    private static FormRemoteDataSource INSTANCE;
    private RequestQueue requestQueue;

    public FormRemoteDataSource(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    public static FormRemoteDataSource getInstance(RequestQueue requestQueue){
        if(INSTANCE == null){
            INSTANCE = new FormRemoteDataSource(requestQueue);
        }
        return INSTANCE;
    }

    @Override
    public void setForm(@NonNull final InsertFormCallBack callback, final String idAtlet, final String idPsikolog, final String sesiLatihan, final String antusiasmePreLatihan, final String antusiasmePosLatihan, final String fisik, final String catatanFisik, final String stres, final String konsentrasi, final String keyakinan, final String target, final String lelah, final String komunikasi, final String intensitas, final String hidrasi, final String tidur, final String nutrisi, final String recovery, final String recoveryLain, final String mentalSkill, final String mentalSkillLain, final String kendalaMentalSkill, final String saranMasalah, final String saranMasalahLain, final String halPositif, final String scoringMental, final String scoringFisik, final String intensitasTarget) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_INSERT_FORM,
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onRequestFailed(RequestStatus.PARSING_FAILURE, "Gagal menyimpan data");
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
                param.put("idAtlet", idAtlet);
                param.put("idPsikolog", idPsikolog);
                param.put("sesiLatihan", sesiLatihan);
                param.put("antusiasmePreLatihan", antusiasmePreLatihan);
                param.put("antusiasmePosLatihan", antusiasmePosLatihan);
                param.put("fisik", fisik);
                param.put("catatanFisik", catatanFisik);
                param.put("stres", stres);
                param.put("konsentrasi", konsentrasi);
                param.put("keyakinan", keyakinan);
                param.put("target", target);
                param.put("lelah", lelah);
                param.put("komunikasi", komunikasi);
                param.put("intensitas", intensitas);
                param.put("hidrasi", hidrasi);
                param.put("tidur", tidur);
                param.put("nutrisi", nutrisi);
                param.put("recovery", recovery);
                param.put("recoveryLain", recoveryLain);
                param.put("mentalSkill", mentalSkill);
                param.put("mentalSkillLain", mentalSkillLain);
                param.put("kendalaMentalSkill", kendalaMentalSkill);
                param.put("saranMasalah", saranMasalah);
                param.put("saranMasalahLain", saranMasalahLain);
                param.put("halPositif", halPositif);
                param.put("scoringMental", scoringMental);
                param.put("scoringFisik", scoringFisik);
                param.put("intensitasTarget", intensitasTarget);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
