package com.amar.alarm;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.amar.alarm.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;
    Button offs,on,off;
    public  static final String SELLCOMPANYNAME  = "1";
    public  static final String  SELLADDRESS = "first";
    public  static final String  SELLCONTACTNUMBER = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
      //  ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        offs = (Button) findViewById(R.id.buttonoff);
        off = (Button) findViewById(R.id.off);
        on = (Button) findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle1 = new Bundle();
                bundle1.putInt(SELLCOMPANYNAME,alarmTimePicker.getCurrentHour());
                bundle1.putInt(SELLADDRESS,alarmTimePicker.getCurrentMinute());

                Intent intent = new Intent(MainActivity.this,AlarmService.class);
                intent.putExtras(bundle1);
                startService(intent);

            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopService(new Intent(MainActivity.this,AlarmService.class));

            }
        });
    }
    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}