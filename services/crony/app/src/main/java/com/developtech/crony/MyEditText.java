package com.developtech.crony;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Divya on 05-05-2017.
 */
public class MyEditText extends EditText {
    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        if(!isInEditMode())
        {
            Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"myfont/FaktSoftPro-Blond.ttf");
            setTypeface(tf);
        }
    }
}
