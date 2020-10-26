package com.developtech.crony;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.developtech.crony.util.ALLURL;
import com.developtech.crony.util.RestClient;
import com.developtech.crony.util.ResultModel;
import com.developtech.crony.util.UserApi;
import com.developtech.crony.util.UserBean;
import com.developtech.crony.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePassword extends AppCompatActivity {
    private MyTextView txt1,txt2,txt3;
    private MyEditText etcurrPass,etnewPass,etconfPass;
    private Button btnsave,btncancel;
    private String currentpassword,username;
    private static boolean flag;
    public void init()
    {
        txt1=(MyTextView)findViewById(R.id.txt1);
        txt2=(MyTextView)findViewById(R.id.txt2);
        txt3=(MyTextView)findViewById(R.id.txt3);
        etcurrPass=(MyEditText)findViewById(R.id.etcurrPass);
        currentpassword=etcurrPass.getText().toString().trim();
        etnewPass=(MyEditText)findViewById(R.id.etnewPass);
        etconfPass=(MyEditText)findViewById(R.id.etconfPass);
        btnsave=(Button)findViewById(R.id.btnsave);
        btncancel=(Button)findViewById(R.id.btncancel);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcurrPass.getText().toString().equals(" "))
                {
                    Toast.makeText(ChangePassword.this,"please enter current password",Toast.LENGTH_SHORT).show();
                }
                else if(etnewPass.getText().toString().equals(" "))
                {
                    Toast.makeText(ChangePassword.this,"please enter new password",Toast.LENGTH_SHORT).show();
                }
                else if (etconfPass.getText().toString().equals(" "))
                {
                    Toast.makeText(ChangePassword.this,"please confirm password",Toast.LENGTH_SHORT).show();
                }
                else if(matchCurrentPassword(currentpassword))
                {
                    Toast.makeText(ChangePassword.this,"Wrong current password",Toast.LENGTH_SHORT).show();
                }
                else if(!(etconfPass.getText().toString().equals(etnewPass.getText().toString())))
                {
                    Toast.makeText(ChangePassword.this,"Enter same new passwords in both fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    updatePassword(btnsave);
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin=new Intent(ChangePassword.this,LoginPage.class);
                startActivity(intToLogin);
                finish();
            }
        });

    }
    private void updatePassword(final View v)
    {
        UserBean userBean=new UserBean();
        userBean.setUsername(username);
        userBean.setNewpassword(etnewPass.getText().toString().trim());
        userBean.setPassword(etcurrPass.getText().toString().trim());
        Retrofit retrofit= RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi= retrofit.create(UserApi.class);
        Call<ResultModel> call= userApi.changePassword(userBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel<UserBean> r=response.body();
                UserBean userBean1=r.getResult_data();
                System.out.println(r.getResult_code()+"************************");
                if(r.getResult_code()==1)
                {
                    Utility.showInSnackbar("Password updated Successfully", v);
                    Intent intToLogin = new Intent(ChangePassword.this,LoginPage.class);
                    startActivity(intToLogin);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }
private boolean matchCurrentPassword(final String currentpassword)
{
    SharedPreferences sp=getSharedPreferences("spdata",MODE_PRIVATE);
    username=sp.getString("username","");
    UserBean objbean=new UserBean();
    objbean.setUsername(username);

    Retrofit retrofit= RestClient.build(ALLURL.APP_BASE_URL);
    UserApi userApi= retrofit.create(UserApi.class);
    Call<ResultModel<UserBean>> call= userApi.getUserData(objbean);
    call.enqueue(new Callback<ResultModel<UserBean>>(){

        @Override
        public void onResponse(Call<ResultModel<UserBean>> call, Response<ResultModel<UserBean>> response) {
            ResultModel<UserBean> r=response.body();
            UserBean userBean1=r.getResult_data();
            System.out.println(r.getResult_code()+"************************");
            if(r.getResult_code()==1)
            {
               if(userBean1.getPassword().equals(currentpassword))
               {
                   ChangePassword.flag=true;
               }
                else
               {
                   ChangePassword.flag=false;
               }
            }

        }

        @Override
        public void onFailure(Call<ResultModel<UserBean>> call, Throwable t) {
            Toast.makeText(ChangePassword.this,"An error occured please try again",Toast.LENGTH_LONG).show();
        }

    });

        return ChangePassword.flag;
}
}
