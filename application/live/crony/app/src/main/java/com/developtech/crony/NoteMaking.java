package com.developtech.crony;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Calendar;

public class NoteMaking extends AppCompatActivity implements View.OnClickListener{

    private EditText etNotes;
    Button btnSave, btnDiscard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_making);
        initComponents();
    }//end of onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }//emd of onDestroy()

    private void initComponents(){
        etNotes = (EditText)findViewById(R.id.etNotes);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);

        //eventHandling
        btnSave.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);
    }//end of initComponets

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnDiscard):{
                Toast.makeText(this, "Discarded", Toast.LENGTH_SHORT).show();
                finish();
            }
            case(R.id.btnSave):{
                try{
                    File file = getExternalFilesDir("ExternalNotes");
                    Calendar cal = Calendar.getInstance();
                    String note = "note "+cal.getTime()+".txt";
                    File f = new File(file, note);
                    FileWriter fw = new FileWriter(f);
                    fw.write(etNotes.getText().toString());
                    fw.close();

                    Log.e("notes", "done");
                    finish();
                }
                catch (Exception e){
                    Log.e("Writing Exception", e.toString());
                }
            }
        }//end of switch
    }//end of onClick(View v)
}