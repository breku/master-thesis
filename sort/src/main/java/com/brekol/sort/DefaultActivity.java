package com.brekol.sort;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@NoTitle
@EActivity(R.layout.activity_main)
public class DefaultActivity extends Activity {

    private final String TAG = getClass().getName();

    private List<Integer> list;

    private Random random =new Random();

    private int listSize;

    @ViewById
    TextView listSizeTextBox;

    @ViewById
    TextView generatedListLabel;

    @ViewById
    TextView sortedListLabel;

    @ViewById
    TextView timeLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, ">> onCreate called.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Click
    public void sortListButton() {
        long startTime = System.nanoTime();
        sort(list);
        long endTime = System.nanoTime();
//        sortedListLabel.setText(list.toString());
        double timeSec = ((endTime - startTime) / Math.pow(10, 6));
        DecimalFormat df = new DecimalFormat("#.######");
        timeLabel.setText(df.format(timeSec));

    }


    public void sort(List<Integer> list){

        for(int i =0;i<list.size()-1;i++){
            int index = i;
            for(int j=i+1;j<list.size();j++){
                if(list.get(j) < list.get(index) ){
                    index = j;
                }
            }
            if(index != i){
                int tmp = list.get(i);
                list.set(i,list.get(index));
                list.set(index,tmp);
            }
        }

    }

    @Click
    public void generateListButton() {
        listSize = Integer.valueOf(listSizeTextBox.getText().toString());
        list = new ArrayList<Integer>();
        for(int i =0;i< listSize;i++){
            list.add(random.nextInt(100));
        }
//        generatedListLabel.setText(list.toString());
    }

}

