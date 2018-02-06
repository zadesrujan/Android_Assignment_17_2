package com.example.user.android_assignment_17_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundService extends Service {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with Service which is offering service to the function.

    IBinder iBinder = new LocalBinder();
    //creating the new object as iBinder.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public IBinder onBind(Intent intent) { //  Return the communication channel to the service.
        return iBinder;
    }


    public class LocalBinder extends Binder {
        //class which extending the binder object
        public BoundService getService() {
            // Return this instance of BoundService
            return BoundService.this;
        }

    }

    public String getTime() {//Method for GetTime()
        //creating object of SimpleDateFormat
        SimpleDateFormat simpleDateFormat = null;
        //Applying the condition for getting the date and time
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return simpleDateFormat.format(new Date());

    }
}