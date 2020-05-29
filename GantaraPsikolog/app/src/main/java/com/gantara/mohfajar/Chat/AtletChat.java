package com.gantara.mohfajar.Chat;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Moh_Fajar on 14/07/2017.
 */

public class AtletChat implements Parcelable {
    private String id_atlet;
    private String id_psikolog;
    private String nama;
    private String cabang_olahraga;
    private int jenis_kelamin;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String alamat;
    private String kota_asal;
    private String no_telefon;
    private String user_name;
    private String token;
    private String photo_profile;

    public AtletChat(Cursor cursor){
        this.id_atlet = cursor.getString(cursor.getColumnIndex(DbChat.ID_ATLET));
        this.id_psikolog = cursor.getString(cursor.getColumnIndex(DbChat.ID_PSIKOLOG));
        this.nama = cursor.getString(cursor.getColumnIndex(DbChat.NAMA));
        this.cabang_olahraga = cursor.getString(cursor.getColumnIndex(DbChat.CABANG_OLAHRAGA));
        this.jenis_kelamin = cursor.getInt(cursor.getColumnIndex(DbChat.JENIS_KELAMIN));
        this.tempat_lahir = cursor.getString(cursor.getColumnIndex(DbChat.TEMPAT_LAHIR));
        this.tanggal_lahir = cursor.getString(cursor.getColumnIndex(DbChat.TANGGAL_LAHIR));
        this.alamat = cursor.getString(cursor.getColumnIndex(DbChat.ALAMAT));
        this.kota_asal = cursor.getString(cursor.getColumnIndex(DbChat.KOTA_ASAL));
        this.no_telefon = cursor.getString(cursor.getColumnIndex(DbChat.NO_TELEFON));
        this.user_name = cursor.getString(cursor.getColumnIndex(DbChat.USER_NAME));
        this.token = cursor.getString(cursor.getColumnIndex(DbChat.TOKEN));
        this.photo_profile = cursor.getString(cursor.getColumnIndex(DbChat.PHOTO_PROFILE));
    }

    public AtletChat(){

    }

    public AtletChat(JSONObject jsonObject){
        try {
            this.id_atlet =jsonObject.getString(DbChat.ID_ATLET);
            this.id_psikolog = jsonObject.getString(DbChat.ID_PSIKOLOG);
            this.nama = jsonObject.getString(DbChat.NAMA);
            this.cabang_olahraga = jsonObject.getString(DbChat.CABANG_OLAHRAGA);
            this.jenis_kelamin = jsonObject.getInt(DbChat.JENIS_KELAMIN);
            this.tempat_lahir = jsonObject.getString(DbChat.TEMPAT_LAHIR);
            this.tanggal_lahir = jsonObject.getString(DbChat.TANGGAL_LAHIR);
            this.alamat = jsonObject.getString(DbChat.ALAMAT);
            this.kota_asal = jsonObject.getString(DbChat.KOTA_ASAL);
            this.no_telefon = jsonObject.getString(DbChat.NO_TELEFON);
            this.user_name = jsonObject.getString(DbChat.USER_NAME);
            this.token = jsonObject.getString(DbChat.TOKEN);
            this.photo_profile = jsonObject.getString(DbChat.PHOTO_PROFILE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected AtletChat(Parcel in) {
        id_atlet = in.readString();
        id_psikolog = in.readString();
        nama = in.readString();
        cabang_olahraga = in.readString();
        jenis_kelamin = in.readInt();
        tempat_lahir = in.readString();
        tanggal_lahir = in.readString();
        alamat = in.readString();
        kota_asal = in.readString();
        no_telefon = in.readString();
        user_name = in.readString();
        token = in.readString();
        photo_profile = in.readString();
    }

    public static final Creator<AtletChat> CREATOR = new Creator<AtletChat>() {
        @Override
        public AtletChat createFromParcel(Parcel in) {
            return new AtletChat(in);
        }

        @Override
        public AtletChat[] newArray(int size) {
            return new AtletChat[size];
        }
    };

    public String getId_atlet() {
        return id_atlet;
    }

    public void setId_atlet(String id_atlet) {
        this.id_atlet = id_atlet;
    }

    public String getId_psikolog() {
        return id_psikolog;
    }

    public void setId_psikolog(String id_psikolog) {
        this.id_psikolog = id_psikolog;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCabang_olahraga() {
        return cabang_olahraga;
    }

    public void setCabang_olahraga(String cabang_olahraga) {
        this.cabang_olahraga = cabang_olahraga;
    }

    public int getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(int jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota_asal() {
        return kota_asal;
    }

    public void setKota_asal(String kota_asal) {
        this.kota_asal = kota_asal;
    }

    public String getNo_telefon() {
        return no_telefon;
    }

    public void setNo_telefon(String no_telefon) {
        this.no_telefon = no_telefon;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_atlet);
        dest.writeString(id_psikolog);
        dest.writeString(nama);
        dest.writeString(cabang_olahraga);
        dest.writeInt(jenis_kelamin);
        dest.writeString(tempat_lahir);
        dest.writeString(tanggal_lahir);
        dest.writeString(alamat);
        dest.writeString(kota_asal);
        dest.writeString(no_telefon);
        dest.writeString(user_name);
        dest.writeString(token);
        dest.writeString(photo_profile);
    }
}