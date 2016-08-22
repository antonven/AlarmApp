package myapps.wycoco.com.alarmapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class SetAlarm extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnDate;
    int years, month, day;
    static final int DIALOG_ID = 0;
    PendingIntent pi;
    Uri songUri;
    AlarmManager am;
    Bundle b;
    Intent intent;
    ListView songView;
    ListAdapter adap;
    String sTitle, sArtist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        String[] song = {"Choose an alarm song"};
        Intent intent = getIntent();


        adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song);
        songView = (ListView)findViewById(R.id.songView);
        songView.setAdapter(adap);
        songView.setOnItemClickListener(this);


        Button btnAlarm = (Button)findViewById(R.id.btnAlarm);
        Button btnStop = (Button)findViewById(R.id.btnStop);


        final Calendar cal = Calendar.getInstance();
        years = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

        btnAlarm.setOnClickListener(this);

        assert btnStop != null;
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pi.cancel();
                am.cancel(pi);
                Toast.makeText(SetAlarm.this, "Alarm has been cancelled!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showDialogOnButtonClick(){
        btnDate = (Button)findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, years, month, day);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            years = year;
            month = monthOfYear + 1;
            day = dayOfMonth;
        }
    };

    @Override
    public void onClick(View v) {
        TimePicker tp = (TimePicker)findViewById(R.id.timePicker);

        Calendar cal = Calendar.getInstance();
        int hr = tp.getCurrentHour();
        int min = tp.getCurrentMinute();

        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.YEAR, years);
//        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        String hour = String.valueOf(hr);
        String mt = String.valueOf(min);
        String yr = String.valueOf(years);
        String mo = String.valueOf(month);
        String d = String.valueOf(day);

        if(hr > 12)
            hour = String.valueOf(hr - 12);
        if(min < 10)
            mt = String.valueOf("0" + mt);

        Toast.makeText(this, "Alarm set to " + hour +":"+ mt +" on "+ mo +"/" +d +"/" + yr, Toast.LENGTH_LONG).show();

        intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("song", songUri);
        pi = PendingIntent.getBroadcast(this, 234324243, intent, 0);

        am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
        songUri = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Audio/");
        intent.setDataAndType(uri, "audio/*");
//        intent.setDataAndType(uri, "audio/*");
        startActivityForResult(Intent.createChooser(intent, "Open"), 1);
//        String scheme = uri.getScheme();
        String[] title = {};
//        Cursor curs = this.getContentResolver().query(uri, null, null, null, null);
//        scheme = curs.getString();





//        Arrays.toString(song) = songUri.toString();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            songUri = data.getData();
            Cursor s = null;
            try{
                s = getContentResolver().query(songUri,new String[]{
                                MediaStore.Audio.Media.TITLE,
                                MediaStore.Audio.Media.ARTIST},
                        null,null,null);
                if(s != null && s.moveToFirst()){

                    sTitle = s.getString(0);
                    sArtist = s.getString(1);
                    String[] song1 = {sTitle + "-" + sArtist};
                    adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song1);
                    songView.setAdapter(adap);
//                    songView.setText(name + ", " + number);

                }
            }finally {
                if(s!=null)
                    s.close();
            }
        }

    }
}
