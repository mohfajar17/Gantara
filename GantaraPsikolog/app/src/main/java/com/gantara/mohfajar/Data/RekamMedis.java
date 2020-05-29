package com.gantara.mohfajar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.gantara.mohfajar.API;

import org.json.JSONException;
import org.json.JSONObject;

public class RekamMedis implements Parcelable {
    private int idForm;
    private String idAtlet;
    private String idPsikolog;
    private String waktuInput;
    private int sesiLatihan;
    private String catatanFisik;
    private String kendalaMentalSkill;
    private String halPositif;
    private String isiRm;

    public RekamMedis(int idForm, String idAtlet, String idPsikolog, String waktuInput, int sesiLatihan, String catatanFisik, String kendalaMentalSkill, String halPositif, String isiRm){
        this.idForm = idForm;
        this.idAtlet = idAtlet;
        this.idPsikolog = idPsikolog;
        this.waktuInput = waktuInput;
        this.sesiLatihan = sesiLatihan;
        this.catatanFisik = catatanFisik;
        this.kendalaMentalSkill = kendalaMentalSkill;
        this.halPositif = halPositif;
        this.isiRm = isiRm;
    }

    protected RekamMedis(Parcel in) {
        idForm = in.readInt();
        idAtlet = in.readString();
        idPsikolog = in.readString();
        waktuInput = in.readString();
        sesiLatihan = in.readInt();
        catatanFisik = in.readString();
        kendalaMentalSkill = in.readString();
        halPositif = in.readString();
        isiRm = in.readString();
    }

    public RekamMedis(JSONObject jsonObject){
        try {
            this.idForm = jsonObject.getInt(API.PARAM_ID_FORM);
            this.idAtlet = jsonObject.getString(API.PARAM_ID_ATLET);
            this.idPsikolog = jsonObject.getString(API.PARAM_ID_PSIKOLOG);
            this.waktuInput = jsonObject.getString(API.PARAM_WAKTU_INPUT);
            this.sesiLatihan = jsonObject.getInt(API.PARAM_SESI_LATIHAN);
            this.catatanFisik = jsonObject.getString(API.PARAM_CATATAN_FISIK);
            this.kendalaMentalSkill = jsonObject.getString(API.PARAM_KENDALA_MENTAL_SKILL);
            this.halPositif = jsonObject.getString(API.PARAM_HAL_POSITIF);
            this.isiRm = jsonObject.getString(API.PARAM_ISI_RM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<RekamMedis> CREATOR = new Creator<RekamMedis>() {
        @Override
        public RekamMedis createFromParcel(Parcel in) {
            return new RekamMedis(in);
        }

        @Override
        public RekamMedis[] newArray(int size) {
            return new RekamMedis[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idForm);
        parcel.writeString(idAtlet);
        parcel.writeString(idPsikolog);
        parcel.writeString(waktuInput);
        parcel.writeInt(sesiLatihan);
        parcel.writeString(catatanFisik);
        parcel.writeString(kendalaMentalSkill);
        parcel.writeString(halPositif);
        parcel.writeString(isiRm);
    }

    public int getIdForm() {
        return idForm;
    }

    public String getIdAtlet() {
        return idAtlet;
    }

    public String getIdPsikolog() {
        return idPsikolog;
    }

    public String getWaktuInput() {
        return waktuInput;
    }

    public int getSesiLatihan() {
        return sesiLatihan;
    }

    public String getCatatanFisik() {
        return catatanFisik;
    }

    public String getKendalaMentalSkill() {
        return kendalaMentalSkill;
    }

    public String getHalPositif() {
        return halPositif;
    }

    public String getIsiRm() {
        return isiRm;
    }
}
