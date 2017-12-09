package com.example.david.final_exam_rev1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.json.JSONObject;

import java.text.DecimalFormat;
//
//
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.*;
//
//public class LoanCalculatorActivity extends Activity {
//    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
//    private TextView mMontlyPaymentResult, mTotalPaymentsResult;
//
//    /** Initializes the app when it is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////try{
////        String baseUrl = "http://10.12.17.61:8080/loan-calculator";
////        new LoanCalculatorActivity.HttpAsyncTask().execute(baseUrl, jsonString.toString());
////
////
////    } catch(Exception jse) {
////
////        mMontlyPaymentResult.setText("JSON exception: " +jse.getLocalizedMessage());
////        mTotalPaymentsResult.setText("");
////    }
//        setContentView(R.layout.activity_main);
//        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
//        mInterestRate = (EditText)findViewById(R.id.interest_rate);
//        mLoanPeriod = (EditText)findViewById(R.id.loan_period);
//        mMontlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result);
//        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payments_result);
//    }
//    private class HttpAsyncTask extends AsyncTask<String, String, String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            // Getting username and password from user input
//            String jsonString = "";
//
//
//            try {
//                jsonString = HttpUtils.urlContentPost(params[0],"loanInputs", params[1]);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.out.println(jsonString);
//            return jsonString;
//        }
//
//        @Override
//        protected void onPostExecute(String result){
//            try{
//
//                JSONObject jsonResult = new JSONObject(result);
//                mMontlyPaymentResult.setText(jsonResult.getString("formattedMonthlyPayment"));
//                mTotalPaymentsResult.setText(jsonResult.getString("formattedTotalPayments"));
//                mLoanAmount.setText(jsonResult.getString("loanAmount"));
//                mInterestRate.setText(jsonResult.getString("annualInterestRateInPercent"));
//                mLoanPeriod.setText(jsonResult.getString("loanPeriodInMonths"));
//            } catch (Exception e) {
//                mMontlyPaymentResult.setText("Illegal base URL");
//                mTotalPaymentsResult.setText("");
//
//                mMontlyPaymentResult.setText("Server error: " + e);
//                mTotalPaymentsResult.setText("");
//
//                mMontlyPaymentResult.setText("JSON exception: " +e.getLocalizedMessage());
//                mTotalPaymentsResult.setText("");
//            }
//        }
//    }
//
//    public void showLoanPayments(View clickedButton) {
//
//        double loanAmount = Integer.parseInt(mLoanAmount.getText().toString());
//        double interestRate = (Integer.parseInt(mInterestRate.getText().toString()));
//        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());
//        double r = interestRate/1200;
//        double r1 = Math.pow(r+1,loanPeriod);
//
//        double monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
//        double totalPayment = monthlyPayment * loanPeriod;
//
//        mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
//        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));
//    }
//}

//package com.example.david.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

//import org.apache.http.client.ClientProtocolException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/** Demonstrates the use of JSON for communicating with a remote HTTP server.
 *  Note that the JSON version that is built into Android is a bit obsolete.
 *  In particular, it lacks a JSONObject constructor that lets you pass a bean.
 *  So, on the Android side we use a Map instead, but the server-side code uses the simpler
 *  and more modern constructor.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class LoanCalculatorActivity extends Activity {
    private EditText mBaseUrl, mLoanAmount, mInterestRate, mLoanPeriod, name,
            mLoanAmount2, mInterestRate2, mLoanPeriod2, name2, address1, address2;
    private TextView mMontlyPaymentResult, mTotalPaymentsResult,mMontlyPaymentResult2, mTotalPaymentsResult2;
    DatabaseHandler db = new DatabaseHandler(this);
    ComponentName service;
    Intent intentMyService3;
    BroadcastReceiver receiver;



    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        address1 = (EditText)findViewById(R.id.address);

        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
        mInterestRate = (EditText)findViewById(R.id.interest_rate);
        mLoanPeriod = (EditText)findViewById(R.id.loan_period);
        mMontlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result);
        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payments_result);

        name2 = (EditText)findViewById(R.id.name2);
        address2 = (EditText)findViewById(R.id.address2);
        mLoanAmount2 = (EditText)findViewById(R.id.loan_amount2);
        mInterestRate2 = (EditText)findViewById(R.id.interest_rate2);
        mLoanPeriod2 = (EditText)findViewById(R.id.loan_period2);
        mMontlyPaymentResult2 = (TextView)findViewById(R.id.monthly_payment_result2);
        mTotalPaymentsResult2 = (TextView)findViewById(R.id.total_payments_result2);

        intentMyService3 = new Intent(this, MyService3.class);
        service = startService(intentMyService3);

        // register & define filter for local listener
        IntentFilter mainFilter = new IntentFilter("matos.action.GOSERVICE3");
        receiver = new LoanCalculatorActivity.MyMainLocalReceiver();
        registerReceiver(receiver, mainFilter);

        }

    public void stop(View clickedButton) {
        // assume:  v.getId() == R.id.btnStopService
        try {

            stopService(intentMyService3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(View clickedButton) {
        // assume:  v.getId() == R.id.btnStopService
//        parse.setEnabled(false);

//                new MyService();
        // do slow XML reading in a separated thread
        Integer xmlResFile = R.xml.person;
//                myService.onStartCommand();
        new backgroundAsyncTask().execute(xmlResFile);
    }
    public class MyMainLocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context localContext, Intent callerIntent) {
            ArrayList serviceData = callerIntent.getStringArrayListExtra("service3Data");

            mInterestRate.setText(serviceData.get(0).toString());
            mLoanPeriod.setText(serviceData.get(1).toString());
            mInterestRate2.setText(serviceData.get(2).toString());
            mLoanPeriod2.setText(serviceData.get(3).toString());
        }
    }//MyMainLocalReceiver

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            stopService(intentMyService3);
            unregisterReceiver(receiver);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void showLoanPayments(View clickedButton) {
        try {

            stopService(intentMyService3);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String amount = mLoanAmount.getText().toString();
        String rate = mInterestRate.getText().toString();
        String months = mLoanPeriod.getText().toString();
        LoanInputs inputs = new LoanInputs(amount, rate, months);
        JSONObject inputsJson = new JSONObject(inputs.getInputMap());
        String base = "http://10.12.17.61:8080/loan-calculator";

        try {
            new HttpAsyncTask().execute(base, inputsJson.toString());

        } catch(Exception jse) {

            mMontlyPaymentResult.setText("JSON exception: " +jse.getLocalizedMessage());
            mTotalPaymentsResult.setText("");
        }
        try{
            wait(10000);
        }
        catch (Exception e){
            System.out.println("");
        }
        String amount2 = mLoanAmount2.getText().toString();
        String rate2 = mInterestRate2.getText().toString();
        String months2 = mLoanPeriod2.getText().toString();
        LoanInputs inputs2 = new LoanInputs(amount2, rate2, months2);
        JSONObject inputsJson2 = new JSONObject(inputs2.getInputMap());
        String base2 = "http://10.12.17.61:8080/loan-calculator";
        try {
            new HttpAsyncTask2().execute(base2, inputsJson2.toString());

        } catch(Exception jse) {

            mMontlyPaymentResult2.setText("JSON exception: " +jse.getLocalizedMessage());
            mTotalPaymentsResult2.setText("");
        }
    }


    private class HttpAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String jsonString = "";

            try {
                jsonString = HttpUtils.urlContentPost(params[0],"loanInputs", params[1]);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(jsonString);
            return jsonString;
        }

        @Override
        protected void onPostExecute(String result){
            try{
                System.out.println("testing");
                JSONObject jsonResult = new JSONObject(result);
                mMontlyPaymentResult.setText(jsonResult.getString("formattedMonthlyPayment"));
                mTotalPaymentsResult.setText(jsonResult.getString("formattedTotalPayments"));
                mLoanAmount.setText(jsonResult.getString("loanAmount"));
            } catch (Exception e) {
                System.out.println("ecxeption");
                mMontlyPaymentResult.setText("Illegal base URL");
                mTotalPaymentsResult.setText("");

                mMontlyPaymentResult.setText("Server error: " + e);
                mTotalPaymentsResult.setText("");

                mMontlyPaymentResult.setText("JSON exception: " +e.getLocalizedMessage());
                mTotalPaymentsResult.setText("");
            }
        }
    }

    private class HttpAsyncTask2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // Getting username and password from user input
            String jsonString = "";

            try {
                jsonString = HttpUtils.urlContentPost(params[0],"loanInputs", params[1]);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(jsonString);
            return jsonString;
        }

        @Override
        protected void onPostExecute(String result){
            try{

                JSONObject jsonResult2 = new JSONObject(result);

                mMontlyPaymentResult2.setText(jsonResult2.getString("formattedMonthlyPayment"));
                mTotalPaymentsResult2.setText(jsonResult2.getString("formattedTotalPayments"));
                mLoanAmount2.setText(jsonResult2.getString("loanAmount"));

            } catch (Exception e) {
                mMontlyPaymentResult2.setText("Illegal base URL");
                mTotalPaymentsResult2.setText("");

                mMontlyPaymentResult2.setText("Server error: " + e);
                mTotalPaymentsResult2.setText("");

                mMontlyPaymentResult2.setText("JSON exception: " +e.getLocalizedMessage());
                mTotalPaymentsResult2.setText("");
            }
        }
    }


    public class backgroundAsyncTask extends
            AsyncTask<Integer, Void, ArrayList<String>> {

//        ProgressDialog dialog = new ProgressDialog(ActivityMain.this);

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            System.out.println("result =========");
            System.out.println(result);


            name.setText(result.get(0));
            address1.setText(result.get(1));
            mLoanAmount.setText(result.get(2));
            name2.setText(result.get(3));
            address2.setText(result.get(4));
            mLoanAmount2.setText(result.get(5));

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog.setMessage("Please wait...");
//            dialog.setCancelable(false);
//            dialog.show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            int xmlResFile = params[0];
            XmlPullParser parser = getResources().getXml(xmlResFile);
            ArrayList<String> a1=new ArrayList<String>();

            StringBuilder stringBuilder = new StringBuilder();
            String nodeText = "";
            String nodeName = "";
            try {
                int eventType = -1;
                while (eventType != XmlPullParser.END_DOCUMENT) {

                    eventType = parser.next();

                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        stringBuilder.append("\nSTART_DOCUMENT");

                    } else if (eventType == XmlPullParser.END_DOCUMENT) {
                        stringBuilder.append("\nEND_DOCUMENT");

                    } else if (eventType == XmlPullParser.START_TAG) {
                        nodeName = parser.getName();
                        stringBuilder.append("\nSTART_TAG: " + nodeName);
                        stringBuilder.append(getAttributes(parser));

                    } else if (eventType == XmlPullParser.END_TAG) {
                        nodeName = parser.getName();
                        stringBuilder.append("\nEND_TAG:   " + nodeName );

                    } else if (eventType == XmlPullParser.TEXT) {
                        nodeText = parser.getText();
                        stringBuilder.append("\n    TEXT: " + nodeText);
                        System.out.println("'nodeText!!!!!!!!! " + nodeText);

                        if (nodeText != null) {
                            a1.add(nodeText);
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("<<PARSING ERROR>>", e.getMessage());

            }
            return a1;
        }// doInBackground

        private String getAttributes(XmlPullParser parser) {
            StringBuilder stringBuilder = new StringBuilder();
            // trying to detect inner attributes nested inside a node tag
            String name = parser.getName();
            if (name != null) {
                int size = parser.getAttributeCount();

                for (int i = 0; i < size; i++) {
                    String attrName = parser.getAttributeName(i);
                    String attrValue = parser.getAttributeValue(i);
                    stringBuilder.append("\n    Attrib <key,value>= "
                            + attrName + ", " + attrValue);
                }

            }
            return stringBuilder.toString();

        }// innerElements

    }// backroundAsyncTask

}
