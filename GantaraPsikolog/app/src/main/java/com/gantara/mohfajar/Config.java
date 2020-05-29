package com.gantara.mohfajar;

public class Config {
    //link api server
    public static final String DATA_URL = "http://192.168.43.119/gantara_web_service/";
//    public static final String DATA_URL = "http://192.168.0.8/gantara_web_service/";
    public static final String DATA_URL_FINAL = DATA_URL+"apiPsikolog.php?apicall=";
    public static final String DATA_URL_PHOTO_PROFIL = DATA_URL + "photo";
    public static final String DATA_URL_LOGIN = DATA_URL_FINAL + "login";
    public static final String DATA_URL_GET_INFO = DATA_URL_FINAL + "info";
    public static final String DATA_URL_INSERT_SIGNUP = DATA_URL_FINAL + "signup";
    public static final String DATA_URL_INSERT_INFO = DATA_URL_FINAL + "addInfo";
    public static final String DATA_URL_GET_FORM = DATA_URL_FINAL + "form";
    public static final String DATA_URL_UPDATE_REKAM_MEDIS = DATA_URL_FINAL + "addTanggapan";
    public static final String DATA_URL_UPDATE_STATUS_FORM = DATA_URL_FINAL + "readForm";
    public static final String DATA_URL_GET_ATLET = DATA_URL_FINAL + "daftarAtlet";
    public static final String DATA_URL_GET_ADD_ATLET = DATA_URL_FINAL + "addAtlet";
    public static final String DATA_URL_GET_REKAM_MEDIS = DATA_URL_FINAL+"rekamMedis";
    public static final String DATA_URL_UPDATE_INTENSITAS = DATA_URL_FINAL+"intensitas";
    public static final String DATA_URL_GET_STATISTIK = DATA_URL_FINAL+"statistik";
    public static final String DATA_URL_UPDATE_PROFILE = DATA_URL_FINAL+"profile";
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