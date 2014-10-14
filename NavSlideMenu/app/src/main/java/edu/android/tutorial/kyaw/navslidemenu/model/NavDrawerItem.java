package edu.android.tutorial.kyaw.navslidemenu.model;

/**
 * Created by kyawthan on 10/13/14.
 */
public class NavDrawerItem {

    private String title;
    private int icon;
    private String count = "0";
    private boolean isCounterVisible = false;

    public NavDrawerItem(){}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setCounterVisible(boolean isCounterVisible) {
        this.isCounterVisible = isCounterVisible;
    }

    public int getIcon() {

        return icon;
    }

    public String getCount() {
        return count;
    }

    public boolean isCounterVisible() {
        return isCounterVisible;
    }

    public String getTitle() {

        return title;
    }

    public NavDrawerItem(String title, int icon){
        this.title = title;

        this.icon = icon;

    }

    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count){
        this.title = title;
        this.icon = icon;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }
}
