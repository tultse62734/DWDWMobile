package com.example.dwdwproject;

import com.example.dwdwproject.activities.SignUpActivity;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.util.Random;
import java.util.UUID;

public class FirebaseMessageReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Handle when receive notification through data event
        if(remoteMessage.getData().size() > 0){
            showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
        }
        //Handle when receive notification
        if(remoteMessage.getNotification() != null){
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }
    //Cách hiển thị Notification
    public void showNotification(String title, String message){
        //Khi ấn vào notify sẽ gửi trả về Intent này
        Intent intent = new Intent(this, SignUpActivity.class);
        String channel_id = "web_app_channel";
        //Clearing previous activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channel_id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);
        builder = builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);
        //NotificationManager để hiển thị ra
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,"web_app",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Random rd = new Random();
        UUID idVariable = UUID.randomUUID();
        String idText = "" + idVariable;
        int uid = idText.hashCode();
        String filter = "" + uid;
        idText = filter.replaceAll("-","");
        int id = Integer.parseInt(idText);
        notificationManager.notify(id,builder.build());

    }
}