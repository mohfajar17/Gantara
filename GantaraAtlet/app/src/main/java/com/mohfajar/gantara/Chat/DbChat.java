package com.mohfajar.gantara.Chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbChat extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="pmondb.db";
    public static final String TABLE_CHAT ="chat";
    public static final String ID ="id";
    public static final String PENGIRIM ="pengirim";
    public static final String PENERIMA ="penerima";
    public static final String WAKTU ="waktu";
    public static final String ISI ="isi";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_TABLE="CREATE TABLE "+ TABLE_CHAT +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PENGIRIM+" VARCHAR(255), "+PENERIMA+" VARCHAR(255), "+WAKTU+" VARCHAR(255), "+ISI+" VARCHAR(255));";
    private Context context;

    public DbChat(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS teknorialtable");
        onCreate(db);
    }

    public long save(IsiChat log){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PENGIRIM,log.getPengirim());
        cv.put(PENERIMA,log.getPenerima());
        cv.put(WAKTU,log.getWaktu());
        cv.put(ISI,log.getIsi());

        long id = db.insert(TABLE_CHAT,null,cv);

        return id;
    }

    public ArrayList<IsiChat> getAthleteChat(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<IsiChat> chats = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_CHAT + " ORDER BY " +WAKTU + " ASC" ;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {
                chats.add(new IsiChat(cursor));
            }
            while (cursor.moveToNext());
        }

        return chats;

    }
}