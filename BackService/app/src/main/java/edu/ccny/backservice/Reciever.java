package edu.ccny.backservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kyawthan on 1/20/15.
 */
public class Reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentU = new Intent(context, RepeatService.class);
        context.startService(intentU);
        Log.i("Reciever", "Service from broatcast");
    }
}
