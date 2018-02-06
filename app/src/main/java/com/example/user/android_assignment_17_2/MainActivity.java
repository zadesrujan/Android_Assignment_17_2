package com.example.user.android_assignment_17_2;
//Package objects contain version information about the implementation and specification of a Java package.

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    TextView textView;
    Button button;
    BoundService boundService;
    boolean serviceBound;
    //Intializing the textview,button,service and bound service.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        textView=(TextView)findViewById(R.id.textView);
        //giving id to the text
        button=(Button)findViewById(R.id.button);
        //giving id to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(boundService.getTime());
                //setting the click on listner to the button when we click on it to show something.
            }
        });
    }

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onStart() {
        //Called when the activity is becoming visible to the user.
        super.onStart();
        //creating object of intent
        // Bind to LocalService
        Intent intent = new Intent(this, BoundService.class);
        //creating intent with the bound class to combine the main activity to anthor class.
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        //creating autoservice of internet.

    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        //creating object of serviceConnection
        public void onServiceDisconnected(ComponentName name) {
            //creating the method as service disconnect
            Toast.makeText(MainActivity.this, "Service is disconnected", Toast.LENGTH_SHORT).show();
            //toasting a message that service is disconnect when ever the service is not available.
            serviceBound=false;

        }
        @Override
        //we use override to tells the compiler that the following method overrides a method of its superclass.
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //creating method onServiceConected
            Toast.makeText(MainActivity.this, "Service is connected", Toast.LENGTH_SHORT).show();
            serviceBound = true;
            // bounding  the LocalService,
            BoundService.LocalBinder localBinder = (BoundService.LocalBinder) iBinder;
            //getting localService Instance
            boundService = localBinder.getService();
        }
    };


    protected void onStop(){
        MainActivity.super.onStop();
        // Unbind from the service
        if (serviceBound)
        {
            unbindService(serviceConnection);
            serviceBound= false;

        }
    }


}

