package com.example.ycq.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button button;
    MyHandler myHandler = new MyHandler(this);
    static int i;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                textView.setText("1");
                break;
            case  R.id.buju:
               textView.setText("yinchang");
               break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.d);
        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        findViewById(R.id.buju).setOnClickListener(this);

        new Thread(){
            @Override
            public void run() {
                super.run();
                do {
                    try {
                        sleep(3000);
                        Message msg = Message.obtain();
                        msg.what = 1;
                        i++;
                        myHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }.start();
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
                    sleep(100);
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
