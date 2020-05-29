package com.gantara.mohfajar.Chat;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Created by Moh_Fajar on 18/04/2017.
 */

public class Chat implements Parcelable {
    private int id;
    private int gambar;
    private String pengirim;
    private String penerima;
    private String waktu;
    private String isi;
    private String id_chatroom;
    private boolean status;
    public static final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat expectedDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    public static final SimpleDateFormat expectedTimeFormat = new SimpleDateFormat("HH:mm");

    public Chat(int id, String id_chatroom, int gambar, String pengirim, String penerima, String waktu, String isi, boolean status){
        this.id=id;
        this.id_chatroom = id_chatroom;
        this.gambar=gambar;
        this.pengirim =pengirim;
        this.penerima = penerima;
        this.waktu=waktu;
        this.isi=isi;
        this.status = status;
    }

    public Chat(String id_chatroom, int gambar, String pengirim, String penerima, String waktu, String isi, boolean status){
        this.id_chatroom = id_chatroom;
        this.gambar=gambar;
        this.pengirim =pengirim;
        this.penerima = penerima;
        this.waktu=waktu;
        this.isi=isi;
        this.status = status;
    }


    public Chat(JSONObject jsonObject){
        try {
            this.penerima = jsonObject.getString("id_penerima");
            this.pengirim = jsonObject.getString("id_pengirim");
            this.waktu = jsonObject.getString("waktu");
            this.isi = jsonObject.getString("message");
            this.status = false;
            this.id_chatroom = this.pengirim;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Chat(Cursor cursor){
        this.id = cursor.getInt(cursor.getColumnIndex(DbChat.ID));
        this.pengirim = cursor.getString(cursor.getColumnIndex(DbChat.PENGIRIM));
        this.penerima = cursor.getString(cursor.getColumnIndex(DbChat.PENERIMA));
        this.waktu = cursor.getString(cursor.getColumnIndex(DbChat.WAKTU));
        this.isi = cursor.getString(cursor.getColumnIndex(DbChat.ISI));
        Log.d("Chat","Status from : "+id+" : "+cursor.getInt(cursor.getColumnIndex(DbChat.STATUS)));
        this.status = cursor.getInt(cursor.getColumnIndex(DbChat.STATUS)) == 1 ? true:false;
        this.id_chatroom = cursor.getString(cursor.getColumnIndex(DbChat.ID_CHATROOM));
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    //set item


    public String getId_chatroom() {
        return id_chatroom;
    }

    public void setId_chatroom(String id_chatroom) {
        this.id_chatroom = id_chatroom;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    //get item

    public int getId() {
        return id;
    }

    public int getGambar() {
        return gambar;
    }

    public String getIsi() {
        return isi;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getWaktu() {
        return waktu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.gambar);
        dest.writeString(this.pengirim);
        dest.writeString(this.penerima);
        dest.writeString(this.waktu);
        dest.writeString(this.isi);
        dest.writeString(this.id_chatroom);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
    }

    protected Chat(Parcel in) {
        this.id = in.readInt();
        this.gambar = in.readInt();
        this.pengirim = in.readString();
        this.penerima = in.readString();
        this.waktu = in.readString();
        this.isi = in.readString();
        this.id_chatroom = in.readString();
        this.status = in.readByte() != 0;
    }

    public String getExpectedDate(SimpleDateFormat df){
        try {
            String time = df.format(dbDateFormat.parse(this.waktu));
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getExpectedTime(){
        try {
            String time = expectedTimeFormat.format(dbDateFormat.parse(this.waktu));
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getExpectedDate(){
        try {
            String date = expectedDateFormat.format(dbDateFormat.parse(this.waktu));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public static class ChatComparator implements Comparator<Chat> {

        @Override
        public int compare(Chat o1, Chat o2) {
            return o1.getWaktu().compareTo(o2.getWaktu());
        }
    }
}