package edu.asm.hoctap;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class SocialActivity extends AppCompatActivity {
    int UPDATE_FREQUENCY = 500;
    int STEP_VALUE = 4000;
    TextView tv_fileduocchon;
    SeekBar seekbar;
    MediaPlayer player;
    ImageButton bt_play, bt_prev, bt_next;
    ListView lv;
    boolean da_play = true;
    String filehientai = "";
    boolean dichuyenseekbar = false;

    private final Handler handler = new Handler();

    private final Runnable updatePositionRunnable = new Runnable() {

        public void run() {

            updatePosition();

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        tv_fileduocchon = findViewById(R.id.selectedfile);
        bt_play = findViewById(R.id.play);
        bt_next =  findViewById(R.id.next);
        bt_prev = findViewById(R.id.prev);
        seekbar = findViewById(R.id.seekbar);
        lv = findViewById(R.id.list);

        player=new MediaPlayer();

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        MyAdapter adapter = new MyAdapter(SocialActivity.this, R.layout.one_item, cursor);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id)

            {

                filehientai=(String)view.getTag();

                Toast.makeText(SocialActivity.this, filehientai, Toast.LENGTH_SHORT).show();

                batdauphatnhac(filehientai);

            }

        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (dichuyenseekbar == true)
                    player.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                dichuyenseekbar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                dichuyenseekbar = false;
            }
        });

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying())//dang phat
                {
                    handler.removeCallbacks(updatePositionRunnable);
                    player.pause();
                    bt_play.setImageResource(android.R.drawable.ic_media_play);
                } else//dang pause
                {
                    if (da_play == true)//dang chay thi chay tiep
                    {
                        player.start();
                        bt_play.setImageResource(android.R.drawable.ic_media_pause);
                        updatePosition();
                    } else //chua chay ma
                    {
                        batdauphatnhac(filehientai);
                    }
                }
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int seekto = player.getCurrentPosition() + STEP_VALUE;
                if(seekto > player.getDuration()) seekto = player.getDuration();
                player.pause();
                player.seekTo(seekto);
                player.start();
                updatePosition();
            }
        });

        bt_prev.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int seekto = player.getCurrentPosition() - STEP_VALUE;
                if(seekto < 0)

            seekto = 0;
                player.pause();
                player.seekTo(seekto);
                player.start();
                updatePosition();
            }
        });


    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.reset();
        player.release();
        player = null;
        handler.removeCallbacks(updatePositionRunnable);
    }

    private void batdauphatnhac(String filehientai) {

        tv_fileduocchon.setText(filehientai);

        seekbar.setProgress(0);
        player.stop();
        player.reset();

        try {
            player.setDataSource(filehientai);
            player.prepare();
            player.start();
        } catch (Exception e) {

        }
        seekbar.setMax(player.getDuration());
        bt_play.setImageResource(android.R.drawable.ic_media_pause);
        da_play = true;
        updatePosition();
    }

    private void updatePosition(){
        handler.removeCallbacks(updatePositionRunnable);
        seekbar.setProgress(player.getCurrentPosition());
        handler.postDelayed(updatePositionRunnable, UPDATE_FREQUENCY);
    }


    class MyAdapter extends SimpleCursorAdapter {

        public MyAdapter(Context context, int layout, Cursor c) {

            super(context, layout, c, new String[]{MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.TITLE,
                    MediaStore.Audio.AudioColumns.DURATION}, new int[]{R.id.displayname, R.id.title, R.id.duration}, FLAG_REGISTER_CONTENT_OBSERVER);

        }
        @Override

        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(context);

            View v = inflater.inflate(R.layout.one_item, parent, false);

            bindView(v, context, cursor);

            return v;

        }

        @Override

        public void bindView(View view, Context context, Cursor cursor) {

            TextView title = (TextView) view.findViewById(R.id.title);

            TextView name = (TextView) view.findViewById(R.id.displayname);

            TextView duration = (TextView) view.findViewById(R.id.duration);


            name.setText(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)));

            title.setText(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.TITLE)));

            long durationInMs = Long.parseLong(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION)));

            double durationInMin = ((double) durationInMs / 1000.0) / 60.0;

            durationInMin = new BigDecimal(Double.toString(durationInMin)).setScale(2, BigDecimal.ROUND_UP).doubleValue();

            duration.setText("" + durationInMin);

            view.setTag(cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA)));

        }

    }

}