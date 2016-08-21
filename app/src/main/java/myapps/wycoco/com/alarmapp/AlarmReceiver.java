package myapps.wycoco.com.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;




public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }
    Uri songUri2;
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp;


//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
//        r.play();



//        String son =
        songUri2 = intent.getParcelableExtra("song");

        Intent al = new Intent(context, StopAlarm.class);
        al.putExtra("song1", songUri2);
        al.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(al);

    }


    }



