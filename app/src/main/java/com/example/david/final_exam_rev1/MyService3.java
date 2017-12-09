package com.example.david.final_exam_rev1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MyService3 extends Service {
    boolean isRunning = true;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, final int startId) {
        Log.e ("<<MyService3-onStart>>", "I am alive-3!");

        Thread serviceThread = new Thread ( new Runnable(){


            public void run() {
                for(int i=0; (i< 120) & isRunning; i++) {
                    try {
                        //fake that you are very busy here
                        Thread.sleep(1000);
                        Intent intentDataForMyClient = new Intent("matos.action.GOSERVICE3");
//                        String msg = "Interest1=5," + "period1=180,"+"Interest2=2," + "period2=140,";
                        int max = 5;
                        int min = 1;
                        int max2 = 60;
                        int min2 = 120;
                        Random random = new Random();
                        int showme = min + random.nextInt(max);
                        int showme2 = min + random.nextInt(max);

                        Random random2 = new Random();
                        int showme3 = min2 + random2.nextInt(max2);
                        int showme4 = min2 + random2.nextInt(max2);

                        String interest1 = Integer.toString(showme);
                        String period1 = Integer.toString(showme3);
                        String interest2 = Integer.toString(showme2);
                        String period2 = Integer.toString(showme4);

                        ArrayList<String> msg = new ArrayList<>();
                        msg.add(interest1);
                        msg.add(period1);
                        msg.add(interest2);
                        msg.add(period2);
                        intentDataForMyClient.putExtra("service3Data", msg);
                        sendBroadcast(intentDataForMyClient);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//for

            }//run

        });
        serviceThread.start();

    }//onStart

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.e ("<<MyService3-onDestroy>>", "I am Dead-3");
        isRunning = false;
    }//onDestroy



}//MyService3