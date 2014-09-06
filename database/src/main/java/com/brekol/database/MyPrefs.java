package com.brekol.database;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultInt;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * User: Breku
 * Date: 2014-09-06
 */
@SharedPref
public interface MyPrefs {


    int key();

    @DefaultInt(1)
    int value();

    String name();
}
