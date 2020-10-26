package com.developtech.crony;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Reminder_1 extends AppCompatActivity {
    private MyTextView txtrem;
    private MyButton btnstop;
    private MediaPlayer mp=null;
    private SharedPreferences sp;
    public static final String DEFAULT_VALUE="null";
    public  void init()
    {
        txtrem=(MyTextView)findViewById(R.id.txtrem);
        btnstop=(MyButton)findViewById(R.id.btnstop);
        mp=MediaPlayer.create(Reminder_1.this,R.raw.sia);
        mp.start();
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                Toast.makeText(Reminder_1.this,"reminder stop",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        SharedPreferences sp=getSharedPreferences("spdata",MODE_PRIVATE);
        String name=sp.getString("name",DEFAULT_VALUE);
        if(name!=null)
        {
            txtrem.setText(name);
        }

    }

}
