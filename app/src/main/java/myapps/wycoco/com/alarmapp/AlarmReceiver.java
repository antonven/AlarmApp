package myapps.wycoco.com.alarmapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }
    Uri songUri2, notification;
    NotificationManager notify;
    @Override
    public void onReceive(Context context, Intent intent) {

//        NotificationManager notify_manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification_popup = new Notification.Builder()
//                .setContentTitle("An alarm is going off!")
//                .setContentText("Click me!")
////                .setSmallIcon(R.drawable.)
////                .setContentIntent(pending_intent_main_activity)
//                .setAutoCancel(true)
//                .build();
//        notify = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notify.notify();
//        Toast.makeText(context, "WAKE UP!!!!!", Toast.LENGTH_SHORT).show();
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
        r.play();



        songUri2 = intent.getParcelableExtra("song");

        Intent al = new Intent(context, StopAlarm.class);
        al.putExtra("song1", songUri2);
        al.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(al);

    }


    }



