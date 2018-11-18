package com.example.ycq.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler = new MyHandler(this);
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.d);
        new MyThread().start();
    }

    public static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            super();
            mActivity = new WeakReference<MainActivity>(activity);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mActivity.get();

            switch (msg.what) {
                case 1:
                    activity.textView.setText(activity.i + "");
                    break;
            }

        }
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            do {
                try {
                    sleep(1000);
                    Message msg = Message.obtain();
                    msg.what = 1;
                    i++;
                    myHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }
}
