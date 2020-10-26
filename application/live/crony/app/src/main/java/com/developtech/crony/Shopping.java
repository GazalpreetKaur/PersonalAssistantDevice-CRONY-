package com.developtech.crony;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.developtech.crony.listServices.NotesListService;

import java.io.File;
import java.util.ArrayList;

public class Shopping extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    private ListView list;
    private static ArrayList<String> files;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_shopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try{
            File filepath  = getExternalFilesDir("ExternalShoppingList/");
            files = NotesListService.getShoppingList(filepath);
            Log.e("ExternalShoppingList", String.valueOf(files.size()));
        }
        catch (Exception e){
            Log.e("ExternalShoppingList", e.toString());
        }

        //list
        list = (ListView)findViewById(R.id.list);
        list.setOnItemLongClickListener(this);

        //adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, files);
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

                Intent intToShowNotes = new Intent(Shopping.this, ShowShopping.class);
                intToShowNotes.putExtras(bundleNote);
                startActivity(intToShowNotes);
         }//end of onItemClick(AdapterView<?> parent, View view, int position, long id)
        });//end of setOnItemClickListener()


    }//end of oncreate

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final AlertDialog.Builder builder=new  AlertDialog.Builder(Shopping.this);
        builder.setMessage("Do you really want to delete this file?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String filename = (String)list.getItemAtPosition(position);
                File file = getExternalFilesDir("ExternalShoppingList");
                File f = new File(file, filename);
                f.delete();
                files.remove(position);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
            }
        });//ok button

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Shopping.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });//cancel button
        builder.show();
        return true;
    }
}
