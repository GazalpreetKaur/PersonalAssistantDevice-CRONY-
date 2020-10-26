package com.developtech.crony;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class ProfileView extends AppCompatActivity {
    private ImageView img1;
    private TextView txtuser,txtmobile,txtlocation,txtdob,txtemail,txtname;
    private MyButton btnSave;
    private ImageView imguser;
    private String username,name;
    public void init()
    {
        txtuser=(TextView)findViewById(R.id.txtuser);
        txtdob=(TextView)findViewById(R.id.txtdob);
        txtname=(TextView)findViewById(R.id.txtname);
        txtmobile=(TextView)findViewById(R.id.txtmobile);
        txtlocation=(TextView)findViewById(R.id.txtlocation);
        txtemail=(TextView)findViewById(R.id.txtemail);
        imguser=(ImageView)findViewById(R.id.userimage);
        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfileView.this,EditProfile.class);
                startActivity(i);
                finish();
            }
        });
        loadProfile();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        init();


    }
        public void loadProfile()
        {
            SharedPreferences sp=getSharedPreferences("spdata",MODE_PRIVATE);
            username=sp.getString("username","");
            System.out.println("afghjkmnsdbncdjsdskl-----------------------------------------"+username);
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
                       txtuser.setText(username);
                        txtdob.setText(userBean1.getDob());
                        txtname.setText(userBean1.getName());
                        txtlocation.setText(userBean1.getCity());
                        txtmobile.setText(userBean1.getMobile());
                        txtemail.setText(userBean1.getEmail());
                        SharedPreferences s=getSharedPreferences("spdata",MODE_PRIVATE);
                        SharedPreferences.Editor e =s.edit();
                        e.putString("mobilenumber",txtmobile.getText().toString());
                        e.putString("email",txtemail.getText().toString());
                        e.putString("location",txtlocation.getText().toString());
                        e.putString("name",txtname.getText().toString());
                        e.putString("dob",txtdob.getText().toString());
                        e.commit();
                    }


                }

                @Override
                public void onFailure(Call<ResultModel<UserBean>> call, Throwable t) {
                    Toast.makeText(ProfileView.this,"Unable to Connect please try again",Toast.LENGTH_LONG).show();
                }
            });
        }
    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(ProfileView.this, Home.class);
        startActivity(i);
        finish();
        onBackPressed();
        return true;
    }
}
