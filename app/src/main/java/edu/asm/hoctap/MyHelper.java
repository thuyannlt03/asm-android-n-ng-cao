package edu.asm.hoctap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    static String dbname = "QuanlyLopHoc";
    static  int dbversion = 1;
    static Context c;
    public MyHelper(@Nullable Context context) {
        super(context,dbname,null,dbversion);
        this.c = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLopHoc = "Create table LopHoc"+"("+
                "_id integer primary key autoincrement,"+
                "tenlop text"+
                ")";
        db.execSQL(sqlLopHoc);
        String sqlSinhVien = "Create table SinhVien"+"("+
                "_id integer primary key autoincrement,"+
                "tenSinhVien text,"+
                "id_lophoc integer"+
                ")";
        db.execSQL(sqlSinhVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists LopHoc");
        db.execSQL("drop table if exists SinhVien");
        this.onCreate(db);
    }
    public void insertLop(String tenlop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenlop",tenlop);
        db.insert("LopHoc",null,values);
    }

}

