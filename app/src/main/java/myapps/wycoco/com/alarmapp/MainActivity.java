package myapps.wycoco.com.alarmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long date = System.currentTimeMillis();
        TextClock textClock = (TextClock)findViewById(R.id.textClock);
        Button btnSet = (Button)findViewById(R.id.btnSet);
        TextView txtDate = (TextView)findViewById(R.id.txtDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, EEE");
        String dateString = sdf.format(date);
        txtDate.setText(dateString);

        btnSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SetAlarm.class);
        startActivity(i);
    }
}
