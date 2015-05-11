package edu.ccny.backservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kyawthan on 1/20/15.
 */
public class RepeatService extends IntentService {

    private String TAG = RepeatService.class.getSimpleName().toUpperCase();


    public RepeatService(){
        super("RepeatService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "Recieved Service");

    }
}
