package com.cookandroid.the_son;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlramReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context,Activity_Loading.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);

        String channelName = "알람 채널";
        String description = "정해진 시간대에 알람을 보낸다";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel("default", channelName, importance);
        channel.setDescription(description);

        if(notificationManager != null){
            notificationManager.createNotificationChannel(channel);
        }

        if(notificationManager != null) {
            notificationManager.notify(1234, builder.build());

        }
    }
}
