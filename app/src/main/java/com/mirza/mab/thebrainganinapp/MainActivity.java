package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private View baseContext;
    private Button splay, mplay, settings, share, info, like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseContext = findViewById(R.id.baseContext);
        onViewInitialized();
    }

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

    public void onViewInitialized() {
        splay = (Button) findViewById(R.id.button);
        mplay = (Button) findViewById(R.id.button2);
        settings = (Button) findViewById(R.id.button3);
    }

    public void singlePlay(View v){
        Intent intent =new Intent(this,SinglePlayer.class);
        startActivity(intent);

    }

    public void multiPlay(View v){

    }

    public void settings(View v){
//        Intent intent=new Intent(this,MultiPlayer.class);
//        startActivity(intent);

    }

    public void like(View v){

    }

    public void share(View v){

    }

    public void info(View v) {

    }

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
}
