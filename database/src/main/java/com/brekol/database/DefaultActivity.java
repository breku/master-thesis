package com.brekol.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    private DatabaseService databaseService = new DatabaseService(this);

    @Pref
    MyPrefs_ myPrefs;

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView editText;

    @ViewById
    RadioButton sharedPreferencesRadio;

    @ViewById
    RadioButton sqlLiteRadio;


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

        if(sqlLiteRadio.isChecked()){
            saveSQLite(numberOfValues);
        }else if(sharedPreferencesRadio.isChecked()){
            saveSharedPrefs(numberOfValues);
        }
    }

    private void saveSharedPrefs(int numberOfValues) {
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfValues; i++) {
            myPrefs.getSharedPreferences().edit().putInt(String.valueOf(i),i);
        }

        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 6));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));

    }

    private void saveSQLite(int numberOfValues){

        long startTime = System.nanoTime();
        databaseService.save(numberOfValues);
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 6));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));
    }


    @Click
    public void buttonGet() {
        if(sqlLiteRadio.isChecked()){
            List<Integer> list = getSQLite();
        }else if(sharedPreferencesRadio.isChecked()){
            List<Integer> list = getSharedPrefs();
        }
    }

    private List<Integer> getSharedPrefs(){
        int numberOfValues = Integer.valueOf(editText.getText().toString());

        long startTime = System.nanoTime();

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <numberOfValues; i++) {
            result.add(myPrefs.getSharedPreferences().getInt(String.valueOf(i),i));
        }
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 6));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));
        return result;
    }


    private List<Integer> getSQLite(){
        long startTime = System.nanoTime();
        List<Integer> values = databaseService.getValues();
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 6));
        DecimalFormat df = new DecimalFormat("##.########");
        timeTextView.setText(df.format(timeSec));
        return values;
    }

    @Click
    public void buttonClear() {

        if(sqlLiteRadio.isChecked()){
            databaseService.clearDB();
        }else if(sharedPreferencesRadio.isChecked()){
            myPrefs.clear();
        }

    }

}

