package com.belivnat.tasks.modules.scheduler.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.belivnat.tasks.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    public void showNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), "TASK")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Scheduled Notification")
                .setContentText("You Have Set Notification to Receive Now")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You Have Set Notification to Receive Now"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (notificationManager != null) {
            String channelId = "TASK";
            NotificationChannel channel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel(
                        channelId,
                        "Task Notification",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
            notificationManager.notify(1, builder.build());
        }
    }
}
