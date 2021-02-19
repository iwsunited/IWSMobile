package com.iws.mobile.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iws.mobile.R;
import com.iws.mobile.activity.MainActivity;

import static com.iws.mobile.notification.OreoNotification.CHANNEL_ID;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    private static final String TAG = "ganteng";
    
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: jalan");
        if (remoteMessage != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                sendOreoNotif(remoteMessage);
            } else {
                sendNotif(remoteMessage);
            }
        }
    }

    private void sendNotif(RemoteMessage remoteMessage){
        Log.d(TAG, "sendNotif: jalan");
        ///////////////////////////////////////////////////////////////////////////////////////////
        String idNotif = remoteMessage.getData().get("id_notification");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        //untuk ngasih variabel ke main activity dan menentukan tujuan clicknya notif
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id_notification", idNotif);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //untuk membedakan jenis notifnya untuk sekarang 111 untuk ke main activity
        String requestCode = "111" + idNotif;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, Integer.parseInt(requestCode), intent, PendingIntent.FLAG_ONE_SHOT);
        ///////////////////////////////////////////////////////////////////////////////////////////

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Integer.parseInt(requestCode), builder.build());
    }

    private void sendOreoNotif(RemoteMessage remoteMessage){
        Log.d(TAG, "sendOreoNotif: jalan");
        ///////////////////////////////////////////////////////////////////////////////////////////
        String idNotif = remoteMessage.getData().get("id_notification");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        //untuk ngasih variabel ke main activity dan menentukan tujuan clicknya notif
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id_notification", idNotif);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //untuk membedakan jenis notifnya untuk sekarang 111 untuk ke main activity
        String requestCode = "111" + idNotif;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, Integer.parseInt(requestCode), intent, PendingIntent.FLAG_ONE_SHOT);
        ///////////////////////////////////////////////////////////////////////////////////////////

        OreoNotification oreoNotification = new OreoNotification(this);
        oreoNotification.createChannel();

        oreoNotification.getManager().notify(Integer.parseInt(requestCode), getOreoNotification(title, body, pendingIntent).build());
    }

    @TargetApi(Build.VERSION_CODES.O)
    private Notification.Builder getOreoNotification(String title, String body, PendingIntent pendingIntent){
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);
    }

//    private void sendNotifaha(String messageBody){
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("FCM Message")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notificationBuilder.build());
//    }
}
