package com.example.alarmsysytem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.tv.BroadcastInfoResponse;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import android.content.BroadcastReceiver;

public class AlarmReciver extends BroadcastReceiver {
    public void onReceive(Context context,Intent intent){
        Toast.makeText(context,"Alarm wakeup",Toast.LENGTH_LONG).show();
        Uri alarmUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri==null){
            alarmUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context,alarmUri);
        ringtone.play();
    }
}
