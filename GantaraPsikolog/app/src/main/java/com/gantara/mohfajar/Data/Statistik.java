package com.gantara.mohfajar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.gantara.mohfajar.API;

import org.json.JSONException;
import org.json.JSONObject;

public class Statistik implements Parcelable {
    private int id_form;
    private String id_atlet;
    private String id_psikolog;
    private String waktu_input;
    private int sesi_latihan;
    private int antusiasme_pre_latih;
    private int antusiasme_pos_latih;
    private int fisik;
    private int stres;
    private int konsentrasi;
    private int keyakinan;
    private int target;
    private int lelah;
    private int komunikasi;
    private int intensitas;
    private int hidrasi;
    private int tidur;
    private int nutrisi;
    private int recovery;
    private int mental_skill;
    private double scoring_mental;
    private double scoring_fisik;
    private int intensitas_target;

    protected Statistik(Parcel in) {
        id_form = in.readInt();
        id_atlet = in.readString();
        id_psikolog = in.readString();
        waktu_input = in.readString();
        sesi_latihan = in.readInt();
        antusiasme_pre_latih = in.readInt();
        antusiasme_pos_latih = in.readInt();
        fisik = in.readInt();
        stres = in.readInt();
        konsentrasi = in.readInt();
        keyakinan = in.readInt();
        target = in.readInt();
        lelah = in.readInt();
        komunikasi = in.readInt();
        intensitas = in.readInt();
        hidrasi = in.readInt();
        tidur = in.readInt();
        nutrisi = in.readInt();
        recovery = in.readInt();
        mental_skill = in.readInt();
        scoring_mental = in.readDouble();
        scoring_fisik = in.readDouble();
        intensitas_target = in.readInt();
    }

    public Statistik(int id_form, String id_atlet, String id_psikolog, String waktu_input, int sesi_latihan,
                     int antusiasme_pre_latih, int antusiasme_pos_latih, int fisik, int stres,
                     int konsentrasi, int keyakinan, int target, int lelah, int komunikasi,
                     int intensitas, int hidrasi, int tidur, int nutrisi, int recovery,
                     int mental_skill, double scoring_mental, double scoring_fisik, int intensitas_target){
        this.id_form = id_form;
        this.id_atlet = id_atlet;
        this.id_psikolog = id_psikolog;
        this.waktu_input = waktu_input;
        this.sesi_latihan = sesi_latihan;
        this.antusiasme_pre_latih = antusiasme_pre_latih;
        this.antusiasme_pos_latih = antusiasme_pos_latih;
        this.fisik = fisik;
        this.stres = stres;
        this.konsentrasi = konsentrasi;
        this.keyakinan = keyakinan;
        this.target = target;
        this.lelah = lelah;
        this.komunikasi = komunikasi;
        this.intensitas = intensitas;
        this.hidrasi = hidrasi;
        this.tidur = tidur;
        this.nutrisi = nutrisi;
        this.recovery = recovery;
        this.mental_skill = mental_skill;
        this.scoring_mental = scoring_mental;
        this.scoring_fisik = scoring_fisik;
        this.intensitas_target = intensitas_target;
    }

    public Statistik(JSONObject jsonObject){
        try {
            this.id_form = jsonObject.getInt(API.PARAM_ID_FORM);
            this.id_atlet = jsonObject.getString(API.PARAM_ID_ATLET);
            this.id_psikolog = jsonObject.getString(API.PARAM_ID_PSIKOLOG);
            this.waktu_input = jsonObject.getString(API.PARAM_WAKTU_INPUT);
            this.sesi_latihan = jsonObject.getInt(API.PARAM_SESI_LATIHAN);
            this.antusiasme_pre_latih = jsonObject.getInt(API.PARAM_ANTUSIASME_PRE_LATIHAN);
            this.antusiasme_pos_latih = jsonObject.getInt(API.PARAM_ANTUSIASME_POS_LATIHAN);
            this.fisik = jsonObject.getInt(API.PARAM_FISIK);
            this.stres = jsonObject.getInt(API.PARAM_STRESS);
            this.konsentrasi = jsonObject.getInt(API.PARAM_KONSENTRASI);
            this.keyakinan = jsonObject.getInt(API.PARAM_KEYAKINAN);
            this.target = jsonObject.getInt(API.PARAM_TARGET);
            this.lelah = jsonObject.getInt(API.PARAM_LELAH);
            this.komunikasi = jsonObject.getInt(API.PARAM_KOMUNIKASI);
            this.intensitas = jsonObject.getInt(API.PARAM_INTENSITAS);
            this.hidrasi = jsonObject.getInt(API.PARAM_HIDRASI);
            this.tidur = jsonObject.getInt(API.PARAM_TIDUR);
            this.nutrisi = jsonObject.getInt(API.PARAM_NUTRISI);
            this.recovery = jsonObject.getInt(API.PARAM_RECOVERY);
            this.mental_skill = jsonObject.getInt(API.PARAM_MENTAL_SKILL);
            this.scoring_mental = jsonObject.getDouble(API.PARAM_SCORING_MENTAL);
            this.scoring_fisik = jsonObject.getDouble(API.PARAM_SCORING_FISIK);
            this.intensitas_target = jsonObject.getInt(API.PARAM_INTENSITAS_TARGET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_form);
        dest.writeString(id_atlet);
        dest.writeString(id_psikolog);
        dest.writeString(waktu_input);
        dest.writeInt(sesi_latihan);
        dest.writeInt(antusiasme_pre_latih);
        dest.writeInt(antusiasme_pos_latih);
        dest.writeInt(fisik);
        dest.writeInt(stres);
        dest.writeInt(konsentrasi);
        dest.writeInt(keyakinan);
        dest.writeInt(target);
        dest.writeInt(lelah);
        dest.writeInt(komunikasi);
        dest.writeInt(intensitas);
        dest.writeInt(hidrasi);
        dest.writeInt(tidur);
        dest.writeInt(nutrisi);
        dest.writeInt(recovery);
        dest.writeInt(mental_skill);
        dest.writeDouble(scoring_mental);
        dest.writeDouble(scoring_fisik);
        dest.writeInt(intensitas_target);
    }

    public static final Creator<Statistik> CREATOR = new Creator<Statistik>() {
        @Override
        public Statistik createFromParcel(Parcel in) {
            return new Statistik(in);
        }

        @Override
        public Statistik[] newArray(int size) {
            return new Statistik[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId_form() {
        return id_form;
    }

    public void setId_form(int id_form) {
        this.id_form = id_form;
    }

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

    public String getWaktu_input() {
        return waktu_input;
    }

    public void setWaktu_input(String waktu_input) {
        this.waktu_input = waktu_input;
    }

    public int getSesi_latihan() {
        return sesi_latihan;
    }

    public void setSesi_latihan(int sesi_latihan) {
        this.sesi_latihan = sesi_latihan;
    }

    public int getAntusiasme_pre_latih() {
        return antusiasme_pre_latih;
    }

    public void setAntusiasme_pre_latih(int antusiasme_pre_latih) {
        this.antusiasme_pre_latih = antusiasme_pre_latih;
    }

    public int getAntusiasme_pos_latih() {
        return antusiasme_pos_latih;
    }

    public void setAntusiasme_pos_latih(int antusiasme_pos_latih) {
        this.antusiasme_pos_latih = antusiasme_pos_latih;
    }

    public int getFisik() {
        return fisik;
    }

    public void setFisik(int fisik) {
        this.fisik = fisik;
    }

    public int getStres() {
        return stres;
    }

    public void setStres(int stres) {
        this.stres = stres;
    }

    public int getKonsentrasi() {
        return konsentrasi;
    }

    public void setKonsentrasi(int konsentrasi) {
        this.konsentrasi = konsentrasi;
    }

    public int getKeyakinan() {
        return keyakinan;
    }

    public void setKeyakinan(int keyakinan) {
        this.keyakinan = keyakinan;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getLelah() {
        return lelah;
    }

    public void setLelah(int lelah) {
        this.lelah = lelah;
    }

    public int getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(int komunikasi) {
        this.komunikasi = komunikasi;
    }

    public int getIntensitas() {
        return intensitas;
    }

    public void setIntensitas(int intensitas) {
        this.intensitas = intensitas;
    }

    public int getHidrasi() {
        return hidrasi;
    }

    public void setHidrasi(int hidrasi) {
        this.hidrasi = hidrasi;
    }

    public int getTidur() {
        return tidur;
    }

    public void setTidur(int tidur) {
        this.tidur = tidur;
    }

    public int getNutrisi() {
        return nutrisi;
    }

    public void setNutrisi(int nutrisi) {
        this.nutrisi = nutrisi;
    }

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    public int getMental_skill() {
        return mental_skill;
    }

    public void setMental_skill(int mental_skill) {
        this.mental_skill = mental_skill;
    }

    public double getScoring_mental() {
        return scoring_mental;
    }

    public void setScoring_mental(double scoring_mental) {
        this.scoring_mental = scoring_mental;
    }

    public double getScoring_fisik() {
        return scoring_fisik;
    }

    public void setScoring_fisik(double scoring_fisik) {
        this.scoring_fisik = scoring_fisik;
    }

    public int getIntensitas_target() {
        return intensitas_target;
    }

    public void setIntensitas_target(int intensitas_target) {
        this.intensitas_target = intensitas_target;
    }
}
