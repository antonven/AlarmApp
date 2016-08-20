package myapps.wycoco.com.alarmapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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
import java.util.Calendar;

public class SetAlarm extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnDate;
    int years, month, day;
    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Intent intent = getIntent();
        String[] song = {"Choose a song"};

        ListAdapter adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song);
        ListView songView = (ListView)findViewById(R.id.songView);
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
        btnStop.setOnClickListener(this);
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

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 234324243, intent, 0);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "/Audio/");
        intent.setDataAndType(uri, "*/*");

        startActivity(Intent.createChooser(intent, "Open"));

    }

}
