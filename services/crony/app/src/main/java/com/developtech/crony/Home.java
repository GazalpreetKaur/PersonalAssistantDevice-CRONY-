package com.developtech.crony;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ViewPager pager;
    FloatingActionMenu materialDesignFAM;
    public static  ArrayList <Integer> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        FloatingActionButton fab0=(FloatingActionButton)findViewById(R.id.fab0);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Alarms/");
                String path=String.valueOf(uri);
                System.out.println(path);
                i.setDataAndType(uri,"text/csv");
                startActivity(Intent.createChooser(i,"Open folder"));
            }
        });
        initComponents();
    }
    private void initComponents()
    {
        arr =new ArrayList<Integer>();
        arr.add(R.drawable.banner_slider);
        arr.add(R.mipmap.ic_launcher);
        arr.add(R.mipmap.ic_launcher);
        arr.add(R.mipmap.ic_launcher);
        pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new adapter_viewpager(Home.this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case(R.id.action_Edit):
            {   Intent i=new Intent(Home.this,Home.class);
                startActivity(i);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
