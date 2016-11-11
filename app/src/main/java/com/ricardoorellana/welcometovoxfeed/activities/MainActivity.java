package com.ricardoorellana.welcometovoxfeed.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ricardoorellana.welcometovoxfeed.R;
import com.ricardoorellana.welcometovoxfeed.fragments.Publications;
import com.ricardoorellana.welcometovoxfeed.fragments.Welcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for loading tab layouts
 *
 * This activity is used to display different layout inside of tabs.
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Holds and sets toolbar element
     */
    private Toolbar toolbar;

    /**
     * Holds a reference of the tabLayout element.
     */
    private TabLayout tabLayout;

    /**
     * Holds a reference of viewPager content.
     */
    private ViewPager viewPager;

    /**
     * Initializes activity
     * @param savedInstanceState if the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied in
     * onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Makes initial configuration for the view pager object
     * @param viewPager Gets a view pager object to be set adapter
     */
    private void setupViewPager(ViewPager viewPager) {
        String tabStartLabel = getResources().getString(R.string.tab_start).toUpperCase();
        String tabPublicationsLabel = getResources().getString(R.string.tab_publications).toUpperCase();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Welcome(), tabStartLabel);
        adapter.addFragment(new Publications(), tabPublicationsLabel);
        viewPager.setAdapter(adapter);
    }

    /**
     * Defines options to loaded in menu
     * @param menu Reference of menu to be inflated
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Inner class that defines custom adapter to load fragments content
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
