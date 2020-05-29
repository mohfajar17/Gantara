package com.mohfajar.gantara;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private Context mContext;

    public static final String PREF_NAME  = "GantaraPref";
    public static final String KEY_ATLET_ID = "atlet_id";
    public static final String KEY_PSIKOLOG_ID = "psikolog_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ISLOGGEDIN = "is_logged_in";
    public static final String KEY_CABOR = "cabor";
    public static final String KEY_TEMPAT_LAHIR = "tempat_lahir";
    public static final String KEY_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String KEY_KOTA_ASAL = "kota_asal";
    public static final String KEY_NO_TLP = "no_tlp";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_JENIS_KELAMIN = "gender";
    public static final String KEY_PHOTO = "photo_profile";
    public static final String KEY_INTENSITAS_TARGET = "intensitas_target";

    public SharedPrefManager(Context context){
        this.mContext = context;
    }

    public static SharedPrefManager getInstance(Context context){
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        return sharedPrefManager;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_ISLOGGEDIN) && sharedPreferences.getBoolean(KEY_ISLOGGEDIN,false);
    }

    public void loginAtlet(String atletid, String psikologid, String name, String cabor, int jenis_kelamin, String tempat_lahir, String tanggal_lahir, String alamat, String kota_asal, String no_tlp, String user_name, String photo, float intensitas_target){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ATLET_ID,atletid);
        editor.putString(KEY_PSIKOLOG_ID,psikologid);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_CABOR,cabor);
        editor.putInt(KEY_JENIS_KELAMIN,jenis_kelamin);
        editor.putString(KEY_TEMPAT_LAHIR,tempat_lahir);
        editor.putString(KEY_TANGGAL_LAHIR,tanggal_lahir);
        editor.putString(KEY_ALAMAT,alamat);
        editor.putString(KEY_KOTA_ASAL,kota_asal);
        editor.putString(KEY_NO_TLP,no_tlp);
        editor.putString(KEY_USER_NAME,user_name);
        editor.putString(KEY_PHOTO,photo);
        editor.putFloat(KEY_INTENSITAS_TARGET,intensitas_target);
        editor.putBoolean(KEY_ISLOGGEDIN,true);

        editor.apply();
    }

    public void loadDataAtlet(String psikologid, String cabor, String alamat, String kota_asal, String no_tlp, String user_name){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_PSIKOLOG_ID,psikologid);
        editor.putString(KEY_CABOR,cabor);
        editor.putString(KEY_ALAMAT,alamat);
        editor.putString(KEY_KOTA_ASAL,kota_asal);
        editor.putString(KEY_NO_TLP,no_tlp);
        editor.putString(KEY_USER_NAME,user_name);

        editor.apply();
    }

    public String getAtletID(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ATLET_ID,null);
    }

    public String getPsikologID(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PSIKOLOG_ID,null);
    }

    public String getName(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null);
    }

    public String getUserName(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME,null);
    }

    public String getCabor(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CABOR,null);
    }

    public String getTempatLahir(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TEMPAT_LAHIR,null);
    }

    public String getTanggalLahir(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TANGGAL_LAHIR,null);
    }

    public String getAlamat(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ALAMAT,null);
    }

    public String getKotaAsal(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_KOTA_ASAL,null);
    }

    public String getNoTlp(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NO_TLP,null);
    }

    public int getJenisKelamin(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_JENIS_KELAMIN,1);
    }

    public String getPhoto(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHOTO,null);
    }

    public float getIntensitasTarget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_INTENSITAS_TARGET,0f);
    }

    public void logout(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        mContext.deleteDatabase(DbChat.DATABASE_NAME);
        editor.clear();
        editor.apply();
    }
}
