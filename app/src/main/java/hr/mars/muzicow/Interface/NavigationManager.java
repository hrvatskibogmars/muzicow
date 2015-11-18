package hr.mars.muzicow.Interface;

import android.support.v4.app.Fragment;

/**
 * Created by mars on 17/11/15.
 */
/**
 * TODO
 * Prebaciti fragmente u interface
 */
public interface NavigationManager {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();

}
