package com.example.rohit.simpleui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by ROHIT on 29-11-2015.
 */
public class FinalPaymentStepForSavedCard extends AppCompatActivity {

    String payNowUrl;
    Button payButton;
    TextView infoTv;
    ListView itemsListView;
    //String balance;
    RequestQueue requestQueue =null;
    int balance1;
    int amountToBePaid;
    JSONObject json = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment_step);
        payNowUrl =  getResources().getString(R.string.localhost) +"/balance";
        int amount        = Integer.parseInt(getResources().getString(R.string.amountToBePaid));
        String merchant_code = getResources().getString(R.string.merchant_code);

        String valletname = getIntent().getExtras().getString("walletname");

        String username      = getResources().getString(R.string.username);
        amountToBePaid = Integer.parseInt(getResources().getString(R.string.amountToBePaid));

        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);

        try {
            json.put("merchant_code",merchant_code);
            json.put("valletname",valletname);
            json.put("username",username);


        }
        catch (Exception e){

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        payButton = (Button)findViewById(R.id.payNowButton);
        infoTv = (TextView) findViewById(R.id.infoAboutBalance);
        itemsListView = (ListView) findViewById(R.id.cartItemList);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amountToBePaid>balance1){
                    Toast.makeText(FinalPaymentStepForSavedCard.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                }
                else{


                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                            payNowUrl, json, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(FinalPaymentStepForSavedCard.this,
                                        "PAYMENT SUCCESSFULL", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(FinalPaymentStepForSavedCard.this, MainActivity.class);
                                startActivity(i);


                            } catch (Exception e) {

                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // VolleyLog.d(TAG, "Error: " + error.getMessage());
                        }
                    });

                    requestQueue.add(jsonObjReq);

                }
            }


        });
    }

}

