package edu.asm.hoctap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class LopHocActivity extends AppCompatActivity {
    ListView dslop;
    Button btAdd,btUpdate;
    EditText inputTenLop;
    TextView malop,tenlop,ttID;
    LopHocDAO lopDAO;
    ArrayList<LopHoc> dslh = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc);

        dslop = findViewById(R.id.lvLopHoc);
        inputTenLop = findViewById(R.id.edtTenLop);
        malop = findViewById(R.id.tvIdEdit);
        tenlop = findViewById(R.id.tvTenLopEdit);
        ttID = findViewById(R.id.tvtitleID);
        btAdd = findViewById(R.id.btnAdd);
        btUpdate = findViewById(R.id.btnUpdate);

        lopDAO = new LopHocDAO(LopHocActivity.this);
        loadActivity();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lop = inputTenLop.getText().toString();
                LopHoc lh = new LopHoc(lop);
                lopDAO.insertLop(lh);
                loadActivity();

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int _id = Integer.parseInt(malop.getText().toString());
                 String tenlop = inputTenLop.getText().toString();
                 LopHoc lh = new LopHoc(_id,tenlop);
                 lopDAO.editlophoc(lh);
                 loadActivity();
            }
        });
    }

    public void loadActivity() {
        dslh = lopDAO.showlist();
        LopHocAdapter adapter = new LopHocAdapter(LopHocActivity.this,dslh);
        dslop.setAdapter(adapter);
    }
    public void deletelop(int _id){
        lopDAO.deletelop(_id);
        loadActivity();
    }

    public void ValuesClassEdit(LopHoc lh) {
        ttID.setText("Mã Lớp: ");
        malop.setText(lh.get_id()+"");
        tenlop.setText("Tên Lớp: "+lh.getTenLop());
        inputTenLop.setHint("Nhập Tên Lớp Muốn Đổi");
    }
}