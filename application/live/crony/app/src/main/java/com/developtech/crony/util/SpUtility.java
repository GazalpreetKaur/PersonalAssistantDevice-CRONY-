package com.developtech.crony.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Amandeep on 5/9/2017.
 */
public class SpUtility
{
    public static SharedPreferences getSharedPreference(Context context)
    {
        return context.getSharedPreferences("SP",Context.MODE_PRIVATE);
    }

    public static void clearSharedPreference(Context context,String key)
    {
        SharedPreferences sp=context.getSharedPreferences("SP",0);
        sp.edit().remove(key).commit();
    }
}
