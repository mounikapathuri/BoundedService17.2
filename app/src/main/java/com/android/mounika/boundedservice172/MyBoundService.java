package com.android.mounika.boundedservice172;
import android.app.Service;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {
    private IBinder iBinder = new myBinder();

    public MyBoundService() {
    }

    public IBinder onBind(Intent arg0) {
        return iBinder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTime() {
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/mm/yyyy", Locale.US);
        String DateTime = DateFormat.getDateTimeInstance().format(new Date());
        return (DateTime);
    }

    class myBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}