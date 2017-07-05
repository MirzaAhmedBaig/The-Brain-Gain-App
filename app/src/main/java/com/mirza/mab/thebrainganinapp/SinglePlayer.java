package com.mirza.mab.thebrainganinapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayer extends AppCompatActivity {

    private View baseContext;
    PagerContainer mContainer;
    ViewPager pager;
    TextView level;
    int maxLevel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        baseContext = findViewById(R.id.baseContext);
        level = (TextView) findViewById(R.id.textView2);
        mContainer = (PagerContainer) findViewById(R.id.pager_container);

        pager = mContainer.getViewPager();
        pager.setPageTransformer(true, new ZoomOutPageTransformer());

        PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setPageMargin(60);

        pager.setClipChildren(true);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @SuppressLint("SetTextI18n")
            public void onPageSelected(int position) {
                level.setText("Level " + (position + 1));
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        baseContext.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        baseContext.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        return super.onTouchEvent(event);
    }

    public void play(View v) {
        Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_LONG).show();
    }

    public void next(View v) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }

    public void previous(View v) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 6;
        }

        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return Levels.newInstance("flash1", 1);
                case 1:
                    return Levels.newInstance("flas2", 2);
                case 2:
                    return Levels.newInstance("flash1", 3);
                case 3:
                    return Levels.newInstance("flas2", 4);
                case 4:
                    return Levels.newInstance("flash1", 5);
                default:
                    return Levels.newInstance("flas2", 6);
            }
        }


    }


}
