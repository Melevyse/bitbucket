package com.melevyse.android1.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.melevyse.android1.homework1.R;

public class FirstActivity extends AppCompatActivity {
    private static final long SLEEP_DELAY = 2000L;
    private boolean mResumed;
    private volatile boolean mOnBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mResumed) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(SLEEP_DELAY);

                        if (!mOnBackPressed) {
                            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            mResumed = true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mOnBackPressed = true;
    }
}
