package com.mirza.mab.thebrainganinapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayer extends AppCompatActivity {

    private View baseContext;
    PagerContainer mContainer;
    ViewPager pager;
    TextView level, stars;
    int levelNo = 1;
    public static DatabaseHandler dbHandler;
    int maxLevel = 1;
    int lock = 1;
    MessagePane pane;
    Typeface type;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_player);

        baseContext = findViewById(R.id.baseContext);
        level = (TextView) findViewById(R.id.textView2);
        stars = (TextView) findViewById(R.id.textView4);
        mContainer = (PagerContainer) findViewById(R.id.pager_container);

        pager = mContainer.getViewPager();
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        addViews();

        type = Typeface.createFromAsset(getAssets(), "round.ttf");

        pager.setClipChildren(true);
        pager.setClipToPadding(false);
        level.setTypeface(type);
        stars.setTypeface(type);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @SuppressLint("SetTextI18n")
            public void onPageSelected(int position) {
                levelNo = position + 1;
                level.setText("LEVEL " + (levelNo));
            }
        });

        dbHandler = new DatabaseHandler(this);
        dbHandler.addLevel(1, 0);
        maxLevel = dbHandler.getMaxLevel();
        stars.setText("" + dbHandler.getTotalScore());
        pager.setCurrentItem(maxLevel - 1);
    }

    @Override
    protected void onResume() {
        if (Flags.refreshFlag) {
            Flags.refreshFlag = false;
            Intent intent = new Intent(this, SinglePlayer.class);
            this.finish();
            startActivity(intent);
        }
        super.onResume();
    }

    public void addViews() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());

    }

    public void play(View v) {
        Intent intent;
        switch (levelNo) {

            case 1:
                intent = new Intent(this, Level_1.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, Level_2.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, Level_3.class);
                startActivity(intent);
                break;
            case 4:
                Toast.makeText(getBaseContext(), "level4", Toast.LENGTH_LONG).show();
                break;
        }


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
            float stars = dbHandler.getScore(pos + 1);
            switch (pos) {
                case 0:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l1", 1, lock, stars);
                case 1:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l2", 2, lock, stars);
                case 2:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l3", 3, lock, stars);
                case 3:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l1", 4, lock, stars);
                case 4:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l2", 5, lock, stars);
                default:
                    if ((pos + 1) <= maxLevel) {
                        lock = 0;
                    } else {
                        lock = 1;
                    }
                    return Levels.newInstance("l3", 6, lock, stars);
            }
        }


    }

    public void levelLockedMsg(View v) {
        pane = new MessagePane(SinglePlayer.this, "Level Locked!", "You don't have enough stars to play this level");
        pane.show();
    }


}
