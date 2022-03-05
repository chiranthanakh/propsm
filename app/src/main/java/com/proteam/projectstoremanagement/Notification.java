package com.proteam.projectstoremanagement;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notification extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        getFirebasemessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }

    public  void getFirebasemessage(String title, String msg){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myFirebasechannel")
                .setSmallIcon(R.drawable.ic_circle_primary)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(101,builder.build());


    }

}
