package com.developtech.crony;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Divya on 06-05-2017.
 */
public class MyButton extends Button {
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        if(!isInEditMode())
        {
            Typeface tf= Typeface.createFromAsset(getContext().getAssets(),"myfont/FaktSoftPro-Normal.ttf");
            setTypeface(tf);
        }
    }
}
