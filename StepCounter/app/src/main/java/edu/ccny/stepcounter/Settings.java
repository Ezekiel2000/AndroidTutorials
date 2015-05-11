package edu.ccny.stepcounter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kyawthan on 1/21/15.
 */
public class Settings {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public Settings(Context context) {
        sharedPreferences = context.getSharedPreferences("com.nexHealth.settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setCurrentStep(int step){editor.putInt("steps",step).commit();}
    public int getCurrentStep(){return sharedPreferences.getInt("steps", 0);}
}
