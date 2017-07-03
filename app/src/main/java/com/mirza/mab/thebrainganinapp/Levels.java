package com.mirza.mab.thebrainganinapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Levels extends Fragment {

    private View baseContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_levels, container, false);

        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        baseContext=v.findViewById(R.id.baseContext);

        tv.setText(getArguments().getString("msg"));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.argb(255, getArguments().getInt("no") * 50, getArguments().getInt("no") * 10, getArguments().getInt("no") * 50));
        baseContext.setBackgroundColor(Color.argb(255, getArguments().getInt("no") * 50, getArguments().getInt("no") * 10, getArguments().getInt("no") * 50));

        return v;
    }

    public static Levels newInstance(String text,int position) {

        Levels f = new Levels();
        Bundle b = new Bundle();
        b.putString("msg", text);
        b.putInt("no",position);

        f.setArguments(b);

        return f;
    }

}