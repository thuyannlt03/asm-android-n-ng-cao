package edu.asm.hoctap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;




public class NewsActivity extends AppCompatActivity {
    String [] ten_loai={"Giáo dục","Công nghệ thông tin","Điện thoại","Tin tức","Sức khỏe đời sống"};

    String [] rss_loai={"https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss",

            "https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss",

            "https://www.24h.com.vn/upload/rss/dienthoai.rss",

            "https://cdn.24h.com.vn/upload/rss/tintuctrongngay.rss",

            "https://cdn.24h.com.vn/upload/rss/suckhoedoisong.rss"

    };

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lv = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ten_loai);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,

                                    long arg3) {
                Intent intent = new Intent(getApplicationContext(),TinActivity.class);
                intent.putExtra("diachi_rss",rss_loai[arg2]);
                startActivity(intent);
            }
        });

    }

}