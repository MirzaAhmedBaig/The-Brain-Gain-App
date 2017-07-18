package com.mirza.mab.thebrainganinapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
    private TextView heading, subHeading, msg, roundMsg, roundMsg1, resultMsg, r1, r2, r3, r4;
    private RatingBar ratingBar;
    private int oncePosition = 0, roundNo = 1;
    private int progressStatus = 0;
    int width;
    int height;
    DisplayMetrics displayMetrics;
    private boolean stop = false, playing = false, lost = false;
    private Handler handler;
    private Thread progressThread;
    private int score = 0;
    private static int totalRounds = 4;
    private DatabaseHandler dbHandler = SinglePlayer.dbHandler;
    private Typeface type = MainActivity.type;
    private boolean correct = false;


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
        Flags.paused = false;
        handler = new Handler();
        heading.setTypeface(type);
        subHeading.setTypeface(type);
        r1.setTypeface(type);
        r2.setTypeface(type);
        r3.setTypeface(type);
        r4.setTypeface(type);
        resultMsg.setTypeface(type);
        progressBar.setMax(1000);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

    }

    @Override
    public void onBackPressed() {
        if (playing) {
            Flags.paused = true;
            dialogue = new MyDialogue(Level_1.this);
            dialogue.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Flags.paused = false;
                }
            });
            dialogue.show();
        } else {
            Flags.refreshFlag=true;
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
                        while (progressStatus < 1000) {
                            if (!Flags.paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(30);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 1000) {
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
                        while (progressStatus < 1000) {
                            if (!Flags.paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(25);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 1000) {
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
                        while (progressStatus < 1000) {
                            if (!Flags.paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 1000) {
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
                        while (progressStatus < 1000) {
                            if (!Flags.paused) {
                                if (stop) {
                                    return;
                                }
                                progressStatus += 1;
                                try {
                                    Thread.sleep(15);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        if (progressStatus == 1000) {
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

    public void nextLevel(View v) {
        this.finish();
        Intent intent = new Intent(getBaseContext(), Level_2.class);
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
            final Button correctButton;
            int w,h;
            w=(width/7)-12;
            h=(height/12)-15;
            if (convertView == null) {
                button = new Button(mContext);
                correctButton = new Button(mContext);
                button.setLayoutParams(new GridView.LayoutParams(w, h));
                correctButton.setLayoutParams(new GridView.LayoutParams(w, h));
            } else {
                button = (Button) convertView;
                correctButton = (Button) convertView;
            }

            if (position == oncePosition) {
                correct = true;
                correctButton.setTextColor(Color.rgb(80, 80, 80));
                correctButton.setText(randomValue);
            } else {
                button.setTextColor(Color.rgb(80, 80, 80));
                button.setText(value);
            }

            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            correctButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            int resID = getResources().getIdentifier("button", "drawable", "com.mirza.mab.thebrainganinapp");
            button.setBackgroundResource(resID);
            correctButton.setBackgroundResource(resID);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Button btn = (Button) parent.getChildAt(oncePosition);
                    ObjectAnimator colorAnim = ObjectAnimator.ofInt(btn, "textColor", Color.rgb(76, 175, 80), Color.TRANSPARENT);
                    colorAnim.setDuration(1000);
                    colorAnim.setEvaluator(new ArgbEvaluator());
                    colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                    colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                    colorAnim.start();
                    onError(roundNo);
                }
            });

            correctButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (roundNo) {
                        case 1:
                            score = score + progressBar.getProgress();
                            stop = true;
                            heading.setText("CONGRATS !");
                            subHeading.setText("Now find and tap number 3");
                            roundMsg.setText("Round 2 of 4");
                            gamePan.setVisibility(View.GONE);
                            descriptPan.setVisibility(View.VISIBLE);
                            roundNo = 2;
                            break;
                        case 2:
                            score = score + progressBar.getProgress();
                            stop = true;
                            heading.setText("CONGRATS !");
                            subHeading.setText("Now find and tap number O");
                            roundMsg.setText("Round 3 of 4");
                            gamePan.setVisibility(View.GONE);
                            descriptPan.setVisibility(View.VISIBLE);
                            roundNo = 3;
                            break;
                        case 3:
                            score = score + progressBar.getProgress();
                            stop = true;
                            heading.setText("CONGRATS !");
                            subHeading.setText("Now find and tap number 8");
                            roundMsg.setText("Round 3 of 4");
                            gamePan.setVisibility(View.GONE);
                            descriptPan.setVisibility(View.VISIBLE);
                            roundNo = 4;

                            break;
                        case 4:
                            score = score + progressBar.getProgress();
                            stop = true;
                            score = score / totalRounds;
                            score++;
                            if (score <= 1 && score <= 300) {
                                score = 5;
                                resultMsg.setText("You have good eye!");
                            } else if (score <= 301 && score <= 500) {
                                resultMsg.setText("Be faster for more stars!");
                                score = 4;
                            } else if (score <= 501 && score <= 700) {
                                score = 3;
                                resultMsg.setText("Be faster for more stars!");
                            } else if (score <= 701 && score <= 900) {
                                score = 2;
                                resultMsg.setText("Be faster for more stars!");
                            } else if (score <= 901 && score <= 999) {
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
                            ObjectAnimator anim = ObjectAnimator.ofFloat(ratingBar, "rating", 0, score);
                            anim.setDuration(4000);
                            anim.start();
                            resultPan.setVisibility(View.VISIBLE);
                            roundNo = 4;
                            playing = false;
                            score=0;
                            progressStatus=0;
                            progressBar.setProgress(0);
                            break;
                    }
                }
            });
            if (correct) {
                correct = false;
                return correctButton;
            }
            return button;
        }

    }


}
