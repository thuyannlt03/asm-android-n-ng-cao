package edu.asm.hoctap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    Context c;
    ArrayList<SinhVien> dsSV = new ArrayList<>();

    public SinhVienAdapter(Context c, ArrayList<SinhVien> dsSV) {
        this.c = c;
        this.dsSV = dsSV;
    }

    @Override
    public int getCount() {
        return dsSV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.layout_sinhvien_listview,null);
        TextView tvMssv = view.findViewById(R.id.tvIdSV);
        TextView tvTenSv = view.findViewById(R.id.tvTenSV);
        TextView tvMaLop = view.findViewById(R.id.tvIdLopSV);
        Button btEditSV = view.findViewById(R.id.btnEditSV);
        Button btDeleteSV = view.findViewById(R.id.btnDeleteSV);

        SinhVien sv = dsSV.get(i);
        tvMssv.setText("MSSV:"+sv.getMssv()+"");
        tvTenSv.setText("Tên: "+sv.getTen());
        tvMaLop.setText("ID Lớp: "+sv.getId_lop()+"");

        btEditSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sv = dsSV.get(i);
                ((SinhVienActivity)c).ValuesSVEdit(sv);
            }
        });

        btDeleteSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int _id = dsSV.get(i).getMssv();
                ((SinhVienActivity)c).deleteSV(_id);
            }
        });
        return view;
    }
}
