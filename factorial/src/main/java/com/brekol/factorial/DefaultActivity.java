package com.brekol.factorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

import java.math.BigInteger;
import java.text.DecimalFormat;


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    @ViewById
    TextView timeTextView;

    @ViewById
    EditText editText;

    @ViewById
    RadioButton iterativeRadioButton;

    @ViewById
    RadioButton recursiveRadioButton;

    @ViewById
    TextView resultTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, ">> onCreate called.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.brekol.factorial.R.menu.main, menu);
        return true;
    }

    @Click
    public void calculateButton() {

        int x = Integer.valueOf(editText.getText().toString());
        long startTime = System.nanoTime();
        BigInteger result = calculateFactorial(x);
        long endTime = System.nanoTime();

        double timeSec = ((endTime - startTime) / Math.pow(10, 9));
        DecimalFormat df = new DecimalFormat("#########");
        timeTextView.setText(df.format(timeSec));

        resultTextView.setText(String.valueOf(result));

    }

    private BigInteger calculateFactorial(int x){
        BigInteger result = BigInteger.ZERO;
        if(iterativeRadioButton.isChecked()){
            result = iterativeFactorial(BigInteger.valueOf(x));
        }else if(recursiveRadioButton.isChecked()){
            result = recursiveFactorial(BigInteger.valueOf(x));
        }
        return result;
    }



    private BigInteger recursiveFactorial(BigInteger x){
        if(x.equals(BigInteger.ZERO)){
            return BigInteger.ONE;
        }else
            return x.multiply(recursiveFactorial(x.subtract(BigInteger.ONE)));
    }

    private BigInteger iterativeFactorial(BigInteger x){
        BigInteger result = BigInteger.ONE;
        for(int i =1; BigInteger.valueOf(i).compareTo(x) < 1;i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}

