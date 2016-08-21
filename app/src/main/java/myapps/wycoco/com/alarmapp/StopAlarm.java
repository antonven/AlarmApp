package myapps.wycoco.com.alarmapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StopAlarm extends AppCompatActivity implements View.OnClickListener {
    Button btnStop;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_alarm);

        btnStop = (Button)findViewById(R.id.btnStop);

        Intent intent = getIntent();
        btnStop.setOnClickListener(this);

        try{
            mp = MediaPlayer.create(this, (Uri)intent.getParcelableExtra("song"));
        }catch(NullPointerException e) {
            mp = MediaPlayer.create(this, R.raw.closer);
        }
        mp.start();
    }


    @Override
    public void onClick(View v) {
        mp.stop();
    }
}
