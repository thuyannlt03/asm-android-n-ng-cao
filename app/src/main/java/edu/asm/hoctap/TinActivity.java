package edu.asm.hoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TinActivity extends AppCompatActivity {
    String diachi_rss;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_theo_loai);

        lv=this.findViewById(R.id.lv);
        diachi_rss=getIntent().getExtras().getString("diachi_rss");

        Toast.makeText(getApplicationContext(), diachi_rss, Toast.LENGTH_SHORT).show();

        MyAsyncTask gandulieu=new MyAsyncTask(TinActivity.this,diachi_rss);
        gandulieu.execute();

    }
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        ArrayList<Item> items=new ArrayList<Item>();
        String chuoi="";
        Context c;
        String diachi_rss;
        MyAsyncTask(Context c, String diachi_rss) {
            this.diachi_rss=diachi_rss;
            this.c=c;
        }
        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                URL url=new URL(diachi_rss);
                URLConnection connection=url.openConnection();

                InputStream is=connection.getInputStream();
                items=(ArrayList<Item>) MySaxParser.xmlParser(is);
                for (int i=0;i<items.size();i++){
                    chuoi+=items.get(i).getTitle()+"";
                    chuoi+=items.get(i).getPubdate()+"\n";
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("dulieu",e.toString());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.d("dulieu",chuoi);

            try{
                TinTheoLoaiAdapter adapter=new TinTheoLoaiAdapter(c,R.layout.item_tintheoloai,items);
                ((TinActivity)c).lv.setAdapter(adapter);
                ((TinActivity)c).lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String link=items.get(position).getLink();
                        // Toast.makeText(getApplicationContext(),link,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(c,XemTinActivity.class);
                        intent.putExtra("link", link);
                        c.startActivity(intent);
                    }
                });

            }catch(Exception e) {
                Log.d("title","adapter khong duoc");
            }

        }
    }
}