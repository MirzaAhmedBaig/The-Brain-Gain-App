package com.mirza.mab.thebrainganinapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class Levels extends Fragment implements View.OnClickListener {

    private View baseContext, lockedLevels;
    private RatingBar ratingBar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_levels, container, false);

        baseContext = v.findViewById(R.id.baseContext);
        lockedLevels = v.findViewById(R.id.lockedLevels);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

        baseContext.setOnClickListener(this);
        int resID = getResources().getIdentifier(getArguments().getString("back"), "drawable", "com.mirza.mab.thebrainganinapp");
        baseContext.setBackgroundResource(resID);
        if (getArguments().getInt("lock") == 0) {
            lockedLevels.setVisibility(View.GONE);
        }
        ratingBar.setRating(getArguments().getFloat("stars"));

        return v;
    }

    public static Levels newInstance(String image, int level, int lock, float stars) {

        Levels f = new Levels();
        Bundle b = new Bundle();
        b.putString("back", image);
        b.putInt("level", level);
        b.putInt("lock", lock);
        b.putFloat("stars", stars);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View v) {
        if (getArguments().getInt("level") == 1 && getArguments().getInt("lock") == 0) {
            Intent intent = new Intent(getContext(), Level_1.class);
            startActivity(intent);
        } else if (getArguments().getInt("level") == 2 && getArguments().getInt("lock") == 0) {
            Intent intent = new Intent(getContext(), Level_2.class);
            startActivity(intent);

        } else if (getArguments().getInt("level") == 3 && getArguments().getInt("lock") == 0) {
            Intent intent = new Intent(getContext(), Level_3.class);
            startActivity(intent);

        } else if (getArguments().getInt("level") == 4 && getArguments().getInt("lock") == 0) {
            Toast.makeText(getContext(), "Level 4", Toast.LENGTH_SHORT).show();

        } else if (getArguments().getInt("level") == 5 && getArguments().getInt("lock") == 0) {
            Toast.makeText(getContext(), "Level 5", Toast.LENGTH_SHORT).show();

        } else if (getArguments().getInt("level") == 6 && getArguments().getInt("lock") == 0) {
            Toast.makeText(getContext(), "Level 6", Toast.LENGTH_SHORT).show();

        }
    }

}