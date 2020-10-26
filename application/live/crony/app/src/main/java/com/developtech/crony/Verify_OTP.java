package com.developtech.crony;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Verify_OTP extends AppCompatActivity implements View.OnClickListener {
    EditText et1, et2, et3, et4;
    String DEFAULT_VALUE = "not Found";
    TextView tvmobile;
    String msg,memberFieldString;;
    Button btnverify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (ActivityCompat.checkSelfPermission(Verify_OTP.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(Verify_OTP.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Verify_OTP.this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                ActivityCompat.requestPermissions(Verify_OTP.this, new String[]{Manifest.permission.READ_SMS}, 2);

            }
        }
        initComponents();
    }

    private void initComponents() {
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        tvmobile = (TextView) findViewById(R.id.tvmobile);
        btnverify = (Button) findViewById(R.id.btnverify);
        btnverify.setOnClickListener(this);
        SharedPreferences sp;
        sp = getSharedPreferences("spdata", MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        String mobile = sp.getString("mobile", DEFAULT_VALUE);
        tvmobile.setText(mobile);
        SharedPreferences sp1=getSharedPreferences("SP",0);
        String msgBody=sp1.getString("otp",DEFAULT_VALUE);
        msg=msgBody;
        System.out.println("verify msg"+msg);
        int num=Integer.parseInt(msg);
        System.out.println(num);
        int remainder;
        int num1=0;
        while(num != 0)
        {
            remainder = num%10;
            num1 = num1*10 + remainder;
            num /= 10;
        }
        System.out.println("num1"+num1);
        et1.setText(String.valueOf(num1%10));
        int num2=num1/10;
        et2.setText(String.valueOf(num2%10));
        int num3=num2/10;
        et3.setText(String.valueOf(num3%10));
        int num4=num3/10;
        et4.setText(String.valueOf(num4));
        String concat_otp=et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString();
        Toast.makeText(Verify_OTP.this, "concatenated otp "+concat_otp, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(Verify_OTP.this, Forgot_Password.class);
        startActivity(i);
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btnverify): {
                SharedPreferences sp;
                sp = getSharedPreferences("SP", MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                String in_otp = sp.getString("otp", DEFAULT_VALUE);
                if (msg.equals(in_otp))
                {
                    e.clear();
                    e.commit();
                    Toast.makeText(Verify_OTP.this, "verified", Toast.LENGTH_SHORT).show();
                   Intent  i=new Intent(Verify_OTP.this,ChangePassword.class);
                    startActivity(i);
                }
                else
                {
                    e.clear();
                    e.commit();
                    Toast.makeText(Verify_OTP.this, "not verified", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

}



