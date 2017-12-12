package com.android.mounika.boundedservice172;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.icu.text.DateFormat;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnShowTime;
    TextView tvShowTime;
    boolean isBound = false;
    MyBoundService myBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowTime = (Button) findViewById(R.id.btnStartService);
        tvShowTime = (TextView) findViewById(R.id.tvTime);
        btnShowTime.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //bind to myBoundService
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(myConnection);
            isBound = false;
            tvShowTime.setText("");
        }
    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.myBinder binder = (MyBoundService.myBinder) service;
            myBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void getTime(View v) {
        tvShowTime.setText(myBoundService.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                if (isBound) {
                    myBoundService.getTime();
                    Toast.makeText(this, "You clicked on Start Service", Toast.LENGTH_SHORT).show();
                    tvShowTime.setText(myBoundService.getTime());
                }
                break;
        }
    }
}
