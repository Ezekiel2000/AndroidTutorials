package edu.android.tutorial.kyaw.navslidemenu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.android.tutorial.kyaw.navslidemenu.R;
import edu.android.tutorial.kyaw.navslidemenu.model.NavDrawerItem;

/**
 * Created by kyawthan on 10/13/14.
 */
public class NavDrawerListAdapater extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapater(Context context, ArrayList<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view ==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView textViewTitle = (TextView) view.findViewById(R.id.title);
        TextView textViewCount = (TextView) view.findViewById(R.id.counter);

        imageView.setImageResource(navDrawerItems.get(i).getIcon());
        textViewTitle.setText(navDrawerItems.get(i).getTitle());
        if(navDrawerItems.get(i).isCounterVisible()){
            textViewCount.setText(navDrawerItems.get(i).getCount());
        }else {
            textViewCount.setVisibility(View.GONE);
        }
        return view;

    }
}
