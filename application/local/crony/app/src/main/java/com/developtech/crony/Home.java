package com.developtech.crony;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.developtech.crony.voice_services.VoiceServices;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    public static ArrayList al1 = new ArrayList();
    public LinearLayout news, facebook, google, whatsapp, phone, clock, instagram, snapchat;
    public ImageView imguser, iv;
    FloatingActionMenu materialDesignFAM;
    public static  ArrayList <Integer> arr;
    public void init() {
        imguser=(ImageView)findViewById(R.id.imguser);
        news = (LinearLayout) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        news.setVisibility(View.VISIBLE);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,News.class);
                startActivity(i);
            }
        });
        facebook = (LinearLayout) findViewById(R.id.facebook);
        google = (LinearLayout) findViewById(R.id.google);
        whatsapp = (LinearLayout) findViewById(R.id.whatsapp);
        phone = (LinearLayout) findViewById(R.id.phone);
        clock = (LinearLayout) findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this,"on click",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
                i.putExtra(AlarmClock.EXTRA_HOUR, 11);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 20);

                startActivity(i);
            }
        });
        clock.setVisibility(View.VISIBLE);
        instagram = (LinearLayout) findViewById(R.id.instagram);
        snapchat = (LinearLayout) findViewById(R.id.snapchat);
        if (MainScreen.flag == true) {
            al1.add(" ");
            MainScreen.flag=false;
        }
        else {
            al1 = MainScreen.al;
            for (int i = 0; i < al1.size(); i++) {
                String a = al1.get(i).toString();
                if (a.equals("facebook")) {
                    facebook.setVisibility(View.VISIBLE);
                    facebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://newsfeed"));
                                startActivity(intent);
                            }
                            catch(Exception e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")));
                            }
                        }
                    });
                }
                if (a.equals("google")) {
                    google.setVisibility(View.VISIBLE);
                    google.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                            startActivity(Intent.createChooser(intent, "Send Email"));
                        }
                    });
                }
                if (a.equals("whatsapp")) {
                    whatsapp.setVisibility(View.VISIBLE);
                    whatsapp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                            startActivity(launchIntent);
                        }
                    });
                }
                if (a.equals("phone")) {
                    phone.setVisibility(View.VISIBLE);
                    phone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(Intent.ACTION_DIAL);//to open dialer
                            startActivity(i);
                        }
                    });
                }
                if (a.equals("instagram")) {
                    instagram.setVisibility(View.VISIBLE);
                    instagram.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Uri uri = Uri.parse("http://instagram.com/_u/google");
                            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                            likeIng.setPackage("com.instagram.android");

                            try {
                                startActivity(likeIng);
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://instagram.com")));
                            }
                        }
                    });
                }
                if (a.equals("snapchat")) {
                    snapchat.setVisibility(View.VISIBLE);
                    snapchat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add"));
                            startActivity(nativeAppIntent);

                        }

                    });
                }
            }
        }
        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,ProfileView.class);
                startActivity(i);
            }
        });
    }
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String voice = "";
    private static  int feedback = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //permissions
        if(ActivityCompat.checkSelfPermission(Home.this,android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Home.this, new String[]{android.Manifest.permission.CAMERA},101);
        }
        if(ActivityCompat.checkSelfPermission(Home.this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Home.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},102);
        }
        if(ActivityCompat.checkSelfPermission(Home.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Home.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},103);
        }
        init();
        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedback = VoiceServices.checkCommand(voice);
        switch(feedback){
            case(1):{//not needed
                Toast.makeText(this, "Running", Toast.LENGTH_SHORT).show();
                break;
            }
            case(2):{//news done
                Toast.makeText(this, "News", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, News.class);
                startActivity(i);
                voice = "";
                break;
            }
            case(3):{//music player done
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                startActivity(intent);
                voice = "";
                break;
            }
            case(4):{//shopping list done
                Toast.makeText(this, "Shopping List", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, Shopping.class);
                startActivity(intent);
                voice = "";
                break;
            }
            case(5):{//facebook done
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://newsfeed"));
                    startActivity(intent);
                }
                catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")));
                }
                voice = "";
                break;
            }
            case(6):{//mail done
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                startActivity(Intent.createChooser(intent, "Send Email"));
                voice = "";
                break;
            }
            case(7):{//alarm done
                Toast.makeText(this, "Alarm", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
                i.putExtra(AlarmClock.EXTRA_HOUR, 11);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 20);

                startActivity(i);
                voice = "";
                break;
            }
            case(8):{//reminders done
                Toast.makeText(this, "Reminders", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, Reminder.class);
                startActivity(intent);
                voice = "";
                break;
            }
            case(9):{//notes making done
                Toast.makeText(this, "Note Making", Toast.LENGTH_SHORT).show();
                Intent intentToNoteMaking = new Intent(Home.this, NotesList.class);
                startActivity(intentToNoteMaking);
                voice = "";
                break;
            }

            case(10):{//whatsapp done
                Toast.makeText(Home.this, "whatsapp", Toast.LENGTH_SHORT).show();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(launchIntent);
                voice = "";
                break;
            }

            case(11):{//dialer done
                Toast.makeText(Home.this, "dialer", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Intent.ACTION_DIAL);//to open dialer
                startActivity(i);
                voice = "";
                break;
            }

            case(12):{//instagram done
                Toast.makeText(Home.this, "instagram", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://instagram.com/_u/google");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com")));
                }
                voice = "";
                break;
            }

            case(13):{//snapchat done
                Toast.makeText(Home.this, "snapchat", Toast.LENGTH_SHORT).show();
                Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add"));
                startActivity(nativeAppIntent);
                voice = "";
                break;
            }
        }//end of switch case
    }//end of onResume

    private void initComponents()
    {
        arr =new ArrayList<Integer>();
        arr.add(R.drawable.banner_slider);
        arr.add(R.drawable.slider_notes);
        arr.add(R.drawable.slider_news);
        arr.add(R.drawable.silder_horoscope);
        arr.add(R.drawable.sildder_aboutus);

        pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new adapter_viewpager(Home.this));


        //initiating components
        //imageView
        //iv=(ImageView)findViewById(R.id.iv);
        //floating buttons
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        FloatingActionButton fbMail = (FloatingActionButton) findViewById(R.id.fbMail);// mail
        FloatingActionButton fbShoppingList = (FloatingActionButton) findViewById(R.id.fbShoppingList);// shopping list
        FloatingActionButton fbNoteMaking = (FloatingActionButton) findViewById(R.id.fbNoteMaking);// note making
        FloatingActionButton fbVoice = (FloatingActionButton) findViewById(R.id.fbVoice);// voice command
        //event handling
        fbMail.setOnClickListener(this);
        fbVoice.setOnClickListener(this);
        fbNoteMaking.setOnClickListener(this);
        fbShoppingList.setOnClickListener(this);
    }//end of initComponents

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.fbMail):{//mail done
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
                break;
            }//fbMail done

            case(R.id.fbShoppingList):{
                Intent intentToNoteMaking = new Intent(Home.this, ShoppingList.class);
                startActivity(intentToNoteMaking);
                //intent to shopping list
                break;
            }//fbShoppingList done

            case(R.id.fbNoteMaking):{//note making done
                Intent intentToNoteMaking = new Intent(Home.this, NoteMaking.class);
                startActivity(intentToNoteMaking);
                break;
            }//end of fbNoteMaking

            case(R.id.fbVoice):{//voice done
                promptSpeechInput();
                break;
            }//end of fbVoice
            default:{
                break;
            }//end of default
        }//end of switch(v.getId())
    }//end of onClick(View v)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case(R.id.action2):
            {   //Intent i=new Intent(Home.this,Home.class);
                //startActivity(i);
                Toast.makeText(Home.this,"share",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"Share via"));
                break;
            }
            case(R.id.action4):
            {   //Intent i=new Intent(Home.this,Home.class);
                //startActivity(i);
                // Toast.makeText(Home.this,"logout",Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("splogin", MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("login", false);
                e.commit();

                Intent i=new Intent(Home.this,LoginPage.class);
                startActivity(i);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Speech To Text
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Speech Not Supported",Toast.LENGTH_SHORT).show();
        }
    }

    //intents
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voice = result.get(0);
                }
                break;
            }
            case(1):{
                if(resultCode == RESULT_OK){
                    Bitmap bm=(Bitmap)data.getExtras().get("data");
                    //            String image=BitMapToString(bm);
                    //            System.out.println(image);
                    //            Bitmap bm1=StringToBitMap(image);
                    iv.setImageBitmap(bm);
                }
                break;
            }
            case(2):{
                if(resultCode == RESULT_OK){
                    Uri u=data.getData();
                    try {
                        Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),u);
                        iv.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }//end of onActivityResult

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }//end of BitMapToString(Bitmap bitmap)

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }//end of StringToBitMap(String encodedString)
}
