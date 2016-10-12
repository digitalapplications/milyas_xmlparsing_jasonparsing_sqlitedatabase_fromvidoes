package com.example.l400.xmlparsing;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by l400 on 10/6/2016.
 */
public class SettingA extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prference);

    }


}
