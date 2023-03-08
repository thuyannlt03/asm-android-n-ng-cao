package edu.asm.hoctap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class SinhVienDao {
    SQLiteDatabase db;
    Context c;
    public SinhVienDao(Context c) {
        this.c = c;
        MyHelper helper = new MyHelper(c);
        db = helper.getWritableDatabase();
    }
    public void insertSinhVien(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("tenSinhVien",sv.getTen());
        values.put("id_lophoc",sv.getId_lop());
        db.insert("SinhVien",null,values);
    }
    public ArrayList<SinhVien> showlistSV(){
        ArrayList<SinhVien> dsSV = new ArrayList<>();
        String sql ="select * from SinhVien";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()== true){
            do{
                int maSV = c.getInt(0);
                String tenSV = c.getString(1);
                int ma_LH = c.getInt(2);
                SinhVien sv = new SinhVien(tenSV,maSV,ma_LH);
                dsSV.add(sv);
            }while (c.moveToNext()==true);
        }
        return dsSV;
    }
    public void deleteSV(int _id) {
        db.delete(
                "SinhVien",
                "_id=?",
                new String[]{_id + ""}
        );
    }
    public void editSV(SinhVien sv) {
        ContentValues values = new ContentValues();
        values.put("tenSinhVien",sv.getTen());
        values.put("id_lophoc",sv.getId_lop());
        db.update(
                "SinhVien",
                values,
                "_id=?",
                new String[]{sv.getMssv()+""}
        );
    }
}

