package com.belivnat.tasks.modules.scheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.belivnat.tasks.R;
import com.belivnat.tasks.modules.scheduler.view.AlarmReceiver;

import org.w3c.dom.Text;

import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SchedulerActivity extends AppCompatActivity {
    Button btnSetScheduler;
    TimePicker timePicker;
    TextView txtTimePicked;
    String format;
    AlarmManager alarmManager;
    PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        timePicker = findViewById(R.id.tp_time_picker);
        txtTimePicked = findViewById(R.id.txt_time_picked);
        btnSetScheduler = findViewById(R.id.btn_set_scheduler);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                showTime(hour, minute);
            }
        });
        btnSetScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlert(timePicker.getHour(), timePicker.getMinute(), false);
            }
        });
    }

    private void setAlert(int hour, int minute, boolean isRepeat) {
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
        Log.d("User Picked Time :", calendar.getTime().toString());
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                60000, alarmIntent);
    }

    public void showTime(int hour, int minute) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        txtTimePicked.setText(new StringBuilder().append("Time Picked: ")
                .append(hour)
                .append(" : ")
                .append(minute)
                .append(" ")
                .append(format));
    }
}
