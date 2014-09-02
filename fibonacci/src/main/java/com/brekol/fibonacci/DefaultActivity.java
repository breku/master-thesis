package com.brekol.fibonacci;

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


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView editText;

    @ViewById
    TextView resultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.brekol.fibonacci.R.menu.main, menu);
        return true;
    }

    @Click
    public void buttonCalculate() {
        int x = Integer.valueOf(editText.getText().toString());
        long startTime = System.nanoTime();
        int result = calculateFibonacci(x);
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 9));
        DecimalFormat df = new DecimalFormat("#.########");
        timeTextView.setText(df.format(timeSec));

        resultTextView.setText(String.valueOf(result));
    }

    private int calculateFibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return (calculateFibonacci(n - 1) + calculateFibonacci(n - 2));
    }
}

