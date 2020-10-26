package com.developtech.crony;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by gazal on 15-May-17.
 */
public class Adapter1 extends PagerAdapter
{
    Context context;
    public Adapter1(Context mcontext)
    {
        context=mcontext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.pager,container, false);
        ImageView iv=(ImageView)layout.findViewById(R.id.image);
        iv.setImageResource(Instruction.arr.get(position));

        if(position == (Instruction.arr.size()) -1){
            System.out.println("---------abcd-----xyz-----"+position+"----------------");
            Instruction.btnskip.setText("Get Started");
        }

        else{
            System.out.println("---------abcd-----xyz-----"+position+"----------------");
            Instruction.btnskip.setText("Skip");
        }
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount()
    {
        return Instruction.arr.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((LinearLayout) object);
    }
}