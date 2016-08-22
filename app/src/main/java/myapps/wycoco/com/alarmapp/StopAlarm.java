
//public void onActivityResu
package myapps.wycoco.com.alarmapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class StopAlarm extends AppCompatActivity{
    boolean isRunning;
    Button btnContacts;
    TextView tv;
    EditText txtMsg;
    Button btnStop;
    String number;
    String name;
    MediaPlayer mp;

    private static final int REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_alarm);

        btnContacts = (Button) findViewById(R.id.btnContacts);
        btnStop = (Button)findViewById(R.id.btnStop);
        txtMsg = (EditText)findViewById(R.id.txtMsg);
        tv = (TextView) findViewById(R.id.textView);





        Intent intent = getIntent();
        try{
            mp = MediaPlayer.create(this, (Uri)intent.getParcelableExtra("song1"));
        }catch(NullPointerException e) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, notification);
            r.play();
            mp = MediaPlayer.create(this, R.raw.closer);
        }
        mp.start();


        if (!this.isRunning) {
            this.isRunning = true;
        }

        else if (this.isRunning) {
            mp.stop();
            mp.reset();
            this.isRunning = false;
        }

        else if (!this.isRunning) {
            this.isRunning = false;
        }

        else if (this.isRunning) {
            this.isRunning = true;
        }

        else {

        }









        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(number != null) {
                        mp.stop();
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(number, null, txtMsg.getText().toString(), null, null);
                        Toast.makeText(StopAlarm.this, "Alarm Stopped and Message sent!", Toast.LENGTH_LONG).show();
                        Intent gb = new Intent(StopAlarm.this, MainActivity.class);
                        gb.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(gb);
                    }else{
                        Toast.makeText(StopAlarm.this, "Please Select contact!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(StopAlarm.this, "You have 0 Load balance to send this message!", Toast.LENGTH_SHORT).show();
                }

            }
        });







        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Contacts/");
                intent.setDataAndType(uri, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, REQ_CODE);
            }
        });
    }







    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if(data!=null){
            Uri uri = data.getData();
            if(uri!=null){
                Cursor c = null;
                try{
                    c = getContentResolver().query(uri,new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}, null,null,null);
                    if(c != null && c.moveToFirst()){
                        number = c.getString(0);
                        name = c.getString(1);
                        tv.setText(name + ", " + number);
                    }
                }finally {
                    if(c!=null)
                        c.close();
                }
            }
        }
    }

}
