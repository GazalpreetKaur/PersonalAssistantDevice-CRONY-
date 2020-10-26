package com.developtech.crony.listServices;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class NotesListService {
    private static ArrayList<String> notesList;
    private static ArrayList<String> shoppingList;
    private static String[] paths;

    public static ArrayList<String> getNotesList(File f){//f = ExternalNotes
        try {
            paths = f.list();
            notesList = new ArrayList<String>();
            for (int i = 0; i < paths.length; i++) {
                String[] filename = paths[i].split(" ");
                if (filename[0].equals("note")) {
                    notesList.add(paths[i]);
                }
            }
            return notesList;
        }
        catch (Exception e){
            Log.e("Exception in notes", e.toString());
            return null;
        }//end of try Catch
    }//end of getNotesList

    public static ArrayList<String> getShoppingList(File f){//f = ExternalShoppingList
        try {
            paths = f.list();
            shoppingList = new ArrayList<String>();
            for (int i = 0; i < paths.length; i++) {
                String[] filename = paths[i].split(" ");
                if (filename[0].equals("shoppingList")) {
                    shoppingList.add(paths[i]);
                }
            }
            return shoppingList;
        }
        catch (Exception e){
            Log.e("Exception in shopping list", e.toString());
            return null;
        }//end of try Catch
    }//end of getNotesList
}
