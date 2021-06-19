package com.amar.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ToggleButton;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class AlarmService extends Service {
    private NotificationManager alarmNotificationManager;
    private PendingIntent pendingIntent;
    private static BroadcastReceiver receiver;
    AlarmManager alarmManager;
    Intent intent;
    Calendar calendar;
    public  static final String SELLCOMPANYNAME  = "sellcompanyname";
    public  static final String  SELLADDRESS = "first";

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {

        super.onCreate();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intent = new Intent();
        Bundle bundle =intent.getExtras();

        Log.d("MyActivity", "Alarm On");
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,bundle.getInt(SELLCOMPANYNAME));
        calendar.set(Calendar.MINUTE, bundle.getInt(SELLADDRESS));


        registerScreenOffReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        unregisterReceiver(receiver);
        receiver = null;


        Log.d("MyActivity", "Alarm Off");
        super.onDestroy();
    }
    private void registerScreenOffReceiver()
    {
        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                // do something, e.g. send Intent to main app
            }
        };

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(AlarmService.this, AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(AlarmService.this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

    }
}
