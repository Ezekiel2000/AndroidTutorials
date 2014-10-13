package edu.android.tutorial.kyaw.notificationbarwithalarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by kyawthan on 10/13/14.
 */
public class MyServices extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
