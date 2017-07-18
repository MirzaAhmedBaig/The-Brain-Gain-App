package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private View baseContext;
    private Button splay, mplay, settings, share, info, like;
    private TextView heading, subHeading;
    private Space space;
    public static Typeface type;
    private Info inf;
    private DatabaseHandler dbHandler = SinglePlayer.dbHandler;
    int width;
    int height;

    DisplayMetrics displayMetrics;

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
        baseContext=findViewById(R.id.baseContext);
        heading = (TextView) findViewById(R.id.textView);
        subHeading = (TextView) findViewById(R.id.textView3);
        splay = (Button) findViewById(R.id.button);
        mplay = (Button) findViewById(R.id.button2);
        settings = (Button) findViewById(R.id.button3);
        type = Typeface.createFromAsset(getAssets(), "round.ttf");
        heading.setTypeface(type);
        subHeading.setTypeface(type);
        splay.setTypeface(type);
        mplay.setTypeface(type);
        settings.setTypeface(type);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
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
        String shareBodyText = "I love The Brain Twist App...! \n This is the best game i ever played.\nhttps://drive.google.com/open?id=0By5B0_-J_O5tWTY3NkFKVkx5ZHM";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "The Brain Gain App");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

}
