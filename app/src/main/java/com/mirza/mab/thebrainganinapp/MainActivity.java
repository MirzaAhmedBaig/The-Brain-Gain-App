package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private View baseContext;
    private Button splay, mplay, settings, share, info, like;
    private TextView heading, subHeading;
    public static Typeface type;
    private Info inf;
    private DatabaseHandler dbHandler = SinglePlayer.dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        baseContext = findViewById(R.id.baseContext);
        onViewInitialized();
    }

    public void onViewInitialized() {
        heading = (TextView) findViewById(R.id.textView);
        subHeading = (TextView) findViewById(R.id.textView3);
        splay = (Button) findViewById(R.id.button);
        mplay = (Button) findViewById(R.id.button2);
        settings = (Button) findViewById(R.id.button3);
        type = Typeface.createFromAsset(getAssets(), "round.ttf");
        heading.setTypeface(type);
        subHeading.setTypeface(type);
    }

    public void singlePlay(View v) {
        Intent intent = new Intent(this, SinglePlayer.class);
        startActivity(intent);

    }

    public void multiPlay(View v) {

    }

    public void settings(View v) {
//        Intent intent=new Intent(this,MultiPlayer.class);
//        startActivity(intent);

    }

    public void like(View v) {

    }

    public void info(View v) {
        inf=new Info(this);
        inf.show();

    }

    public void share(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "I love The Brain Gain App...! \n This is the best game i ever played.\nhttp://insecgps.tk/bgapp.apk";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "The Brain Gain App");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

}
