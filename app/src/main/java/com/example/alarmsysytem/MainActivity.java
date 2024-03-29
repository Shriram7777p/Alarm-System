package com.example.alarmsysytem;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=(TimePicker) findViewById(R.id.Timepick);
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
    }
    public void OnToogle(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(getApplicationContext(), "Alarm On", Toast.LENGTH_LONG).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
            Intent intent = new Intent(this, AlarmReciver.class);
            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this,"Alarm Off",Toast.LENGTH_LONG).show();
        }
    }
}