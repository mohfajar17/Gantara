package com.mohfajar.gantara.Chat;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class IsiChat implements Parcelable {

    private int id;
    private String pengirim;
    private String penerima;
    private String waktu;
    private String isi;

    public IsiChat(int id, String pengirim, String penerima, String waktu, String isi) {
        this.id = id;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.waktu = waktu;
        this.isi = isi;
    }

    public IsiChat(String pengirim, String penerima, String waktu, String isi) {
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.waktu = waktu;
        this.isi = isi;
    }

    protected IsiChat(Parcel in) {
        id = in.readInt();
        pengirim = in.readString();
        penerima = in.readString();
        waktu = in.readString();
        isi = in.readString();
    }


    public static final Creator<IsiChat> CREATOR = new Creator<IsiChat>() {
        @Override
        public IsiChat createFromParcel(Parcel in) {
            return new IsiChat(in);
        }

        @Override
        public IsiChat[] newArray(int size) {
            return new IsiChat[size];
        }
    };

    public IsiChat(JSONObject data) throws JSONException {
        this.penerima = data.getString("id_penerima");
        this.pengirim = data.getString("id_pengirim");
        this.waktu = data.getString("waktu");
        this.isi = data.getString("message");
    }

    public IsiChat(Cursor cursor) {
        this.penerima = cursor.getString(cursor.getColumnIndex(DbChat.PENERIMA));
        this.pengirim = cursor.getString(cursor.getColumnIndex(DbChat.PENGIRIM));
        this.waktu = cursor.getString(cursor.getColumnIndex(DbChat.WAKTU));
        this.isi = cursor.getString(cursor.getColumnIndex(DbChat.ISI));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(pengirim);
        dest.writeString(penerima);
        dest.writeString(waktu);
        dest.writeString(isi);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public int getId() {
        return id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getIsi() {
        return isi;
    }


}
