package com.developtech.crony;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class Instruction extends AppCompatActivity{
    ViewPager pager1;
    public static  ArrayList <Integer> arr;
    Button btnskip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


        initComponents();
    }
    private void initComponents() {
        SharedPreferences sp = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        boolean firstTime = sp.getBoolean("first", true);
        if (firstTime) {
            btnskip = (Button) findViewById(R.id.btnskip);
            arr = new ArrayList<Integer>();
            arr.add(R.drawable.instruction1);
            arr.add(R.drawable.instruction2);
            arr.add(R.drawable.instruction3);
            arr.add(R.drawable.instruction4);
            pager1 = (ViewPager) findViewById(R.id.pager1);
            pager1.setAdapter(new Adapter1(Instruction.this));
           btnskip.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   SharedPreferences sp = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sp.edit();
                   editor.putBoolean("first",false);
                   editor.commit();
                   Intent intent = new Intent(Instruction.this, LoginPage.class);
                   finish();
                   startActivity(intent);
               }
           });
        }
        else{
            Intent intent = new Intent(Instruction.this, LoginPage.class);
            finish();
            startActivity(intent);
        }
    }
}
