package com.mirza.mab.thebrainganinapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Level_1 extends AppCompatActivity {

    private View baseContext;
    private GridView gridview;
    private View frame1, frame2, frame3, fram4;
    private int oncePosition = 0;
    private int playing = 0;
    MyDialogue dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_1);
        nViewInitialized();

        gridview.setAdapter(new ImageAdapter(this));


    }

    public void nViewInitialized() {
        baseContext = findViewById(R.id.baseContext);
        frame1 = findViewById(R.id.frame1);
        frame2 = findViewById(R.id.frame2);
        gridview = (GridView) findViewById(R.id.gridview);
        double pos = Math.random() * 22;
        oncePosition = (int) pos;
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
        );
    }

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

    @Override
    public void onBackPressed() {
        if (playing == 1) {
            dialogue = new MyDialogue(Level_1.this);
            dialogue.show();
        }
    }

    public void round1(View v) {
        frame1.setVisibility(View.GONE);
        playing = 1;
        frame2.setVisibility(View.VISIBLE);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 84;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, final ViewGroup parent) {
            final Button button;
            if (convertView == null) {
                button = new Button(mContext);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
            } else {
                button = (Button) convertView;
            }

            if (position == oncePosition) {
                button.setText("1");
            } else {
                button.setText("7");
            }
            button.setTextColor(Color.BLACK);
            int resID = getResources().getIdentifier("button", "drawable", "com.mirza.mab.thebrainganinapp");
            button.setBackgroundResource(resID);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(Level_1.this, button.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            });
            return button;
        }

    }


}
