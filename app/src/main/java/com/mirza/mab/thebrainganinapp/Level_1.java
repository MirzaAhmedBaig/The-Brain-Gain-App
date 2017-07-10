package com.mirza.mab.thebrainganinapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Level_1 extends AppCompatActivity {

    private View baseContext;
    private GridView gridview;
    private MyDialogue dialogue;
    private ProgressBar progressBar;
    private View descriptPan, gamePan, errorPan, retryPan, resultPan;
    private TextView heading, subHeading, msg, roundMsg, roundMsg1, resultMsg, r1, r2,r3,r4,r5;
    private RatingBar ratingBar;
    private int oncePosition = 0, roundNo = 1;
    private int progressStatus = 0;
    private boolean stop = false, playing = false, lost = false;
    private Handler handler;
    private Thread progressThread;
    int score = 0;
    private static int totalRounds = 4;
    DatabaseHandler dbHandler = SinglePlayer.dbHandler;
    public static boolean paused;
    Typeface type = MainActivity.type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_1);
        nViewInitialized();

    }

    public void nViewInitialized() {
        baseContext = findViewById(R.id.baseContext);
        descriptPan = findViewById(R.id.frame1);
        gamePan = findViewById(R.id.frame2);
        resultPan = findViewById(R.id.resultPan);
        retryPan = findViewById(R.id.retryPan);
        errorPan = findViewById(R.id.errorPan);
        gridview = (GridView) findViewById(R.id.gridview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        heading = (TextView) findViewById(R.id.textView5);
        subHeading = (TextView) findViewById(R.id.textView6);
        msg = (TextView) findViewById(R.id.textView7);
        roundMsg = (TextView) findViewById(R.id.textView8);
        r3 = (TextView) findViewById(R.id.textView9);
        r4 = (TextView) findViewById(R.id.textView10);
        roundMsg1 = (TextView) findViewById(R.id.textView13);
        r1 = (TextView) findViewById(R.id.textView14);
        r2 = (TextView) findViewById(R.id.textView15);
        resultMsg = (TextView) findViewById(R.id.textView16);
        ratingBar = (RatingBar) findViewById(R.id.congoRatingBar);
        paused = false;
        handler = new Handler();
        heading.setTypeface(type);
        subHeading.setTypeface(type);
        r1.setTypeface(type);
        r2.setTypeface(type);
        r3.setTypeface(type);
        r4.setTypeface(type);
        resultMsg.setTypeface(type);

    }

    @Override
    public void onBackPressed() {
        if (playing) {
            paused = true;
            dialogue = new MyDialogue(Level_1.this);
            dialogue.show();
        } else {
            super.onBackPressed();
        }
    }

    public void rounds(View v) {
        double pos = Math.random() * 83;
        oncePosition = (int) pos;
        progressStatus = 0;
        stop = false;
        switch (roundNo) {

            case 1:
                descriptPan.setVisibility(View.GONE);
                gridview.setAdapter(new ButtonAdapter(this, "7", "1"));
                playing = true;
                gamePan.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 100) {
                                            if (!lost) {
                                                onError(1);
                                            }
                                            stop = true;
                                        }
                                    }
                                });
                            }

                        }
                    }
                });
                progressThread.start();

                break;
            case 2:
                descriptPan.setVisibility(View.GONE);
                gridview.setAdapter(new ButtonAdapter(this, "8", "3"));
                playing = true;
                progressBar.setProgress(0);
                gamePan.setVisibility(View.VISIBLE);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(250);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 100) {
                                            if (!lost) {
                                                onError(2);
                                            }
                                            stop = true;
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                progressThread.start();
                break;
            case 3:
                descriptPan.setVisibility(View.GONE);
                gridview.setAdapter(new ButtonAdapter(this, "Q", "O"));
                playing = true;
                progressBar.setProgress(0);
                gamePan.setVisibility(View.VISIBLE);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 100) {
                                            if (!lost) {
                                                onError(3);
                                            }
                                            stop = true;
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                progressThread.start();
                break;
            case 4:
                descriptPan.setVisibility(View.GONE);
                gridview.setAdapter(new ButtonAdapter(this, "B", "8"));
                playing = true;
                progressBar.setProgress(0);
                gamePan.setVisibility(View.VISIBLE);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(150);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 100) {
                                            if (!lost) {
                                                onError(4);
                                            }
                                            stop = true;
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                progressThread.start();
                break;

        }

    }

    public void lostRound(View v) {
        gamePan.setVisibility(View.GONE);
        errorPan.setVisibility(View.GONE);
        retryPan.setVisibility(View.VISIBLE);

    }

    public void onError(int roundNo) {
        stop = true;
        errorPan.setVisibility(View.VISIBLE);
        roundMsg1.setText("Round " + roundNo + " of 4");
    }

    public void mainMenu(View v) {
        this.finish();
    }

    public void restartGame(View v) {
        this.recreate();
    }

    public void nextLevel(View v){
        this.finish();
        Intent intent=new Intent(getBaseContext(),Level_2.class);
        startActivity(intent);
    }

    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;
        private String value = "", randomValue = "";

        public ButtonAdapter(Context c, String value, String randomValue) {
            this.value = value;
            this.randomValue = randomValue;
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
                button.setText(randomValue);
            } else {
                button.setText(value);
            }
            button.setTextColor(Color.rgb(80, 80, 80));
            button.setTextSize(16);
            int resID = getResources().getIdentifier("button", "drawable", "com.mirza.mab.thebrainganinapp");
            button.setBackgroundResource(resID);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (roundNo) {
                        case 1:
                            if (button.getText().toString().equals("1")) {
                                score = score + progressBar.getProgress();
                                stop = true;
                                heading.setText("CONGRATS !");
                                subHeading.setText("Now find and tap number 3");
                                roundMsg.setText("Round 2 of 4");
                                gamePan.setVisibility(View.GONE);
                                descriptPan.setVisibility(View.VISIBLE);
                                roundNo = 2;
                            } else {
                                onError(1);
                            }
                            break;
                        case 2:
                            if (button.getText().toString().equals("3")) {
                                score = score + progressBar.getProgress();
                                stop = true;
                                heading.setText("CONGRATS !");
                                subHeading.setText("Now find and tap number O");
                                roundMsg.setText("Round 3 of 4");
                                gamePan.setVisibility(View.GONE);
                                descriptPan.setVisibility(View.VISIBLE);
                                roundNo = 3;
                            } else {
                                onError(2);
                            }
                            break;
                        case 3:
                            if (button.getText().toString().equals("O")) {
                                score = score + progressBar.getProgress();
                                stop = true;
                                heading.setText("CONGRATS !");
                                subHeading.setText("Now find and tap number 8");
                                roundMsg.setText("Round 3 of 4");
                                gamePan.setVisibility(View.GONE);
                                descriptPan.setVisibility(View.VISIBLE);
                                roundNo = 4;
                            } else {
                                onError(3);
                            }
                            break;
                        case 4:
                            if (button.getText().toString().equals("8")) {
                                score = score + progressBar.getProgress();
                                stop = true;
                                score = score / totalRounds;
                                score++;
                                if (score <= 1 && score <= 20) {
                                    score = 5;
                                    resultMsg.setText("You have good eye!");
                                } else if (score <= 21 && score <= 40) {
                                    resultMsg.setText("Be faster for more stars!");
                                    score = 4;
                                } else if (score <= 41 && score <= 60) {
                                    score = 3;
                                    resultMsg.setText("Be faster for more stars!");
                                } else if (score <= 61 && score <= 80) {
                                    score = 2;
                                    resultMsg.setText("Be faster for more stars!");
                                } else if (score <= 81 && score <= 99) {
                                    score = 1;
                                    resultMsg.setText("Be faster for more stars!");
                                }

                                if (dbHandler.getScore(1) == 0) {
                                    dbHandler.updateScore(1, score);
                                }
                                if (dbHandler.getScore(1) < score) {
                                    dbHandler.updateScore(1, score);
                                }
                                dbHandler.addLevel(2, 0);
                                gamePan.setVisibility(View.GONE);
//                                ratingBar.setRating(score);
                                ObjectAnimator anim = ObjectAnimator.ofFloat(ratingBar, "rating", 0, score);
                                anim.setDuration(4000);
                                anim.start();
                                resultPan.setVisibility(View.VISIBLE);
                                roundNo = 4;
                                playing = false;
                            } else {
                                onError(4);
                            }
                            break;
                    }
                }
            });
            return button;
        }

    }


}
