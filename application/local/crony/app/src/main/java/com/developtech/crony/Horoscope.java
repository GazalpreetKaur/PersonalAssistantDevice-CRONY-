package com.developtech.crony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Horoscope extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        init();
    }
    private void init()
    {
        wv=(WebView)findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl("File:///android_asset/body.html");
        wv.loadUrl("http://www.horoscope.com/us/index.aspx");
        wv.setWebViewClient(new WebViewClient());
    }

}
