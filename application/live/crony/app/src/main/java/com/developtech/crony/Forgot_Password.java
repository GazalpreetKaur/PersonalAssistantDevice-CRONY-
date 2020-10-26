package com.developtech.crony;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.crony.util.Utility;

import java.util.Random;
import java.util.Timer;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener{
    EditText etmobile;
    TextView tvtimer;
    Button btnforgot,btnresend;
    ProgressBar spinner;
    FrameLayout frmlayout;
    String DEFAULT_VALUE="1";
    int maximum = 9999, minimum = 1111;
    Timer mytimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initComponents();
    }

    private void initComponents() {
        etmobile = (EditText) findViewById(R.id.etmobile);
        tvtimer=(TextView)findViewById(R.id.tvtimer);
        btnforgot = (Button) findViewById(R.id.btnforgot);
        btnresend=(Button)findViewById(R.id.btnresend);
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        frmlayout=(FrameLayout)findViewById(R.id.frmlayout);
        btnforgot.setOnClickListener(this);
        btnresend.setOnClickListener(this);
        spinner.setVisibility(View.GONE);
        frmlayout.setVisibility(View.GONE);
        btnresend.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btnforgot): {
                boolean stats = isNetworkOnline();
                if (stats == false) {
                    Utility.showInSnackbar("PLease turn on mobile data or wifi",v);
                } else {
                    frmlayout.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    Random rn = new Random();
                    //int n = maximum - minimum + 1;
                    int i = rn.nextInt() % 100;
                    String otp = String.valueOf(maximum + i);
                    if (otp.length() > 4 || otp.length() == 4) {
                        String new_otp;
                        if (otp.length() == 4) {
                            new_otp = otp;
                            Toast.makeText(Forgot_Password.this, otp, Toast.LENGTH_SHORT).show();
                        } else {
                            int num = Integer.parseInt(otp);
                            new_otp = String.valueOf(num / 10);
                            Toast.makeText(Forgot_Password.this, new_otp, Toast.LENGTH_SHORT).show();
                        }
                        String mobile = etmobile.getText().toString();

                        SharedPreferences sp = getSharedPreferences("spdata", MODE_PRIVATE);
                        SharedPreferences.Editor e = sp.edit();
                        e.putString("otp", new_otp);
                        e.putString("mobile", mobile);
                        e.commit();

                        SendOtp otpobj = new SendOtp();
                        otpobj.otp(new_otp, mobile);
                        System.out.println(mobile);
                        otpobj.start();
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        SharedPreferences sp = getSharedPreferences("SP", 0);
//                                        String abc = sp.getString("otp", DEFAULT_VALUE);
//                                        System.out.println(abc);
//                                        if (!abc.isEmpty()) {
//                                            Intent i = new Intent(Forgot_Password.this, Verify_OTP.class);
//                                            startActivity(i);
//                                        }
//                                        }
//
//                                }, 100000);
                        btnforgot.setVisibility(View.GONE);
                        try {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    tvtimer.setText("waiting: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    SharedPreferences sp = getSharedPreferences("SP", Integer.parseInt(DEFAULT_VALUE));
                                    String abc = sp.getString("otp",DEFAULT_VALUE);
                                    System.out.println("timer......................" + DEFAULT_VALUE);
                                    System.out.println("..................................."+abc);
                                    if (abc.length()>4 || abc.equals(DEFAULT_VALUE)) {
                                        Toast.makeText(Forgot_Password.this, "no otp", Toast.LENGTH_SHORT).show();
                                        btnforgot.setVisibility(View.VISIBLE);

                                    } else {
                                        Intent i = new Intent(Forgot_Password.this, Verify_OTP.class);
                                        startActivity(i);
                                    }
                                }

                            }.start();
                        }
                        catch(Exception exception)
                        {
                            Toast.makeText(Forgot_Password.this,String.valueOf(exception), Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }
        }


    }



    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(Forgot_Password.this, LoginPage.class);
        startActivity(i);
        onBackPressed();
        return true;
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

}

