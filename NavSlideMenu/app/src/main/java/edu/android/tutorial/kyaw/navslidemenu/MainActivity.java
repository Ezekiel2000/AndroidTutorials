package edu.android.tutorial.kyaw.navslidemenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.android.tutorial.kyaw.navslidemenu.adapter.NavDrawerListAdapater;
import edu.android.tutorial.kyaw.navslidemenu.fragments.CommunityFragment;
import edu.android.tutorial.kyaw.navslidemenu.fragments.FindPeopleFragment;
import edu.android.tutorial.kyaw.navslidemenu.fragments.HomeFragment;
import edu.android.tutorial.kyaw.navslidemenu.fragments.PagesFragment;
import edu.android.tutorial.kyaw.navslidemenu.fragments.PhotosFragment;
import edu.android.tutorial.kyaw.navslidemenu.fragments.WhatsHotFragment;
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

        charSequenceTitle = charSequenceDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_items_menu);
        navMenuIcon  = getResources().obtainTypedArray(R.array.nav_items_icon);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0],navMenuIcon.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcon.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcon.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcon.getResourceId(3, -1), true, "22"));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcon.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcon.getResourceId(5, -1), true, "50+"));

        navMenuIcon.recycle();

        listView.setOnItemClickListener(new SlideMnuListener());
        navDrawerListAdapater = new NavDrawerListAdapater(getApplicationContext(), navDrawerItems);
        listView.setAdapter(navDrawerListAdapater);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){
            public void onDrawerClosed(View view){
               getActionBar().setTitle(charSequenceTitle);invalidateOptionsMenu();
            }
            public void onDrawerOpened(View view){
                getActionBar().setTitle(charSequenceDrawerTitle);
               invalidateOptionsMenu();
            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        if(savedInstanceState == null){
            displayView(0);
        }

    }

    private class SlideMnuListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            displayView(i);

        }




    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new FindPeopleFragment();
                break;
            case 2:
                fragment = new PhotosFragment();
                break;
            case 3:
                fragment = new CommunityFragment();
                break;
            case 4:
                fragment = new PagesFragment();
                break;
            case 5:
                fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            listView.setItemChecked(position, true);
            listView.setSelection(position);
            setTitle(navMenuTitles[position]);
            drawerLayout.closeDrawer(listView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
