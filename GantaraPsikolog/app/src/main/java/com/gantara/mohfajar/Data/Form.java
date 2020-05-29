package com.gantara.mohfajar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.gantara.mohfajar.API;

import org.json.JSONException;
import org.json.JSONObject;

public class Form implements Parcelable {

    private int idForm;
    private String idAtlet;
    private String idPsikolog;
    private String waktuInput;
    private String sesiLatihan;
    private String antusiasmePreLatih;
    private String antusiasmePosLatih;
    private String fisik;
    private String catatanFisik;
    private String stres;
    private String konsentrasi;
    private String keyakinan;
    private String target;
    private String lelah;
    private String komunikasi;
    private String intensitas;
    private String hidrasi;
    private String tidur;
    private String nutrisi;
    private String recoveri;
    private String recoveriLain;
    private String mentalSkill;
    private String mentalSkillLain;
    private String kendalaMentalSkill;
    private String saranMasalah;
    private String saranMasalahLain;
    private String halPositif;
    private double scoringMental;
    private double scoringFisik;
    private String intensitasTarget;
    private String nama;
    private String cabangOlahraga;
    private String userName;


    public Form(int idForm, String idAtlet, String idPsikolog, String waktuInput, String sesiLatihan, String antusiasmePreLatih,
                String antusiasmePosLatih, String fisik, String catatanFisik, String stres, String konsentrasi, String keyakinan,
                String target, String lelah, String komunikasi, String intensitas, String hidrasi, String tidur, String nutrisi,
                String recoveri, String recoveriLain, String mentalSkill, String mentalSkillLain, String kendalaMentalSkill,
                String saranMasalah, String saranMasalahLain, String halPositif, double scoringMental, double scoringFisik,
                String intensitasTarget, String nama, String cabangOlahraga, String userName){
        this.idForm = idForm;
        this.idAtlet = idAtlet;
        this.idPsikolog = idPsikolog;
        this.waktuInput = waktuInput;
        this.sesiLatihan = sesiLatihan;
        this.antusiasmePreLatih = antusiasmePreLatih;
        this.antusiasmePosLatih = antusiasmePosLatih;
        this.fisik = fisik;
        this.catatanFisik = catatanFisik;
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
        this.recoveri = recoveri;
        this.recoveriLain = recoveriLain;
        this.mentalSkill = mentalSkill;
        this.mentalSkillLain = mentalSkillLain;
        this.kendalaMentalSkill = kendalaMentalSkill;
        this.saranMasalah = saranMasalah;
        this.saranMasalahLain = saranMasalahLain;
        this.halPositif = halPositif;
        this.scoringMental = scoringMental;
        this.scoringFisik = scoringFisik;
        this.intensitasTarget = intensitasTarget;
        this.nama = nama;
        this.cabangOlahraga = cabangOlahraga;
        this.userName = userName;
    }

    protected Form(Parcel in) {
        idForm = in.readInt();
        idAtlet = in.readString();
        idPsikolog = in.readString();
        waktuInput = in.readString();
        sesiLatihan = in.readString();
        antusiasmePreLatih = in.readString();
        antusiasmePosLatih = in.readString();
        fisik = in.readString();
        catatanFisik = in.readString();
        stres = in.readString();
        konsentrasi = in.readString();
        keyakinan = in.readString();
        target = in.readString();
        lelah = in.readString();
        komunikasi = in.readString();
        intensitas = in.readString();
        hidrasi = in.readString();
        tidur = in.readString();
        nutrisi = in.readString();
        recoveri = in.readString();
        recoveriLain = in.readString();
        mentalSkill = in.readString();
        mentalSkillLain = in.readString();
        kendalaMentalSkill = in.readString();
        saranMasalah = in.readString();
        saranMasalahLain = in.readString();
        halPositif = in.readString();
        scoringMental = in.readDouble();
        scoringFisik = in.readDouble();
        intensitasTarget = in.readString();
        nama = in.readString();
        cabangOlahraga = in.readString();
        userName = in.readString();
    }

    public Form(JSONObject jsonObject){
        try {
            this.idForm = jsonObject.getInt(API.PARAM_ID_FORM);
            this.idAtlet = jsonObject.getString(API.PARAM_ID_ATLET);
            this.idPsikolog = jsonObject.getString(API.PARAM_ID_PSIKOLOG);
            this.waktuInput = jsonObject.getString(API.PARAM_WAKTU_INPUT);
            this.sesiLatihan = jsonObject.getString(API.PARAM_SESI_LATIHAN);
            this.antusiasmePreLatih = jsonObject.getString(API.PARAM_ANTUSIASME_PRE_LATIHAN);
            this.antusiasmePosLatih = jsonObject.getString(API.PARAM_ANTUSIASME_POS_LATIHAN);
            this.fisik = jsonObject.getString(API.PARAM_FISIK);
            this.catatanFisik = jsonObject.getString(API.PARAM_CATATAN_FISIK);
            this.stres = jsonObject.getString(API.PARAM_STRESS);
            this.konsentrasi = jsonObject.getString(API.PARAM_KONSENTRASI);
            this.keyakinan = jsonObject.getString(API.PARAM_KEYAKINAN);
            this.target = jsonObject.getString(API.PARAM_TARGET);
            this.lelah = jsonObject.getString(API.PARAM_LELAH);
            this.komunikasi = jsonObject.getString(API.PARAM_KOMUNIKASI);
            this.intensitas = jsonObject.getString(API.PARAM_INTENSITAS);
            this.hidrasi = jsonObject.getString(API.PARAM_HIDRASI);
            this.tidur = jsonObject.getString(API.PARAM_TIDUR);
            this.nutrisi = jsonObject.getString(API.PARAM_NUTRISI);
            this.recoveri = jsonObject.getString(API.PARAM_RECOVERY);
            this.recoveriLain = jsonObject.getString(API.PARAM_RECOVERY_LAIN);
            this.mentalSkill = jsonObject.getString(API.PARAM_MENTAL_SKILL);
            this.mentalSkillLain = jsonObject.getString(API.PARAM_MENTAL_SKILL_LAIN);
            this.kendalaMentalSkill = jsonObject.getString(API.PARAM_KENDALA_MENTAL_SKILL);
            this.saranMasalah = jsonObject.getString(API.PARAM_SARAN_MASALAH);
            this.saranMasalahLain = jsonObject.getString(API.PARAM_SARAN_MASALAH_LAIN);
            this.halPositif = jsonObject.getString(API.PARAM_HAL_POSITIF);
            this.scoringMental = jsonObject.getDouble(API.PARAM_SCORING_MENTAL);
            this.scoringFisik = jsonObject.getDouble(API.PARAM_SCORING_FISIK);
            this.intensitasTarget = jsonObject.getString(API.PARAM_INTENSITAS_TARGET);
            this.nama = jsonObject.getString(API.PARAM_NAMA);
            this.cabangOlahraga = jsonObject.getString(API.PARAM_CABANG_OLAHRAGA);
            this.userName = jsonObject.getString(API.PARAM_USER_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<Form> CREATOR = new Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel in) {
            return new Form(in);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
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
        parcel.writeString(sesiLatihan);
        parcel.writeString(antusiasmePreLatih);
        parcel.writeString(antusiasmePosLatih);
        parcel.writeString(fisik);
        parcel.writeString(catatanFisik);
        parcel.writeString(stres);
        parcel.writeString(konsentrasi);
        parcel.writeString(keyakinan);
        parcel.writeString(target);
        parcel.writeString(lelah);
        parcel.writeString(komunikasi);
        parcel.writeString(intensitas);
        parcel.writeString(hidrasi);
        parcel.writeString(tidur);
        parcel.writeString(nutrisi);
        parcel.writeString(recoveri);
        parcel.writeString(recoveriLain);
        parcel.writeString(mentalSkill);
        parcel.writeString(mentalSkillLain);
        parcel.writeString(kendalaMentalSkill);
        parcel.writeString(saranMasalah);
        parcel.writeString(saranMasalahLain);
        parcel.writeString(halPositif);
        parcel.writeDouble(scoringMental);
        parcel.writeDouble(scoringFisik);
        parcel.writeString(intensitasTarget);
        parcel.writeString(nama);
        parcel.writeString(cabangOlahraga);
        parcel.writeString(userName);
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public String getIdAtlet() {
        return idAtlet;
    }

    public void setIdAtlet(String idAtlet) {
        this.idAtlet = idAtlet;
    }

    public String getIdPsikolog() {
        return idPsikolog;
    }

    public void setIdPsikolog(String idPsikolog) {
        this.idPsikolog = idPsikolog;
    }

    public String getWaktuInput() {
        return waktuInput;
    }

    public void setWaktuInput(String waktuInput) {
        this.waktuInput = waktuInput;
    }

    public String getSesiLatihan() {
        return sesiLatihan;
    }

    public void setSesiLatihan(String sesiLatihan) {
        this.sesiLatihan = sesiLatihan;
    }

    public String getAntusiasmePreLatih() {
        return antusiasmePreLatih;
    }

    public void setAntusiasmePreLatih(String antusiasmePreLatih) {
        this.antusiasmePreLatih = antusiasmePreLatih;
    }

    public String getAntusiasmePosLatih() {
        return antusiasmePosLatih;
    }

    public void setAntusiasmePosLatih(String antusiasmePosLatih) {
        this.antusiasmePosLatih = antusiasmePosLatih;
    }

    public String getFisik() {
        return fisik;
    }

    public void setFisik(String fisik) {
        this.fisik = fisik;
    }

    public String getCatatanFisik() {
        return catatanFisik;
    }

    public void setCatatanFisik(String catatanFisik) {
        this.catatanFisik = catatanFisik;
    }

    public String getStres() {
        return stres;
    }

    public void setStres(String stres) {
        this.stres = stres;
    }

    public String getKonsentrasi() {
        return konsentrasi;
    }

    public void setKonsentrasi(String konsentrasi) {
        this.konsentrasi = konsentrasi;
    }

    public String getKeyakinan() {
        return keyakinan;
    }

    public void setKeyakinan(String keyakinan) {
        this.keyakinan = keyakinan;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLelah() {
        return lelah;
    }

    public void setLelah(String lelah) {
        this.lelah = lelah;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public String getIntensitas() {
        return intensitas;
    }

    public void setIntensitas(String intensitas) {
        this.intensitas = intensitas;
    }

    public String getHidrasi() {
        return hidrasi;
    }

    public void setHidrasi(String hidrasi) {
        this.hidrasi = hidrasi;
    }

    public String getTidur() {
        return tidur;
    }

    public void setTidur(String tidur) {
        this.tidur = tidur;
    }

    public String getNutrisi() {
        return nutrisi;
    }

    public void setNutrisi(String nutrisi) {
        this.nutrisi = nutrisi;
    }

    public String getRecoveri() {
        return recoveri;
    }

    public void setRecoveri(String recoveri) {
        this.recoveri = recoveri;
    }

    public String getRecoveriLain() {
        return recoveriLain;
    }

    public void setRecoveriLain(String recoveriLain) {
        this.recoveriLain = recoveriLain;
    }

    public String getMentalSkill() {
        return mentalSkill;
    }

    public void setMentalSkill(String mentalSkill) {
        this.mentalSkill = mentalSkill;
    }

    public String getMentalSkillLain() {
        return mentalSkillLain;
    }

    public void setMentalSkillLain(String mentalSkillLain) {
        this.mentalSkillLain = mentalSkillLain;
    }

    public String getKendalaMentalSkill() {
        return kendalaMentalSkill;
    }

    public void setKendalaMentalSkill(String kendalaMentalSkill) {
        this.kendalaMentalSkill = kendalaMentalSkill;
    }

    public String getSaranMasalah() {
        return saranMasalah;
    }

    public void setSaranMasalah(String saranMasalah) {
        this.saranMasalah = saranMasalah;
    }

    public String getSaranMasalahLain() {
        return saranMasalahLain;
    }

    public void setSaranMasalahLain(String saranMasalahLain) {
        this.saranMasalahLain = saranMasalahLain;
    }

    public String getHalPositif() {
        return halPositif;
    }

    public void setHalPositif(String halPositif) {
        this.halPositif = halPositif;
    }

    public double getScoringMental() {
        return scoringMental;
    }

    public void setScoringMental(double scoringMental) {
        this.scoringMental = scoringMental;
    }

    public double getScoringFisik() {
        return scoringFisik;
    }

    public void setScoringFisik(double scoringFisik) {
        this.scoringFisik = scoringFisik;
    }

    public String getIntensitasTarget() {
        return intensitasTarget;
    }

    public void setIntensitasTarget(String intensitasTarget) {
        this.intensitasTarget = intensitasTarget;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
