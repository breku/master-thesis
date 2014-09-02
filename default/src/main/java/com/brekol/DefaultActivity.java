package com.brekol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    @ViewById
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, ">> onCreate called.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.brekol.R.menu.main, menu);
        return true;
    }

    @Click
    public void buttonDefault() {
        Log.i(TAG, ">> Button clicked");
        Log.i(TAG, String.format(">> Text view: %s", textView));
    }

}

