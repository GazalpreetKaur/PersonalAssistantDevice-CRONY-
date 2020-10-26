package com.developtech.crony;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.crony.util.ALLURL;
import com.developtech.crony.util.RestClient;
import com.developtech.crony.util.ResultModel;
import com.developtech.crony.util.UserApi;
import com.developtech.crony.util.UserBean;
import com.developtech.crony.util.Utility;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditProfile extends AppCompatActivity {
    private ImageView userprofileimg;
    private    String username,mobile,location,email,name,dob;
    private MyTextView txtdob;
    private Button btnSave,btncancel;
    MyButton btndob;
    private MyEditText etuser,etmobile,etemail,etlocation,etname;
    private int date,month,year;
    public void init()
    {
        System.out.println("xcvbnm,234567890-xcvbnmwertyuk------------------------------");
        SharedPreferences sp=getSharedPreferences("spdata",MODE_PRIVATE);
        username=sp.getString("username","");
        userprofileimg=(ImageView)findViewById(R.id.userprofileimg) ;
        etuser=(MyEditText) findViewById(R.id.etuser);
        etuser.setText(username);
        etuser.setEnabled(false);
        etmobile=(MyEditText) findViewById(R.id.etmobile);
        mobile=sp.getString("mobilenumber","");
        etmobile.setText(mobile);

        etemail=(MyEditText) findViewById(R.id.etemail);
        email=sp.getString("email","");
        etemail.setText(email);

        etlocation=(MyEditText) findViewById(R.id.etlocation);
        location=sp.getString("location","");
        etlocation.setText(location);


        etname=(MyEditText)findViewById(R.id.etname);
        name=sp.getString("name","");
        etname.setText(name);

        txtdob=(MyTextView)findViewById(R.id.txtdob);
        dob=sp.getString("dob","");
        txtdob.setText(dob);

        txtdob=(MyTextView) findViewById(R.id.txtdob);
        final DatePickerDialog.OnDateSetListener ondatesetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtdob.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

            }
        };

        txtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                date = cal.get(Calendar.DATE);
                month++;
                DatePickerDialog dpd = new DatePickerDialog(EditProfile.this, ondatesetlistener, year, month, date);
                dpd.show();

            }
        });

        btnSave=(Button)findViewById(R.id.btnSave);
        btncancel=(Button)findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etemail.getText().toString().equals(""))
                {
                    Utility.showInSnackbar("Please enter email address",v);
                }
                else  if(etname.getText().toString().equals(""))
                {
                    Utility.showInSnackbar("Please enter name",v);
                }

                else  if(etmobile.getText().toString().equals(""))
                {
                    Utility.showInSnackbar("Please enter mobile",v);
                }
                else  if(etlocation.getText().toString().equals(""))
                {
                    Utility.showInSnackbar("Please enter email address",v);
                }
                else
                {
                    updateProfile(btnSave);
                }


            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToViewProfile=new Intent(EditProfile.this,ProfileView.class);
                startActivity(inToViewProfile);
                finish();


            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();


    }
    public void updateProfile(final View v)
    {
        final UserBean userBean=new UserBean();
        //userBean.setName(etname.getText().toString().trim());
       // userBean.setPassword(etpassword.getText().toString().trim());
        userBean.setUsername(username);
        userBean.setName(etname.getText().toString().trim());
        userBean.setDob(txtdob.getText().toString().trim());
        userBean.setCity(etlocation.getText().toString().trim());
        userBean.setEmail(etemail.getText().toString().trim());
        userBean.setMobile(etmobile.getText().toString().trim());
        Retrofit retrofit= RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi= retrofit.create(UserApi.class);
        Call<ResultModel> call= userApi.update(userBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel<UserBean> r=response.body();
                // UserBean userBean1=r.getResult_data();
                System.out.println(r.getResult_code()+"edit************************"+userBean.getDob());
                if(r.getResult_code()==1)
                {
                    System.out.println("abcdef-----------------------------------------------------");
                    Utility.showInSnackbar("Profile Updated Successfully", v);
                    Intent inToViewProfile=new Intent(EditProfile.this,ProfileView.class);
                    startActivity(inToViewProfile);
                    finish();
                }
                else
                {
                    Utility.showInSnackbar("Please enter valid details", v);
                }

            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Utility.showInSnackbar("Unable to Connect Try Again!!!", v);
            }

        });

        }

}
