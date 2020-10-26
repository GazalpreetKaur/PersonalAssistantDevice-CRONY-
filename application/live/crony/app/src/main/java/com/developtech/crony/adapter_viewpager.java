package com.developtech.crony;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Divya on 08-05-2017.
 */
public class adapter_viewpager extends PagerAdapter {

    Context context;
    public adapter_viewpager(Context mcontext)
    {
        context=mcontext;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.pager,container, false);
        ImageView iv=(ImageView)layout.findViewById(R.id.image);
        iv.setImageResource(Home.arr.get(position));
        container.addView(layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case(0):{
                        Intent i=new Intent(context,Shopping.class);
                        context.startActivity(i);
                        break;
                    }
                    case(1):{
                        Intent i=new Intent(context,NotesList.class);
                        context.startActivity(i);
                        break;
                    }
                    case(2):{
                        Intent i=new Intent(context,News.class);
                        context.startActivity(i);
                        break;
                    }
                    case(3):{
                        Intent i=new Intent(context,Horoscope.class);
                        context.startActivity(i);
                        break;
                    }
                    case(4):{
                        Intent i=new Intent(context,AboutUs.class);
                        context.startActivity(i);
                        break;
                    }
                }//end of switch
            }
        });
        return layout;
    }

    @Override
    public int getCount()
    {
        return Home.arr.size();
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
