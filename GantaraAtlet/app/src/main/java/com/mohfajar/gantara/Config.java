package com.mohfajar.gantara;

public class Config {
    //link api server
    public static final String DATA_URL = "http://192.168.1.22/gantara_web_service/";
//    public static final String DATA_URL = "http://192.168.0.8/gantara_web_service/";
    public static final String DATA_URL_FINAL = DATA_URL+"apiAtlet.php?apicall=";
    public static final String DATA_URL_PHOTO_PROFIL = DATA_URL+"photo";
    public static final String DATA_URL_LOGIN = DATA_URL_FINAL+"login";
    public static final String DATA_URL_GET_INFO = DATA_URL_FINAL+"info";
    public static final String DATA_URL_INSERT_SIGNUP = DATA_URL_FINAL+"signup";
    public static final String DATA_URL_INSERT_FORM = DATA_URL_FINAL+"addForm";
    public static final String DATA_URL_GET_TANGGAPAN = DATA_URL_FINAL+"tanggapan";
    public static final String DATA_URL_UPDATE_READ_REKAM_MEDIS = DATA_URL_FINAL+"readStatusRekamMedis";
    public static final String DATA_URL_GET_REKAM_MEDIS = DATA_URL_FINAL+"rekamMedis";
    public static final String DATA_URL_GET_STATISTIK = DATA_URL_FINAL+"statistik";
    public static final String DATA_URL_UPDATE_PROFILE = DATA_URL_FINAL+"profile";
    public static final String DATA_URL_UPDATE_PHOTO_PROFILE = DATA_URL_FINAL+"photoProfile";
    public static final String DATA_URL_SEND_CHAT = DATA_URL+"sendChats.php";

    //key data
    public static final String KEY_STATUS = "status";
    public static final String KEY_DATA = "data";
    public static final String KEY_ID = "id";
    public static final String KEY_ID_PSIKOLOG = "id_psikolog";
    public static final String KEY_JUDUL = "judul";
    public static final String KEY_DARI = "dari";
    public static final String KEY_UNTUK = "untuk";
    public static final String KEY_WAKTU = "waktu";
    public static final String KEY_ISI_INFO = "isi_info";
}