package com.mirza.mab.thebrainganinapp;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AHMED on 10-07-2017.
 */

public class Info extends Dialog {

    public Activity c;
    public Dialog d;
    public Button ok;
    public TextView msg,link,title,li;
    Typeface type;

    public Info(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
        title = (TextView) findViewById(R.id.title);
        msg = (TextView) findViewById(R.id.msg);
        li = (TextView) findViewById(R.id.li);
        link = (TextView) findViewById(R.id.link);

        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setLinkTextColor(Color.rgb(237,138,25));
        String text = "<a href='http://www.mirzaahmed.tk'>www.mirzaahmed.tk</a>";
        link.setText(Html.fromHtml(text));

        type = Typeface.createFromAsset(c.getAssets(), "round.ttf");
        title.setTypeface(type);
        msg.setTypeface(type);
        li.setTypeface(type);
        link.setTypeface(type);
    }

}
