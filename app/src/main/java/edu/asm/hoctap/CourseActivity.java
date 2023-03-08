package edu.asm.hoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class CourseActivity extends AppCompatActivity {

    Button btnLop,btnSinhVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        btnLop = findViewById(R.id.btnAtivityLop);
        btnSinhVien = findViewById(R.id.btnActivitySinhVien);

        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseActivity.this,LopHocActivity.class);
                startActivity(i);
            }
        });
        btnSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(CourseActivity.this,SinhVienActivity.class);
               startActivity(intent);
            }
        });
    }

}