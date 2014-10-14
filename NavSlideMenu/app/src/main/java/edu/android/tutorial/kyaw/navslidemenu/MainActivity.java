package edu.android.tutorial.kyaw.navslidemenu;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import edu.android.tutorial.kyaw.navslidemenu.adapter.NavDrawerListAdapater;
import edu.android.tutorial.kyaw.navslidemenu.model.NavDrawerItem;


public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //drawer title
    private CharSequence charSequenceDrawerTitle;
    //app title
    private CharSequence charSequenceTitle;

    //
    private String [] navMenuTitles;
    private TypedArray navMenuIcon;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapater navDrawerListAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
