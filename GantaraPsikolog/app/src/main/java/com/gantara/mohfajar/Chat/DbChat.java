package com.gantara.mohfajar.Chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moh_Fajar on 20/06/2017.
 */

public class DbChat extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="pmondb.db";
    public static final String TABLE_CHAT ="chat";
    public static final String TABLE_CHATROOM ="chatroom";
    public static final String TABLE_ATLET ="atlet";
    public static final String ID ="id";
    public static final String PENGIRIM ="pengirim";
    public static final String PENERIMA ="penerima";
    public static final String WAKTU ="waktu";
    public static final String ISI ="isi";
    public static final String STATUS ="status";
    public static final String ID_CHATROOM ="id_chatroom";
    public static final int DATABASE_VERSION=1;
    public static final String ID_ATLET = "id_atlet";
    public static final String ID_PSIKOLOG = "id_psikolog";
    public static final String NAMA = "nama";
    public static final String CABANG_OLAHRAGA = "cabang_olahraga";
    public static final String JENIS_KELAMIN = "jenis_kelamin";
    public static final String TEMPAT_LAHIR = "tempat_lahir";
    public static final String TANGGAL_LAHIR = "tanggal_lahir";
    public static final String ALAMAT = "alamat";
    public static final String KOTA_ASAL = "kota_asal";
    public static final String NO_TELEFON = "no_telefon";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    public static final String PHOTO_PROFILE = "photo_profile";
    private static final String CRATE_TABLE_ATLET = "CREATE TABLE IF NOT EXISTS " + TABLE_ATLET + " ( " +
            ID_ATLET + " TEXT PRIMARY KEY, " +
            ID_PSIKOLOG + " TEXT, " +
            NAMA + " TEXT, " +
            CABANG_OLAHRAGA + " TEXT, " +
            JENIS_KELAMIN + " INTEGER, " +
            TEMPAT_LAHIR + " TEXT, " +
            TANGGAL_LAHIR + " TEXT, " +
            ALAMAT + " TEXT, " +
            NO_TELEFON + " TEXT, " +
            USER_NAME + " TEXT, " +
            KOTA_ASAL + " TEXT, " +
            TOKEN + " TEXT, " +
            PHOTO_PROFILE + " TEXT );";
    private static final String CREATE_TABLE_CHATROOM = "CREATE TABLE IF NOT EXISTS  "+ TABLE_CHATROOM + " ( "+PENGIRIM +" TEXT PRIMARY KEY, " + WAKTU +" TEXT, " + ISI +" TEXT );";
    private static final String CREATE_TABLE_CHAT ="CREATE TABLE IF NOT EXISTS  "+ TABLE_CHAT +" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ ID_CHATROOM + " TEXT, " +PENGIRIM+" TEXT, "+PENERIMA+" TEXT, "+WAKTU+" TEXT, "+ISI+" TEXT, "+ STATUS + " INTEGER );";
    private Context context;

    public DbChat(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Log.d("PMon_DB","Query create table atlet : "+CREATE_TABLE_CHAT);
            db.execSQL(CREATE_TABLE_CHAT);
            db.execSQL(CREATE_TABLE_CHATROOM);
            db.execSQL(CRATE_TABLE_ATLET);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATLET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHATROOM);
        onCreate(db);
    }

    public long saveChat(Chat log){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PENGIRIM,log.getPengirim());
        cv.put(PENERIMA,log.getPenerima());
        cv.put(WAKTU,log.getWaktu());
        cv.put(ISI,log.getIsi());
        cv.put(STATUS,log.getStatus()? 1:0);
        cv.put(ID_CHATROOM,log.getId_chatroom());
        Log.d("Chat","Status from save : "+cv.get(STATUS));
        Log.d("Chat","Waktu from save : "+cv.get(WAKTU));
        long id = db.insert(TABLE_CHAT,null,cv);
        db.close();
        return id;
    }

    public ArrayList<Chat> getChatandUpdateSatus(String id_chatroom, boolean status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv;
        ArrayList<Chat> chats = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_CHAT + " WHERE " + ID_CHATROOM + " = " +id_chatroom +
                " ORDER BY " + WAKTU + " ASC";
        Log.d("Pmon","Query getChat and update : "+sql);
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        Log.d("Pmon-Chat","Chat count " +cursor.getCount());
        if(cursor.getCount() > 0 ){
            do {
                cv = new ContentValues();
                Chat chat = new Chat(cursor);
                cv.put(ID,chat.getId());
                cv.put(PENGIRIM, chat.getPengirim());
                cv.put(PENERIMA, chat.getPenerima());
                cv.put(WAKTU, chat.getWaktu());
                cv.put(ISI, chat.getIsi());
                cv.put(ID_CHATROOM, chat.getId_chatroom());
                cv.put(STATUS, status ? 1 : 0);
                Log.d("Pmon-Chat","Status on update from " + chat.getId()+ " : " + cv.get(STATUS));
                db.update(TABLE_CHAT, cv,ID+"="+chat.getId(),null);
                chat.setStatus(status);
                chats.add(chat);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return chats;

    }

    public void saveAtlets(List<AtletChat> atlets){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = null;
        for(AtletChat atlet : atlets){
            cv = new ContentValues();
            cv.put(ID_ATLET,atlet.getId_atlet());
            cv.put(ID_PSIKOLOG,atlet.getId_psikolog());
            cv.put(NAMA,atlet.getNama());
            cv.put(CABANG_OLAHRAGA,atlet.getCabang_olahraga());
            cv.put(JENIS_KELAMIN,atlet.getJenis_kelamin());
            cv.put(TEMPAT_LAHIR,atlet.getTempat_lahir());
            cv.put(TANGGAL_LAHIR,atlet.getTanggal_lahir());
            cv.put(ALAMAT,atlet.getAlamat());
            cv.put(KOTA_ASAL,atlet.getKota_asal());
            cv.put(NO_TELEFON,atlet.getNo_telefon());
            cv.put(USER_NAME,atlet.getUser_name());
            cv.put(PHOTO_PROFILE,atlet.getPhoto_profile());
            cv.put(TOKEN,atlet.getToken());

            db.insert(TABLE_ATLET,null,cv);
        }
        db.close();
    }

    public String getNamaAtlet(String id){
        String nama = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ATLET + " WHERE " + ID_ATLET + " = " + id;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            nama = cursor.getString(cursor.getColumnIndex(NAMA));
        }
        cursor.close();
        db.close();
        return nama;
    }

    public List<ChatRoom> getAtletsWithChat (){
        List<ChatRoom> chatRooms = new ArrayList<>();
        ArrayList<Chat> chats = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ATLET +";";

        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 ){
            do{
                String id_atlet = cursor.getString(cursor.getColumnIndex(ID_ATLET));
                String lastChat = "";
                String lastChatDate = "";
                chats = new ArrayList<>();
                int unReadChats = 0;
                boolean lastStatus = true;
                String sqlGetChat = "SELECT * FROM " + TABLE_CHAT + " WHERE " + ID_CHATROOM + " = " + id_atlet +
                        " ORDER BY "+ WAKTU + " DESC";
                Log.d("Pmon","Query getChat : "+sqlGetChat);
                Cursor cursorChat = db.rawQuery(sqlGetChat,null);
                cursorChat.moveToFirst();
                if(cursorChat.getCount() > 0){
                    do {
//                        Log.d("Chat","Status from save : "+cursorChat.getInt(cursorChat.getColumnIndex(STATUS)));
                        chats.add(new Chat(cursorChat));
                    }
                    while (cursorChat.moveToNext());
                    Log.d("Pmon","Query getChat success : "+chats.size());
                    lastChat = chats.get(0).getIsi();
                    lastChatDate = chats.get(0).getWaktu();
                    lastStatus = chats.get(0).getStatus();
//                    Log.d("Chat","Status from save : "+lastStatus);
//                    Log.d("Chat","Waktu: "+lastStatus);
//                    unReadChats = chats.size();
                }
                else lastChat = "Belum ada pesan";
                ChatRoom chatRoom = new ChatRoom(cursor);
                chatRoom.setLastChat(lastChat);
                chatRoom.setLastChatDate(lastChatDate);
                chatRoom.setUnreadCount(unReadChats);
                chatRoom.setChatList(chats);
                chatRoom.setLastStatus(lastStatus);
                chatRooms.add(chatRoom);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return chatRooms;
    }
}