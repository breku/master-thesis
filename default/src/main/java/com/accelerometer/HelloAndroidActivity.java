package com.accelerometer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;


@NoTitle
@EActivity(R.layout.activity_main)
public class HelloAndroidActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.accelerometer.R.menu.main, menu);
        return true;
    }

}

