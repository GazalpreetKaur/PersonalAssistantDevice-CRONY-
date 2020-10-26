package com.developtech.crony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {
    private MyTextView txtabout;
    public void init()
    {
        txtabout=(MyTextView)findViewById(R.id.txtabout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        init();
    }
}
