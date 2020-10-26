package com.developtech.crony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.developtech.crony.listServices.NotesListService;

import java.io.File;
import java.util.ArrayList;

public class TestingShopping extends AppCompatActivity {
    private ListView list;
    private static ArrayList<String> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_shopping);
        try{
            File filepath  = getExternalFilesDir("ExternalShoppingList/");
            files = NotesListService.getNotesList(filepath);
            Log.e("Shopping", String.valueOf(files.size()));
        }
        catch (Exception e){
            Log.e("ShoppingError", e.toString());
        }

        //list
        list = (ListView)findViewById(R.id.list);

        //adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, files);
        list.setAdapter(adapter);

        //event handling
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) list.getItemAtPosition(position);

                //show alert
                Toast.makeText(getApplicationContext(), "Position "+position+" lisitem "+itemValue, Toast.LENGTH_LONG).show();
                Bundle bundleNote = new Bundle();
                bundleNote.putString("fileName", itemValue.toString());

                Intent intToShowShopping = new Intent(TestingShopping.this, ShowShopping.class);
                intToShowShopping.putExtras(bundleNote);
                startActivity(intToShowShopping);
            }//end of onItemClick(AdapterView<?> parent, View view, int position, long id)
        });
    }
}
