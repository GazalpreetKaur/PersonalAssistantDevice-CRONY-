package com.developtech.crony;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reminder extends AppCompatActivity implements View.OnClickListener {
    private MyTextView txtdate,txttime;
    private MyButton btndate,btntime,btnrem;
    private MyEditText etrem;
    private Calendar cal;
    private Calendar current;
    private  int day,month,year;
    private int hours,min,second;
    public void init()
    {
        txtdate=(MyTextView)findViewById(R.id.txtdate);
        txttime=(MyTextView)findViewById(R.id.txttime);
        btndate=(MyButton)findViewById(R.id.btndate);
        etrem=(MyEditText)findViewById(R.id.etrem);
        btndate.setOnClickListener(this);
        btntime=(MyButton)findViewById(R.id.btntime);
        btntime.setOnClickListener(this);
        btnrem=(MyButton)findViewById(R.id.btnrem);
        btnrem.setOnClickListener(this);
        cal =Calendar.getInstance();
        current=Calendar.getInstance();
        hours=(cal.get(Calendar.HOUR_OF_DAY));
        min=(cal.get(Calendar.MINUTE));
        second=(cal.get(Calendar.SECOND));
        day=(cal.get(Calendar.DAY_OF_MONTH));
        year=(cal.get(Calendar.YEAR));
        month=(cal.get(Calendar.MONTH));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btndate:
            {
                DatePickerDialog dpd=new DatePickerDialog(Reminder.this,onr,year,month,day);
                dpd.show();

                break;
            }
            case R.id.btntime:
            {
                TimePickerDialog tpd=new TimePickerDialog(Reminder.this,otl,hours,min,false);
                tpd.show();
                break;
            }
            case R.id.btnrem:
            {
                AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent i=new Intent(Reminder.this,Reminder_1.class);
                SharedPreferences sp=getSharedPreferences("spdata",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.putString("name",etrem.getText().toString());
                e.commit();
                PendingIntent pi=PendingIntent.getActivity(Reminder.this,101,i,0);
                am.set(AlarmManager.RTC,current.getTimeInMillis(),pi);
                Toast.makeText(Reminder.this,"reminder set",Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }

    }
    private DatePickerDialog.OnDateSetListener onr=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
            year=year1;
            month=month1;
            day=dayOfMonth;
            Toast.makeText(Reminder.this,"date seleted", Toast.LENGTH_SHORT).show();
            txtdate.setText(String.valueOf(dayOfMonth+"-"+(month+1)+"-"+year));
            current.set(Calendar.DAY_OF_MONTH,view.getDayOfMonth());
            current.set(Calendar.MONTH,view.getMonth());
            current.set(Calendar.YEAR,view.getYear());
        }
    };

    private TimePickerDialog.OnTimeSetListener otl=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hours=hourOfDay;
                    min=minute;
                    txttime.setText(String.valueOf(hourOfDay+":"+minute));
                    current.set(Calendar.HOUR_OF_DAY,view.getCurrentHour());
                    current.set(Calendar.MINUTE,view.getCurrentMinute());
                }
            };
}
