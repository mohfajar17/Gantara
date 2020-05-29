package com.mohfajar.gantara.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.mohfajar.gantara.Config;

import org.json.JSONException;
import org.json.JSONObject;


public class Info implements Parcelable {

    private int id;
    private int idPsikolog;
    private String judul;
    private String dari;
    private String untuk;
    private String waktu;
    private String isiInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPsikolog() {
        return idPsikolog;
    }

    public void setIdPsikolog(int idPsikolog) {
        this.idPsikolog = idPsikolog;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIsiInfo() {
        return isiInfo;
    }

    public void setIsiInfo(String isiInfo) {
        this.isiInfo = isiInfo;
    }


    public Info(int id, int idPsikolog, String judul, String dari, String untuk, String waktu, String isiInfo){
        this.id=id;
        this.idPsikolog=idPsikolog;
        this.judul=judul;
        this.dari=dari;
        this.untuk=untuk;
        this.waktu=waktu;
        this.isiInfo=isiInfo;
    }

    protected Info(Parcel in) {
        id = in.readInt();
        idPsikolog = in.readInt();
        judul = in.readString();
        dari = in.readString();
        untuk = in.readString();
        waktu = in.readString();
        isiInfo = in.readString();
    }

    public Info(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt(Config.KEY_ID);
            this.idPsikolog = jsonObject.getInt(Config.KEY_ID_PSIKOLOG);
            this.dari = jsonObject.getString(Config.KEY_DARI);
            this.judul = jsonObject.getString(Config.KEY_JUDUL);
            this.untuk = jsonObject.getString(Config.KEY_UNTUK);
            this.waktu = jsonObject.getString(Config.KEY_WAKTU);
            this.isiInfo = jsonObject.getString(Config.KEY_ISI_INFO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idPsikolog);
        parcel.writeString(judul);
        parcel.writeString(dari);
        parcel.writeString(untuk);
        parcel.writeString(waktu);
        parcel.writeString(isiInfo);
    }
}