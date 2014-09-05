package com.brekol.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.util.List;


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    private DatabaseService databaseService = new DatabaseService(this);

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView editText;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, ">> onCreate called.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.brekol.database.R.menu.main, menu);
        return true;
    }

    @Click
    public void buttonSave() {
        int numberOfValues = Integer.valueOf(editText.getText().toString());


        long startTime = System.nanoTime();
        databaseService.save(numberOfValues);
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 9));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));


    }

    @Click
    public void buttonGet() {
        long startTime = System.nanoTime();
        List<Integer> values = databaseService.getValues();
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 9));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));
    }

    @Click
    public void buttonClear() {
        databaseService.clearDB();
    }

}

