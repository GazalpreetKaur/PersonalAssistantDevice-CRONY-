package com.developtech.crony;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{
    private ImageView facebook,google,whatsapp,phone,instagram,snapchat;
    private Button btnnext,btnskip;
    public static boolean flag=false;
    public static ArrayList al=new ArrayList();
    private void init()
    {
        facebook=(ImageView)findViewById(R.id.facebook);
        google=(ImageView)findViewById(R.id.google);
        whatsapp=(ImageView)findViewById(R.id.whatsapp);
        phone=(ImageView)findViewById(R.id.phone);
        instagram=(ImageView)findViewById(R.id.instagram);
        snapchat=(ImageView)findViewById(R.id.snapchat);
        btnnext=(Button)findViewById(R.id.btnnext);
        btnskip=(Button)findViewById(R.id.btnskip);

        btnnext.setOnClickListener(this);
        btnskip.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        phone.setOnClickListener(this);
        instagram.setOnClickListener(this);
        snapchat.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();


    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.facebook:
            {
                al.add("facebook");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                facebook.setBackground(highlight);
                break;
            }
            case R.id.google:
            {
                al.add("google");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                google.setBackground(highlight);
                break;
            }
            case R.id.whatsapp:
            {
                al.add("whatsapp");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                whatsapp.setBackground(highlight);
                break;
            }
            case R.id.phone:
            {
                al.add("phone");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                phone.setBackground(highlight);
                break;
            }
            case R.id.instagram:
            {
                al.add("instagram");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                instagram.setBackground(highlight);
                break;
            }
            case R.id.snapchat:
            {
                al.add("snapchat");
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                snapchat.setBackground(highlight);
                break;
            }
            case R.id.btnnext:
            {
                Intent i=new Intent(MainScreen.this,Home.class);
                startActivity(i);
                finish();
                break;
            }
            case R.id.btnskip:
            {
                flag=true;
                Intent i=new Intent(MainScreen.this,Home.class);
                startActivity(i);
                break;
            }
        }

    }
}
