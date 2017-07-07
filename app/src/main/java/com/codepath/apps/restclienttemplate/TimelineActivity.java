package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TimelineActivity extends AppCompatActivity {

    private Tweet newTweet;
    private TweetsPagerAdapter tweetsPagerAdapter;
    private ViewPager vpPager;
    private HomeTimelineFragment homeTimelineFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), this);

        //get the view pager
         vpPager = (ViewPager) findViewById(R.id.viewpager);
        //set the adapter for the pager
        vpPager.setAdapter(tweetsPagerAdapter);
        // set up the tablayout to use the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
    }

    public int RESULT_CODE = 50;
    public int REQUEST_CODE = 20;
    public void onCompose(MenuItem menuItem){
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_CODE && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            newTweet = (Tweet) Parcels.unwrap(intent.getParcelableExtra("tweet"));

            homeTimelineFragment = (HomeTimelineFragment) tweetsPagerAdapter.getItem(0);

            vpPager.setCurrentItem(0);

            homeTimelineFragment.addComposedTweets(newTweet);
            // Toast the name to display temporarily on screen
            //Toast.makeText(this, newTweet, Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onProfileView(MenuItem item) {
        //launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}
