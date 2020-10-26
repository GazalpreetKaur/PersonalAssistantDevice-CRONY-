package com.developtech.crony;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by a on 11/5/17.
 */
class SendOtp extends Thread{
String new_otp;
    String mobile;
public void otp(String new_otp,String mobile)
{
    this.new_otp=new_otp;
    this.mobile=mobile;
}
    @Override
    public void run() {
        super.run();
        try{
            String otp1 = "http://api.mvaayoo.com/mvaayooapi/MessageCompose?user=adityadogra94@gmail.com:qwerty&senderID=TEST%20SMS&receipientno="+mobile+"&dcs=0&msgtxt="+new_otp+"&state=4";
            System.out.println(mobile);
            URL u = new URL(otp1);
            URLConnection urlconnection = u.openConnection();
            BufferedReader bs = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            System.out.println(".......ddd........"+bs.readLine());


        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
}
