package com.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;


@NoTitle
@EActivity(R.layout.activity_main)
public class HelloAndroidActivity extends Activity implements SensorEventListener {


    @ViewById
    TextView accelX;

    @ViewById
    TextView accelY;

    @ViewById
    TextView accelZ;

    @ViewById
    EditText editText;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private float[] accelValues = new float[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAccelerometer();
    }

    private void initAccelerometer() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, 1000 * 2000);
    }


    @Click
    public void buttonPlay() {
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.registerListener(this, mAccelerometer, 1000 * Integer.valueOf(editText.getText().toString()));
        updateAccelValues();

    }

    private void updateAccelValues() {
        accelX.setText(String.valueOf(accelValues[0]));
        accelY.setText(String.valueOf(accelValues[1]));
        accelZ.setText(String.valueOf(accelValues[2]));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.accelerometer.R.menu.main, menu);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        for (int i = 0; i < 3; i++) {
            accelValues[i] = sensorEvent.values[i];
        }
       updateAccelValues();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

