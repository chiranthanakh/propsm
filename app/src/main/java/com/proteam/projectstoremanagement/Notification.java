package com.proteam.projectstoremanagement;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.proteam.projectstoremanagement.Activities.LoginActivity;
import com.proteam.projectstoremanagement.Activities.MainActivity;
import com.proteam.projectstoremanagement.Activities.PendingIndentActivity;
import com.proteam.projectstoremanagement.Activities.PendingIndentListActivity;
import com.proteam.projectstoremanagement.Utils.SqlDb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

public class Notification extends FirebaseMessagingService {

    SqlDb sqlDb = new SqlDb(this);

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String,String> data = remoteMessage.getData();
        String text = data.get("key1");

       // sqlDb.insertData(remoteMessage.getNotification().getBody(),"Title","02/2/2022");
       // getFirebasemessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        generateNotification(this,remoteMessage.getNotification().getBody());

        if(remoteMessage.getNotification().getBody().equalsIgnoreCase("")){

        }else {

        }
    }

    @Override
    public void handleIntent(@NonNull Intent intent) {
        super.handleIntent(intent);

        String msg = (String) intent.getExtras().get("body1");
        String title = (String) intent.getExtras().get("title1");
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd. yyy");
        String saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");

        sqlDb.insertData(msg,title,saveCurrentDate);

    }

    private void generateNotification(Context context, String msg) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "channel-fbase";
        String channelName = "demoFbase";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(getApplicationContext(), PendingIndentListActivity.class);
        notificationIntent.putExtra("click_action", "CLICK_ACTION");
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            int color = 0x008000;
            mBuilder.setColor(color);
        } else {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        }
        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));

        mBuilder.setContentTitle("Pro PIMS");
        mBuilder.setContentText(msg);
        mBuilder.setContentIntent(pendingIntent);

        //If you don't want all notifications to overwrite add int m to unique value
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(m, mBuilder.build());
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
