package com.developtech.crony;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.developtech.crony.util.ALLURL;
import com.developtech.crony.util.RestClient;
import com.developtech.crony.util.ResultModel;
import com.developtech.crony.util.SpUtility;
import com.developtech.crony.util.UserApi;
import com.developtech.crony.util.UserBean;
import com.developtech.crony.util.Utility;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginPage extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private EditText etusername, etpassword;
    Button btnsignin,btnfblogin;
    TextView tvforgotpassword,tvsignup;
    private SignInButton signInButton;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    FrameLayout frm;
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
        frm=(FrameLayout)findViewById(R.id.frm);
        frm.setVisibility(View.GONE);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_WIDE);
//        signInButton.setScopes(gso.getScopeArray());
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        signInButton.setOnClickListener(this);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        System.out.println("opr "+opr);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        }
//    }
//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            System.out.println("if "+data.getExtras());
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            System.out.println("result "+result.getSignInAccount());
//            handleSignInResult(result);
//        }
//    }
//    private void handleSignInResult(GoogleSignInResult result) {
//        System.out.println("GoogleSignInResult "+result.getSignInAccount());
//        if (result.isSuccess()) {
//            // Signed in successfully, show authenticated UI.
//            GoogleSignInAccount acct = result.getSignInAccount();
//            etusername.setText(acct.getDisplayName());
//           // etusername.setText(acct.getEmail());
//        }else {
//            //If login fails
//            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
//        }
//    }


    @Override
    public void onClick(View v) {
        String username=etusername.getText().toString();
        String password=String.valueOf(etpassword.getText().toString());
        switch (v.getId()) {
            case (R.id.btnsignin):
            {
                Utility.hideKeyboard(this);
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
                    if (isNetworkOnline()==false)
                    {
                      Utility.showInSnackbar("No Connection",v);
                    }
                    else {
                        frm.setVisibility(View.VISIBLE);
                        login(btnsignin);
                    }
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
                startActivity(intentsignup);
            }
            case R.id.sign_in_button:
            {
               /* signIn();*/
                break;
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    public void login(final View v)
    {
        UserBean userBean=new UserBean();
        userBean.setUsername(etusername.getText().toString().trim());
        userBean.setPassword(etpassword.getText().toString().trim());
        Retrofit retrofit= RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi= retrofit.create(UserApi.class);
        Call<ResultModel<UserBean>> call= userApi.login(userBean);
        call.enqueue(new Callback<ResultModel<UserBean>>() {
            @Override
            public void onResponse(Call<ResultModel<UserBean>> call, retrofit2.Response<ResultModel<UserBean>> response) {
                ResultModel<UserBean> r=response.body();
                UserBean userBean1=r.getResult_data();
                System.out.println(r.getResult_code()+"************************");
                if(r.getResult_code()==1)
                {
                    Intent intToMain = new Intent(LoginPage.this, MainScreen.class);
                    SharedPreferences sp= getSharedPreferences("spdata",MODE_PRIVATE);
                    SharedPreferences.Editor e =sp.edit();
                    e.putString("username",userBean1.getUsername());
                    e.commit();

                    SharedPreferences sp1 = getSharedPreferences("splogin", MODE_PRIVATE);
                    SharedPreferences.Editor e1 = sp1.edit();
                    e1.putBoolean("login", true);
                    e1.commit();
                    frm.setVisibility(View.GONE);
                    Utility.hideKeyboard(LoginPage.this);
                    finish();
                    startActivity(intToMain);
                }
                else
                {
                    Utility.showInSnackbar("Please enter valid details", v);
                    frm.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResultModel<UserBean>> call, Throwable t) {
                t.printStackTrace();
                Utility.showInSnackbar("Unable to Connect Try Again!!!", v);
                frm.setVisibility(View.GONE);
        }
        });

    }
    //networkcheck
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
        //end of check

    }

}