package com.developtech.crony;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String DEFAULT_VALUE="no";
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                SharedPreferences sp = getSharedPreferences("splogin",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                boolean login=sp.getBoolean("login",false);
                if(!login)//if not logged in
                {
                    Intent i=new Intent(SplashScreen.this,Instruction.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(SplashScreen.this, MainScreen.class);
                    startActivity(i);
                    finish();
                }
            }
        },1000);
    }
    }


