package com.developtech.crony;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Divya on 06-05-2017.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        if(!isInEditMode())
        {
            Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"myfont/FaktSoftPro-Medium.ttf");
            setTypeface(tf);
        }
    }
}
