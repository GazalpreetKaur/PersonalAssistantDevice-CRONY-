package com.developtech.crony;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.crony.util.Utility;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText etusername, etpassword;
    Button btnsignin;
    TextView tvforgotpassword,tvsignup;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponents();
    }

    private void initComponents() {
        etusername = (EditText) findViewById(R.id.etusername);
        etpassword = (EditText) findViewById(R.id.etpassword);
        btnsignin = (Button) findViewById(R.id.btnsignin);
        tvforgotpassword=(TextView)findViewById(R.id.tvforgotpassword);
        tvsignup=(TextView)findViewById(R.id.tvsignup);
        tvsignup.setOnClickListener(this);
        tvforgotpassword.setOnClickListener(this);
        btnsignin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String username=etusername.getText().toString();
        String password=String.valueOf(etpassword.getText().toString());
        switch (v.getId()) {
            case (R.id.btnsignin):
            {
                if (username.equals(""))
                {
                    Utility.showInSnackbar("Enter the username", v);
                }
                else if (password.equals(""))
                {
                    Utility.showInSnackbar("Enter the Password", v);
                }
                else
                {
                    System.out.println(username);
                    System.out.println(password);
                    Toast.makeText(LoginPage.this, "sign in", Toast.LENGTH_SHORT).show();
                }
               break;
            }
            case(R.id.tvforgotpassword):
            {
                Intent intent=new Intent(LoginPage.this,Forgot_Password.class);
                finish();
                startActivity(intent);
                break;
            }
            case(R.id.tvsignup):
            {
                Intent intentsignup=new Intent(LoginPage.this,SignUp.class);
            }
        }
    }
}
