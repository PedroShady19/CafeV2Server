package com.example.shady.cafev2server.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.shady.cafev2server.Common.Common;
import com.example.shady.cafev2server.Helper.NotificationHelper;
import com.example.shady.cafev2server.MainActivity;
import com.example.shady.cafev2server.OrderStatus;
import com.example.shady.cafev2server.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            sendNotificationAPI26(remoteMessage);
        else
            sendNotification(remoteMessage);
    }

    private void sendNotificationAPI26(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title=notification.getTitle();
        String content=notification.getBody();
        //Same as below
        Intent intent = new Intent(this, OrderStatus.class);
        intent.putExtra(Common.PHONE_TEXT, Common.currentUser.getPhone());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultUriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationHelper helper = new NotificationHelper(this);
        Notification.Builder builder = helper.getCafeV2ChannelNotification(title,content,pendingIntent,defaultUriSound);
        //Random ID for notification
        helper.getManager().notify(new Random().nextInt(),builder.build());
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultUriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultUriSound)
                .setContentIntent(pendingIntent);
        NotificationManager noti =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        noti.notify(0,builder.build());
    }
}
