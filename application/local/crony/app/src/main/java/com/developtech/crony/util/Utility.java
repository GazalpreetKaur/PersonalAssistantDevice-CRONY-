package com.developtech.crony.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.Arrays;

public class Utility
{
    static Context context;
    public static void  startIntent(Context source, Class destination)
    {
        Intent i=new Intent(source,destination);
        source.startActivity(i);
    }

    public static void hideKeyboard(Activity activity)
    {
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null)
        {
            view = new View(activity);
        }
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showInSnackbar(String msg, View view)
    {
        Snackbar snackbar = Snackbar.make(view,msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#a7a6a5ee"));
        snackbar.show();
    }

    /*public static boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }*/


    public static AlertDialog alertBox(Context context, String title, String[] items, TextView txt)
    {
        final TextView txt1=txt;
        final String[] selectedText = new String[1];
        final String[] item=items;
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedText[0] = Arrays.asList(item).get(which);
                txt1.setText(selectedText[0]);
            }
        });
        AlertDialog alert=builder.create();

        return alert;
    }

    public static boolean isValidEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
