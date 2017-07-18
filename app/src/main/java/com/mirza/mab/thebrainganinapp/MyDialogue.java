package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyDialogue extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no,restart;
    TextView title;
    Typeface type;

    public MyDialogue(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sample_my_dialogue);
        title=(TextView)findViewById(R.id.txt_dia);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        restart= (Button) findViewById(R.id.btn_restart);
        type = Typeface.createFromAsset(c.getAssets(), "round.ttf");

        title.setTypeface(type);
        yes.setTypeface(type);
        no.setTypeface(type);
        restart.setTypeface(type);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        restart.setOnClickListener(this);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Flags.paused=false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                Flags.paused=false;
                break;
            case R.id.btn_restart:
                c.recreate();
                break;
            default:
                break;
        }
        dismiss();
        Flags.paused=false;
    }
}