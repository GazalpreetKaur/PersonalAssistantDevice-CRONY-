package com.developtech.crony;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;

public class ShowShopping extends AppCompatActivity {
    private TextView txtNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        initComponents();

        Bundle bundleFromList;
        bundleFromList = getIntent().getExtras();

        if(bundleFromList != null){
            try{
                File file = getExternalFilesDir("ExternalShoppingList");
                File f = new File(file, bundleFromList.getString("fileName"));

                StringBuffer str = new StringBuffer();

                FileReader fr = new FileReader(f);
                int c = 0;
                while((c = fr.read()) != -1){
                    str.append((char)c);
                }
                txtNotes.setText(str);
            }
            catch (Exception e){
                Log.e("showShopping", e.toString());
            }
        }

    }
    private void initComponents(){
        txtNotes = (TextView)findViewById(R.id.txtNotes);
    }

}
