package edu.asm.hoctap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class LopHocDAO {
    SQLiteDatabase db;
    Context c;

    public LopHocDAO(Context c) {
        this.c = c;
        MyHelper helper = new MyHelper(c);
        db = helper.getWritableDatabase();
    }
    public void insertLop(LopHoc lh){
        ContentValues values = new ContentValues();
        values.put("tenlop",lh.getTenLop());
        db.insert("LopHoc",null,values);
    }
    public ArrayList<LopHoc> showlist(){
        ArrayList<LopHoc> dslh = new ArrayList<>();
        String sql ="select * from LopHoc";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()== true){
            do{
                int idlop = c.getInt(0);
                String tenlop = c.getString(1);
                LopHoc lh = new LopHoc(idlop,tenlop);
                dslh.add(lh);
            }while (c.moveToNext()==true);
        }
        return dslh;
    }
    public void deletelop(int _id) {
        db.delete(
                "LopHoc",
                "_id=?",
                new String[]{_id + ""}
        );
    }
    public void editlophoc(LopHoc lh) {
        ContentValues values = new ContentValues();
        values.put("tenlop",lh.getTenLop());
        db.update(
                "LopHoc",
                values,
                "_id=?",
                new String[]{lh.get_id()+""}
        );
    }
}

