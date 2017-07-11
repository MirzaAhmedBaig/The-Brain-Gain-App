package com.mirza.mab.thebrainganinapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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

public class Level_2 extends AppCompatActivity {

    private View baseContext,
            levelMsgFrame,
            gridFrame,
            errorPan,
            retryPan,
            resultPan;
    private TextView head,
            subHead, tapMsg,
            roundMsg, retHead,
            retsubHead, retMsg,
            retRoundMsg, resHead,
            ressubHead, resMsg;

    private GridView gridview;
    private MyDialogue dialogue;
    private ProgressBar progressBar;
    private RatingBar ratingBar;

    private static final int count = 24;
    private static final int levelNo = 2;
    private final int totalRounds = 2;
    private int roundNo = 1;

    private int[] numbers = new int[count];
    private int nextPosition = 1;
    private int resultCount;
    private int progressStatus = 0;
    private int score = 0;

    private boolean stop = false, playing = false, lost = false;

    private ObjectAnimator anim, colorAnim;
    private Handler handler;
    private Thread progressThread;
    private DatabaseHandler dbHandler = SinglePlayer.dbHandler;
    private Typeface type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_2);
        nViewInitialized();

    }

    public void nViewInitialized() {
        levelMsgFrame = findViewById(R.id.levelMsgFrame);
        gridFrame = findViewById(R.id.gridFrame);
        resultPan = findViewById(R.id.resultPan);
        retryPan = findViewById(R.id.retryPan);
        errorPan = findViewById(R.id.errorPan);

        gridview = (GridView) findViewById(R.id.buttonGrid);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        head = (TextView) findViewById(R.id.head);
        subHead = (TextView) findViewById(R.id.subHead);
        tapMsg = (TextView) findViewById(R.id.tapmsg);
        roundMsg = (TextView) findViewById(R.id.roundmsg);

        retHead = (TextView) findViewById(R.id.retHead);
        retsubHead = (TextView) findViewById(R.id.retsubHead);
        retMsg = (TextView) findViewById(R.id.retMsg);
        retRoundMsg = (TextView) findViewById(R.id.retRoundMsg);

        resHead = (TextView) findViewById(R.id.resHead);
        ressubHead = (TextView) findViewById(R.id.ressubHead);
        resMsg = (TextView) findViewById(R.id.resMsg);

        ratingBar = (RatingBar) findViewById(R.id.congoRatingBar);

        Flags.paused = false;
        handler = new Handler();

        type = Typeface.createFromAsset(getAssets(), "round.ttf");

        head.setTypeface(type);
        subHead.setTypeface(type);
        retHead.setTypeface(type);
        retsubHead.setTypeface(type);
        retMsg.setTypeface(type);
        resHead.setTypeface(type);
        ressubHead.setTypeface(type);
        resMsg.setTypeface(type);

    }

    @Override
    public void onBackPressed() {
        if (playing) {
            Flags.paused = true;
            dialogue = new MyDialogue(Level_2.this);
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
        progressStatus = 0;
        stop = false;
        switch (roundNo) {

            case 1:
                numbers = initArray(numbers, count, 1);
                numbers = doRandom(numbers, count);
                resultCount = 0;
                levelMsgFrame.setVisibility(View.GONE);
                gridview.setAdapter(new ButtonAdapter(this));
                playing = true;
                progressStatus=0;
                gridFrame.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!Flags.paused) {
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
                numbers = initArray(numbers, count, 1);
                numbers = doRandom(numbers, count);
                levelMsgFrame.setVisibility(View.GONE);
                resultCount = 0;
                gridview.setAdapter(new ButtonAdapter(this));
                playing = true;
                progressStatus=0;
                progressBar.setProgress(0);
                gridFrame.setVisibility(View.VISIBLE);
                progressThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            if (!Flags.paused) {
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
        }

    }

    public void lostRound(View v) throws InterruptedException {
        gridFrame.setVisibility(View.GONE);
        errorPan.setVisibility(View.GONE);
        Thread.sleep(100);
        retryPan.setVisibility(View.VISIBLE);

    }

    public void onError(int roundNo) {
        stop = true;
        errorPan.setVisibility(View.VISIBLE);
        retRoundMsg.setText("Round " + roundNo + " of " + totalRounds);
    }

    public void mainMenu(View v) {
        this.finish();
    }

    public void restartGame(View v) {
        this.recreate();
    }

    public void nextLevel(View v) {
        this.finish();
        Intent intent = new Intent(getBaseContext(), Level_3.class);
        startActivity(intent);
    }

    public int[] initArray(int[] arr, int size, int start) {
        for (int i = 0; i < size; i++) {
            arr[i] = start;
            start++;
        }
        return arr;

    }

    public int[] doRandom(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            double px, py;
            px = Math.random() * 24;
            py = Math.random() * 24;
            int p1 = (int) px;
            int p2 = (int) py;
            int temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;
        }
        return arr;
    }

    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonAdapter(Context c) {
            this.mContext = c;
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, final View convertView, final ViewGroup parent) {

            final Button button;
            button = new Button(mContext);

            int resID = getResources().getIdentifier("button", "drawable", "com.mirza.mab.thebrainganinapp");
            button.setText("" + numbers[position]);

            button.setTextColor(Color.rgb(10, 10, 10));
            button.setTextSize(35);
            button.setFocusable(false);
            button.setLayoutParams(new GridView.LayoutParams(150, 160));
            button.setBackgroundResource(resID);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int numClicked = Integer.parseInt(button.getText().toString());
                    button.setClickable(false);

                    switch (roundNo) {
                        case 1:
                            if (numClicked == nextPosition) {
                                nextPosition++;
                                resultCount++;
                                button.setText("");
                                if (resultCount == count) {
                                    stop = true;
                                    score = score + progressBar.getProgress();
                                    roundNo = 2;
                                    head.setText("CONGRATS !");
                                    subHead.setText("Now Tap the numbers in descending order from 24 to 1");
                                    roundMsg.setText("Round 2 of " + totalRounds);
                                    gridFrame.setVisibility(View.GONE);
                                    levelMsgFrame.setVisibility(View.VISIBLE);
                                    nextPosition--;
                                }

                            } else {
                                button.setTextColor(Color.rgb(245, 0, 0));
                                for (int i = 0; i < count; i++) {
                                    Button btn = (Button) parent.getChildAt(i);
                                    int value = -1;
                                    try {
                                        value = Integer.parseInt(btn.getText().toString());

                                    } catch (Exception e) {
                                    }
                                    if (value == nextPosition) {
                                        colorAnim = ObjectAnimator.ofInt(btn, "textColor", Color.rgb(76, 175, 80), Color.TRANSPARENT);
                                        colorAnim.setDuration(1000);
                                        colorAnim.setEvaluator(new ArgbEvaluator());
                                        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                                        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                                        colorAnim.start();
                                        break;
                                    }
                                }
                                onError(roundNo);
                            }
                            break;
                        case 2:
                            if (numClicked == nextPosition) {
                                nextPosition--;
                                resultCount++;
                                button.setText("");
                                if (resultCount == count) {
                                    stop = true;
                                    score = score + progressBar.getProgress();
                                    score = score / totalRounds;
                                    score++;
                                    if (score <= 1 && score <= 30) {
                                        score = 5;
                                        resMsg.setText("You have good eye!");
                                    } else if (score <= 31 && score <= 50) {
                                        resMsg.setText("Be faster for more stars!");
                                        score = 4;
                                    } else if (score <= 51 && score <= 70) {
                                        score = 3;
                                        resMsg.setText("Be faster for more stars!");
                                    } else if (score <= 71 && score <= 90) {
                                        score = 2;
                                        resMsg.setText("Be faster for more stars!");
                                    } else if (score <= 91 && score <= 99) {
                                        score = 1;
                                        resMsg.setText("Be faster for more stars!");
                                    }

                                    if (dbHandler.getScore(levelNo) == 0) {
                                        dbHandler.updateScore(levelNo, score);
                                    }
                                    if (dbHandler.getScore(levelNo) < score) {
                                        dbHandler.updateScore(levelNo, score);
                                    }
                                    dbHandler.addLevel(levelNo + 1, 0);
                                    gridFrame.setVisibility(View.GONE);

                                    anim = ObjectAnimator.ofFloat(ratingBar, "rating", 0, score);
                                    anim.setDuration(4000);
                                    anim.start();
                                    resultPan.setVisibility(View.VISIBLE);
                                    roundNo = 2;
                                    playing = false;
                                }

                            } else {
                                button.setTextColor(Color.rgb(245, 0, 0));
                                for (int i = 0; i < count; i++) {
                                    Button btn = (Button) parent.getChildAt(i);
                                    int value = -1;
                                    try {
                                        value = Integer.parseInt(btn.getText().toString());
                                    } catch (Exception e) {
                                    }
                                    if (value == nextPosition) {
                                        colorAnim = ObjectAnimator.ofInt(btn, "textColor", Color.rgb(76, 175, 80), Color.TRANSPARENT);
                                        colorAnim.setDuration(1000);
                                        colorAnim.setEvaluator(new ArgbEvaluator());
                                        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                                        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                                        colorAnim.start();
                                        break;
                                    }
                                }
                                onError(roundNo);
                            }
                            break;
                    }
                }
            });

            return button;
        }

    }


}
