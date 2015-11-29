package com.example.rohit.simpleui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class WalletGatewayDetailsPayU extends AppCompatActivity {

    CheckBox checkBox;
    Button verifyDetails;
    EditText username;
    EditText password;
    RequestQueue requestQueue =null;
    String authoriseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_gateway_details_pay_u);
        username=(EditText)findViewById(R.id.userName);
        password=(EditText)findViewById(R.id.password);
        final String walletname= getIntent().getExtras().getString("walletname");
        authoriseUrl = getResources().getString(R.string.localhost)+"/authorise";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        checkBox = (CheckBox)findViewById(R.id.saveCardCheck);
        verifyDetails = (Button) findViewById(R.id.verifyDetailsWallet);
        requestQueue = Volley.newRequestQueue(this);
        verifyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=username.getText().toString();
                String pass= password.getText().toString();
                String merchant_code = getResources().getString(R.string.merchant_code);
                String username = getResources().getString(R.string.username);

                JSONObject json = new JSONObject();

                try {

                    json.put("wallet_username", user);
                    json.put("walletId", walletname);
                    json.put("merchant_code", merchant_code);
                    json.put("wallet_password", pass);
                    json.put("username", username);//isChecked
                    json.put("isChecked", checkBox.isChecked());
                }catch (Exception e){}


                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                            authoriseUrl, json, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                String existingAmount = response.getString("balance");

                                if(status){
                                    Intent i = new Intent(WalletGatewayDetailsPayU.this, FinalPaymentStep.class);
                                    i.putExtra("balance",existingAmount);
                                    i.putExtra("valletusername",user);
                                    i.putExtra("walletname",walletname);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(WalletGatewayDetailsPayU.this, "INVAID DETAILS, PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show();
                                }

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
        });

        setSupportActionBar(toolbar);


    }

}
