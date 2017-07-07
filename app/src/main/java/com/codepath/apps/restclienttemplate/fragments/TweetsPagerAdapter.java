package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nettawang on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private HomeTimelineFragment homeTimelineFragment;
    private MentionsTimelineFragment mentionsTimelineFragment;

    private String tabTiles[] = new String[] {"Home", "Mentions"};
    //return total number of fragments
    private Context context;
    public TweetsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }


    @Override
    public int getCount() {
        return 2;
    }

    //return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if (position == 0){

            return getHomeTimelineFragment();
        } else if (position == 1){
            return getMentionsTimelineFragment();
        } else {
            return null;
        }
    }

    public HomeTimelineFragment getHomeTimelineFragment(){
        if (homeTimelineFragment == null){
             homeTimelineFragment = new HomeTimelineFragment();
        }
        return homeTimelineFragment;
    }

    public MentionsTimelineFragment getMentionsTimelineFragment(){
        if (mentionsTimelineFragment == null){
            mentionsTimelineFragment = new MentionsTimelineFragment();
        }
        return mentionsTimelineFragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        //generate title based on item position
        return tabTiles[position];
    }



    //return title
}
