package edu.android.tutorial.kyaw.notificationbarwithalarm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by kyawthan on 10/13/14.
 */
public class TimeAlarm extends BroadcastReceiver {

    NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {


//        Intent intentAddMeal = new Intent(ActivityMain.this, AddMealPicActivity.class);
//        pIntent = PendingIntent.getActivity(this, 0, intentAddMeal, 0);
//        Intent intentAddGlucose = new Intent(ActivityMain.this, AddGlucoseActivity.class);
//        gIntent = PendingIntent.getActivity(this, 0, intentAddGlucose, 0);
//
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setAutoCancel(false);
//        builder.setContentTitle("MealTime");
//        builder.setContentText("Say something here");
//        builder.setSmallIcon(R.drawable.icon_refresh);
//        builder.addAction(R.drawable.ic_action_camera, "Take A Snap", pIntent);
//        builder.addAction(R.drawable.add_glucose_icon_notification, "Add Glucose", gIntent);
//        Notification notification = builder.setOngoing(true).build();
//        manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(8, notification);

        Intent intentTmp = new Intent(context, HelloWorld.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentTmp, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(false);
        builder.setContentTitle("Hello World");
        builder.setContentText("This is Notification Example using alarm Servcie");
        builder.setSmallIcon(R.drawable.ic_launcher);

        Notification notification = builder.setOngoing(false).build();





        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(8,notification );

    }
}
