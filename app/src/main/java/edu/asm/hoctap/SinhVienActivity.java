package edu.asm.hoctap;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;

public class SinhVienActivity extends AppCompatActivity {
    ListView dsSinhVien;
    Button bt_addSV,bt_UpdateSV;
    EditText et_tenSV;
    TextView maSV,TenlopSV,tenSV,ttID;
    Spinner sp_lop;
    ArrayList<LopHoc> dsLH = new ArrayList<>();
    ArrayList<SinhVien> dsSV = new ArrayList<>();
    LopHocDAO lopDAO;
    SinhVienDao SVDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        dsSinhVien = findViewById(R.id.lvSinhVien);
        maSV = findViewById(R.id.tvIDSVEdit);
        tenSV = findViewById(R.id.tvTenSVEdit);
        TenlopSV = findViewById(R.id.tvTenLopSVEdit);
        et_tenSV = findViewById(R.id.edtTenSV);
        ttID = findViewById(R.id.tvttSinhVien);
        sp_lop = findViewById(R.id.spDSLop);
        bt_addSV = findViewById(R.id.btnAddSV);
        bt_UpdateSV = findViewById(R.id.btnUpdateSV);

        //Do DLspinner
        lopDAO = new LopHocDAO(SinhVienActivity.this);
        dsLH = lopDAO.showlist();
        ArrayList<String> ds_tenlop = new ArrayList<>();
        for (int i =0 ; i<dsLH.size();i++){
            ds_tenlop.add(dsLH.get(i).getTenLop());
        }
        ArrayAdapter adapter = new ArrayAdapter(SinhVienActivity.this,
                android.R.layout.simple_spinner_item,
                ds_tenlop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_lop.setAdapter(adapter);



        
        dodulieuSinhVien();

        bt_addSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = et_tenSV.getText().toString();
                int index = sp_lop.getSelectedItemPosition();
                int id_lop = dsLH.get(index).get_id();
                SinhVien sv = new SinhVien(ten,id_lop);
                SVDAO.insertSinhVien(sv);
                dodulieuSinhVien();
            }
        });
        bt_UpdateSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int _id = Integer.parseInt(maSV.getText().toString());
                String tenSV = et_tenSV.getText().toString();
                int id_lop = Integer.parseInt(TenlopSV.getText().toString());
                SinhVien sv = new SinhVien(tenSV,_id,id_lop);
                SVDAO.editSV(sv);
                dodulieuSinhVien();
            }
        });
    }
    public void dodulieuSinhVien(){
        SVDAO = new SinhVienDao(SinhVienActivity.this);
        dsSV = SVDAO.showlistSV();
        SinhVienAdapter sinhVienAdapter = new SinhVienAdapter(SinhVienActivity.this,dsSV);
        dsSinhVien.setAdapter(sinhVienAdapter);
    }
    public void deleteSV(int _id){
        SVDAO.deleteSV(_id);
        dodulieuSinhVien();
    }

    public void ValuesSVEdit(SinhVien sv) {
        ttID.setText("MSSV: ");
        maSV.setText(sv.getMssv()+"");
        tenSV.setText("Tên: "+sv.getTen());
        TenlopSV.setText(sv.getId_lop()+"");
        et_tenSV.setHint("Nhập Tên Cần Đổi");
    }
}