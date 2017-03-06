package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import edu.washington.ischool.commoncents.commoncents.Fragments.FriendsListFragment;
import edu.washington.ischool.commoncents.commoncents.Fragments.SplitCostsFragment;
import edu.washington.ischool.commoncents.commoncents.SettingsActivity;

/**
 * Created by iguest on 3/3/17.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public static final String TAG = "MainPagerAdapter";
    int numOfTabs;

    public MainPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.v(TAG, "switching to tab #" + position);

        switch (position) {
            case 0: {
                SplitCostsFragment tab = new SplitCostsFragment();
                return tab;
            }
            case 1: {
                FriendsListFragment tab = new FriendsListFragment();
                return tab;
            }
            case 2: {
                // TODO make SettingsActivity a fragment and return it here
                FriendsListFragment tab = new FriendsListFragment();
                return tab;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}