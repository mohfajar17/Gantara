package com.gantara.mohfajar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.gantara.mohfajar.API;

import org.json.JSONException;
import org.json.JSONObject;

public class Atlet implements Parcelable {

    private String idAtlet;
    private String nama;
    private String cabangOlahraga;
    private String jenisKelamin;
    private String tempatLahir;
    private String tanggalLahir;
    private String alamat;
    private String kotaAsal;
    private String noTelfon;
    private String userName;
    private String intensitasTarget;

    public Atlet(String idAtlet, String nama, String cabangOlahraga, String jenisKelamin, String tempatLahir, String tanggalLahir,
                 String alamat, String kotaAsal, String noTelfon, String userName, String intensitasTarget){
        this.idAtlet = idAtlet;
        this.nama = nama;
        this.cabangOlahraga = cabangOlahraga;
        this.jenisKelamin = jenisKelamin;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.kotaAsal = kotaAsal;
        this.noTelfon = noTelfon;
        this.userName = userName;
        this.intensitasTarget = intensitasTarget;
    }

    protected Atlet(Parcel in) {
        idAtlet = in.readString();
        nama = in.readString();
        cabangOlahraga = in.readString();
        jenisKelamin = in.readString();
        tempatLahir = in.readString();
        tanggalLahir = in.readString();
        alamat = in.readString();
        kotaAsal = in.readString();
        noTelfon = in.readString();
        userName = in.readString();
        intensitasTarget = in.readString();
    }

    public Atlet(JSONObject jsonObject){
        try{
            this.idAtlet = jsonObject.getString(API.PARAM_ID_ATLET);
            this.nama = jsonObject.getString(API.PARAM_NAMA);
            this.cabangOlahraga = jsonObject.getString(API.PARAM_CABANG_OLAHRAGA);
            this.jenisKelamin = jsonObject.getString(API.PARAM_JENIS_KELAMIN);
            this.tempatLahir = jsonObject.getString(API.PARAM_TEMPAT_LAHIR);
            this.tanggalLahir = jsonObject.getString(API.PARAM_TANGGAL_LAHIR);
            this.alamat = jsonObject.getString(API.PARAM_ALAMAT);
            this.kotaAsal = jsonObject.getString(API.PARAM_KOTA_ASAL);
            this.noTelfon = jsonObject.getString(API.PARAM_NO_TELP);
            this.userName = jsonObject.getString(API.PARAM_USER_NAME);
            this.intensitasTarget = jsonObject.getString(API.PARAM_INTENSITAS_TARGET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<Atlet> CREATOR = new Creator<Atlet>() {
        @Override
        public Atlet createFromParcel(Parcel in) {
            return new Atlet(in);
        }

        @Override
        public Atlet[] newArray(int size) {
            return new Atlet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idAtlet);
        parcel.writeString(nama);
        parcel.writeString(cabangOlahraga);
        parcel.writeString(jenisKelamin);
        parcel.writeString(tempatLahir);
        parcel.writeString(tanggalLahir);
        parcel.writeString(alamat);
        parcel.writeString(kotaAsal);
        parcel.writeString(noTelfon);
        parcel.writeString(userName);
        parcel.writeString(intensitasTarget);
    }

    public String getIdAtlet() {
        return idAtlet;
    }

    public void setIdAtlet(String idAtlet) {
        this.idAtlet = idAtlet;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCabangOlahraga() {
        return cabangOlahraga;
    }

    public void setCabangOlahraga(String cabangOlahraga) {
        this.cabangOlahraga = cabangOlahraga;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getNoTelfon() {
        return noTelfon;
    }

    public void setNoTelfon(String noTelfon) {
        this.noTelfon = noTelfon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIntensitasTarget() {
        return intensitasTarget;
    }

    public void setIntensitasTarget(String intensitasTarget) {
        this.intensitasTarget = intensitasTarget;
    }
}
