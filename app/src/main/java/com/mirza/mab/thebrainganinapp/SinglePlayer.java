package com.mirza.mab.thebrainganinapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
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
    TextView level,stars;
    int levelNo = 1;
    public static DatabaseHandler dbHandler;
    int maxLevel=1;
    int lock=1;
    MessagePane pane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        baseContext = findViewById(R.id.baseContext);
        level = (TextView) findViewById(R.id.textView2);
        stars=(TextView)findViewById(R.id.textView4);
        mContainer = (PagerContainer) findViewById(R.id.pager_container);

        pager = mContainer.getViewPager();
        pager.setPageTransformer(true, new ZoomOutPageTransformer());

        PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());

        pager.setClipChildren(true);
        pager.setClipToPadding(false);

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

        dbHandler=new DatabaseHandler(this);
        dbHandler.addLevel(1,0);
        maxLevel=dbHandler.getMaxLevel();
        stars.setText("Stars: "+dbHandler.getScore(1));

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
        );

    }

    @SuppressLint("MissingSuperCall")
    public void refresh(){
        this.recreate();
    }

    @Override
    public void onResume(){
        super.onResume();
//        this.recreate();
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
        );
        return super.onTouchEvent(event);
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
                Toast.makeText(getBaseContext(), "level3", Toast.LENGTH_LONG).show();
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
            float stars=dbHandler.getScore(pos+1);
            switch (pos) {
                case 0:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("l1", 1,lock,stars);
                case 1:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("flas2", 2,lock,stars);
                case 2:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("flash1", 3,lock,stars);
                case 3:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("flas2", 4,lock,stars);
                case 4:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("flash1", 5,lock,stars);
                default:
                    if((pos+1)<=maxLevel){
                        lock=0;
                    }else {
                        lock=1;
                    }
                    return Levels.newInstance("flas2", 6,lock,stars);
            }
        }


    }

    public void levelLockedMsg(View v){
        pane = new MessagePane(SinglePlayer.this,"Level Locked!","You don't have enough stars to play this level");
        pane.show();
    }


}
