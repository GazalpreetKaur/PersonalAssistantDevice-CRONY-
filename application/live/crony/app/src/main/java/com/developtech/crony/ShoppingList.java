package com.developtech.crony;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public class ShoppingList extends AppCompatActivity implements View.OnClickListener{

    private EditText etShoppingList;
    Button btnSave, btnDiscard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
    }//end of onCreate(Bundle savedInstanceState)

    private void initComponents(){
        etShoppingList = (EditText)findViewById(R.id.etShoppingList);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);

        //event handling
        btnSave.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);
    }//end of initComponents();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnDiscard):{
                Toast.makeText(this, "Discarded", Toast.LENGTH_SHORT).show();
                finish();
            }
            case(R.id.btnSave):{
                try{
                    File file = getExternalFilesDir("ExternalShoppingList");
                    Calendar cal = Calendar.getInstance();
                    String shoppingList = "shoppingList "+cal.getTime()+".txt";
                    File f = new File(file, shoppingList);
                    FileWriter fw = new FileWriter(f);
                    fw.write(etShoppingList.getText().toString());
                    fw.close();

                    Log.e("notes", "done");
                    finish();
                }
                catch (Exception e){
                    Log.e("Writing", e.toString());
                }
            }
        }//end of switch
    }//end of onClick(View v)

}
