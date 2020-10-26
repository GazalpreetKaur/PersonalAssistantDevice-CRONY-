package com.developtech.crony;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.crony.util.Utility;

import java.util.Calendar;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
   EditText etname,etusername,etemail,etpassword,etconfirmpassword,etmobile;
    TextView tvdateofbirth;
    Button btnsignup;
    private int date,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
initComponents();
       }
    private void initComponents()
    {
      etname=(EditText)findViewById(R.id.etname);
      etusername=(EditText)findViewById(R.id.etuname);
      etemail=(EditText)findViewById(R.id.etemail);
      etpassword=(EditText)findViewById(R.id.etpassword);
        etconfirmpassword=(EditText)findViewById(R.id.etconfirmpassword);
        etmobile=(EditText)findViewById(R.id.etmobile);
        tvdateofbirth=(TextView) findViewById(R.id.tvdateofbirth);
        btnsignup=(Button)findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name = etname.getText().toString();
        String username = etusername.getText().toString();
        String password=String.valueOf(etpassword.getText().toString());
        String confirmpassword=String.valueOf(etconfirmpassword.getText().toString());
        String email = etemail.getText().toString().trim();
        String mobile = etmobile.getText().toString();
        String dob = tvdateofbirth.getText().toString();
        tvdateofbirth.setOnClickListener(this);
        switch (v.getId())
        {
            case (R.id.tvdateofbirth):
            {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                date = cal.get(Calendar.DATE);
                DatePickerDialog dpd = new DatePickerDialog(SignUp.this, ondatesetlistener, year, month, date);
                dpd.show();
            }


            case (R.id.btnsignup):
            {
                if (name.equals(""))
                {
                    Utility.showInSnackbar("Enter the name", v);
                }
                else if (username.equals(""))
                {
                    Utility.showInSnackbar("Enter the username", v);
                }
                else if (password.equals(""))
                {
                    Utility.showInSnackbar("Enter the password", v);
                }
                else if (confirmpassword.equals(""))
                {
                    Utility.showInSnackbar("Enter the confirmPassword", v);
                }
                else if (email().equals(false))
                {
                    Utility.showInSnackbar("Enter the correct email", v);
                }
                else if (mobile.equals(""))
                {
                    Utility.showInSnackbar("Enter the mobile", v);
                }
                else if (dob.equals(""))
                {
                    Utility.showInSnackbar("select Date of Birth", v);
                }
                else
                {
                    if (password.equals(confirmpassword)) {
                        Toast.makeText(SignUp.this, "correct password", Toast.LENGTH_SHORT).show();
                    } else {
                        Utility.showInSnackbar("Please enter both the password same", v);
                        System.out.println(name);

                    }
                }
                break;
            }

        }
    }
    protected Boolean email()
    {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches())
        {
            return true;
        }
        else
            return false;
    }
    private DatePickerDialog.OnDateSetListener ondatesetlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            tvdateofbirth.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

        }
    };
}

