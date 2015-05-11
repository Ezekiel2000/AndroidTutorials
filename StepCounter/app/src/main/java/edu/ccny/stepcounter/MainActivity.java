package edu.ccny.stepcounter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {


    private static float ACTIVITY_COEFFICIENT = 1.55F;



    private Timer timer;
    private int second;
    private int minute;
    private int hour;
    //Buttons
    private Button buttonStep;
    private Intent serviceIntent;
    //Calories
    private static int calories;
    //Steps and Speed
    private int speed;
    private static int steps;
    //Some parameters for calculating calories
    private static int gender;
    private static int age;
    private static int height;
    private static int weight;

    private String TAG = MainActivity.class.getSimpleName().toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStep = (Button) findViewById(R.id.step);

        serviceIntent = new Intent(MainActivity.this, StepCounterService.class);
        Log.e(TAG, "started service");
        startService(serviceIntent);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        steps = 0;
        calories = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder binder) {
            ((StepCounterService.LocalBinder) binder).gimmeHandler(updateDisplay);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // do nothing
        }
    };
    //Message hander
    private final Handler updateDisplay = new Handler() {
        @Override
        public void handleMessage(Message message) {
            steps = message.arg1;
            int bigSteps = message.arg2;
            if (steps > 0) {
                Log.e(getClass().getSimpleName().toUpperCase(), String.valueOf(steps));
                buttonStep.setText(String.valueOf(steps));
            }

            //Change mode walking or running
            if(bigSteps >= (int)(steps/2)){
                ACTIVITY_COEFFICIENT = 1.725f;
            }else{
                ACTIVITY_COEFFICIENT = 1.55f;
            }
        }
    };

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {

                    second++;
                    if(second == 60){
                        second=0;
                        minute++;
                    }
                    if(minute == 60){
                        minute=0;
                        hour++;
                    }
                    if(minute%10==0&&second==0){
                        //writeXML();
                    }else{
                        //do nothing
                    }

                    //calculate speed (time/steps) = speed (by minutes)
                    if(steps > 0){
                        speed = (int)((((hour/3600)+(minute/60)+second)/steps)*60);
                    }


                    //Women
                    if(gender == 0){
                        calories += (int)(((((655+(9.6*weight)+(1.8*height)-(4.7*age))*ACTIVITY_COEFFICIENT)/24)/3600)
                                *(second));
                    }//men
                    else{
                        calories += (int)(((((66+(13.7*weight)+(5*height)-(6.8*age))*ACTIVITY_COEFFICIENT)/24)/3600)
                                *(second));
                    }

//
//                    //Display
//                    CurrentSpeed.setText(String.valueOf(speed));
//
//                    CurrentCalories.setText(String.valueOf(calories));
//
//                    CurrentTime.setText(String.valueOf(hour)+" : "+
//                            String.valueOf(minute)+" : "+
//                            String.valueOf(second));

                }
            });
        }
    };

}
