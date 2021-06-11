package com.nx.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intiView();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i2) {
            }
            @Override
            public void onPageSelected(final int i) {
                if(i==0)
                {
                    PostFragment fragment = (PostFragment) viewPagerAdapter.instantiateItem(viewPager, i);
                    if (fragment != null) {
                        fragment.fragmentBecameVisible();
                    }

                }else {
                    FavFragment fragment = (FavFragment) viewPagerAdapter.instantiateItem(viewPager, i);
                    if (fragment != null) {
                        fragment.fragmentBecameVisible();
                    }
                }

            }
            @Override
            public void onPageScrollStateChanged(final int i) {
            }
        });
    }

    private void intiView() {
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new PostFragment();
            } else if (position == 1) {
                fragment = new FavFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = "POST";
            } else if (position == 1) {
                title = "FAV POST";
            }

            return title;
        }
    }
}