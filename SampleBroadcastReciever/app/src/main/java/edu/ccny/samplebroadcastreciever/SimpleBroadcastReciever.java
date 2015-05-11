package edu.ccny.samplebroadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by kyawthan on 1/20/15.
 */
public class SimpleBroadcastReciever extends BroadcastReceiver {

    private String TAG = SimpleBroadcastReciever.class.getSimpleName().toUpperCase();
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i(TAG, "INTENT RECEIVED");



        Toast.makeText(context, "INTENT RECEIVED by Receiver", Toast.LENGTH_LONG).show();

    }
}
