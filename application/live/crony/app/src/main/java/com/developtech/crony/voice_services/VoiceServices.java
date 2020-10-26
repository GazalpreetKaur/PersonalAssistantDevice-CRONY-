package com.developtech.crony.voice_services;

import android.util.Log;

public class VoiceServices {

    public static int checkCommand(String msg){
        int feedback = 0;
        msg = msg.toLowerCase();
        String msgsplit[] = msg.split(" ");
        Log.e("VoiceService",String.valueOf(msg));
        for(int i = 0; i<msgsplit.length; i++){
            switch (msgsplit[i]){
                case("ok"):{
                    if(msgsplit[i+1].equals("crony")){
                        feedback = 1;
                    }
                    break;
                }//ok crony

                case("news"):{
                    feedback = 2;
                    break;
                }//news

                case("music"):{
                    feedback = 3;
                    break;
                }//music

                case("shopping"):{
                    if(msgsplit[i+1].equals("list")){
                        feedback = 4;
                        break;
                    }
                    feedback = 0;
                    break;
                }//shopping

                case("facebook"):{
                    feedback = 5;
                    break;
                }//facebook
                case("fb"):{
                    feedback = 5;
                    break;
                }//facebook

                case("mail"):{
                    feedback = 6;
                    break;
                }//mail

                case("alarm"):{
                    feedback = 7;
                    break;
                }//alarm

                case("reminders"):{
                    feedback = 8;
                    break;
                }//reminders

                case("notes"):{
                    feedback = 9;
                    break;
                }//notes

                case("whatsapp"):{
                    feedback = 10;
                    break;
                }//notes

                case("dialer"):{
                    feedback = 11;
                    break;
                }//notes

                case("instagram"):{
                    feedback = 12;
                    break;
                }//notes

                case("snapchat"):{
                    feedback = 13;
                    break;
                }//notes

                default:{
                    feedback = 0;
                }
            }//end of switch

            if(feedback != 0){
                break;
            }
        }//end of traversing msgsplit

        return feedback;
    }//end of checkCommand()


}
