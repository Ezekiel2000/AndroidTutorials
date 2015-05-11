package edu.ccny.testingfadingactionbar;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.loopj.android.image.SmartImageView;
import com.manuelpeinado.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.header, null);
        SmartImageView smartImageView = (SmartImageView) view.findViewById(R.id.image_header);
        if (smartImageView != null){
            Log.i("Smart", "is Not Null");
            smartImageView.setImageUrl("http://www.picturesnew.com/media/images/e4454c8df9.png");
        }




        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_main);
        View view1 = helper.createView(this);
        SmartImageView imageView = (SmartImageView) view1.findViewById(R.id.image_header);
        imageView.setImageUrl("http://s3.amazonaws.com/nexhealth-images/images/647/medium/JPEG_20150120_163707_-1274102091.jpg?1421789857");

        setContentView(view1);
        helper.initActionBar(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
