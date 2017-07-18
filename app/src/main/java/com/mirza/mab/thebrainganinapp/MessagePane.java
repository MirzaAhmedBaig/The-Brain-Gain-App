package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AHMED on 10-07-2017.
 */

public class MessagePane extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button ok;
    public TextView msg,head;
    private String message, title;
    Typeface type;

    public MessagePane(Activity a,String title, String msg) {
        super(a);
        this.title=title;
        this.message=msg;
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.messagepane);
        head = (TextView) findViewById(R.id.title);
        msg = (TextView) findViewById(R.id.message);
        ok = (Button) findViewById(R.id.btn_ok);
        type = Typeface.createFromAsset(c.getAssets(), "round.ttf");
        head.setText(title);
        msg.setText(message);
        head.setTypeface(type);
        msg.setTypeface(type);
        ok.setTypeface(type);

        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
