package edu.ccny.stepcounter;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * Created by kyawthan on 1/21/15.
 */
public class StepCounterService extends Service implements SensorEventListener {

    private static final String TAG = "STEPCOUNTERLISTENER";
    private final int NOTIFICATION = R.string.service_running;

    private SensorManager sensorManager;
    private Context context;
    private Settings settings;

    private static int smallStep;
    private static int bigStep;
    private static int tmpStep;
    private long lastTime;

    public StepCounterService(Context context){
        this.context = context;
        settings = new Settings(context);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        clientHandler = null;
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            //SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sensorManager.unregisterListener(this);
        } catch (Exception e) {

            e.printStackTrace();
        }
        settings.setCurrentStep(smallStep);
        smallStep = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER){

            return;
        }
        float x = event.values[0]; float y = event.values[1]; float z = event.values[2];

        float accelarationSqRoot = ((x * x + y * y + z * z)
                /(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)) - 1.0f;
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTime > 300){
            if (accelarationSqRoot < - 0.45f){
                smallStep++;
                tmpStep++;
                if (accelarationSqRoot < - 1.0f){
                    bigStep++;
                }
                if (smallStep%50 == 0){
                    smallStep++;
                }
                if (tmpStep == 4){
                    smallStep = 0;
                }
                lastTime = currentTime;

            }
        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (smallStep == 0) {
            smallStep = settings.getCurrentStep();
        }
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        showNotification();
        serviceHandler.post(UpdateSteps);

        return START_STICKY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Handler clientHandler;

    private final IBinder mBinder = new LocalBinder();
    private final Handler serviceHandler = new Handler();
    private final Runnable UpdateSteps = new Runnable() {
        @Override
        public void run() {
            // Log.i(TAG, "UpdateSteps is running.");

            if (clientHandler != null) {
                Log.i(TAG, "Update steps.");
                Message message = Message.obtain();
                message.arg1 = smallStep;
                message.arg2 = bigStep;
                clientHandler.sendMessage(message);
            }
            serviceHandler.postDelayed(this, 1000);
        }
    };

    public class LocalBinder extends Binder {
        public void gimmeHandler(Handler handler) {
            clientHandler = handler;
        }
    }

    private void showNotification() {
        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.ic_notification, null, System.currentTimeMillis());

        // The PendingIntent to launch the activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        // Set the information for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.app_name), getText(R.string.service_running),
                contentIntent);

        startForeground(NOTIFICATION, notification);
    }

}
