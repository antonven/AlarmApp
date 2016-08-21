package myapps.wycoco.com.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;




public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp;


//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
//        r.play();



//        String son = String.valueOf((Uri)intent.getParcelableExtra("song"));

        Intent al = new Intent(context, StopAlarm.class);
        al.putExtra("song", intent.getParcelableExtra("song"));
        al.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(al);

    }


    }



