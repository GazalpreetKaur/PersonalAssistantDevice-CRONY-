package com.developtech.crony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.developtech.crony.util.SpUtility;

public class MyReceiver extends BroadcastReceiver
{   int DEFAULTVALUE=1;
    final SmsManager sms = SmsManager.getDefault();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";
    public MyReceiver()
    {}

    @Override
    public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                SmsMessage[] msgs;
                String sender;
                if (bundle != null) {
                    try {

                        Forgot_Password fp=new Forgot_Password();
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        msgs = new SmsMessage[pdus.length];
                        for (int i = 0; i < msgs.length; i++) {
                            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                            sender = msgs[i].getOriginatingAddress();
                            String msgBody = msgs[i].getMessageBody();
                            SharedPreferences sp=context.getSharedPreferences("SP",DEFAULTVALUE);
                            SharedPreferences.Editor e=sp.edit();
                            e.putString("otp",msgBody);
                            e.commit();
                            System.out.println("msg body my receiver"+msgBody);
                            System.out.println(sender);
                            // you have the sms content in the msgBody
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
}

