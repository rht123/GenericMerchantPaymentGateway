package com.example.rohit.simpleui2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by ank on 11/28/2015.
 */
public class Payments {

    Context c;
    private static final String getWalletsUrl="http://203.199.49.154:3000/getWallets";
    private static final String getSavedWallets="http://192.168.43.20:3000/getSavedWallets";
    private static final String getNotSavedWallets="http://203.199.49.154:3000/getNotSavedWallets";

    private static final String makePaymentFromSavedWalletUrl ="http://203.199.49.154:3000/makePaymentFromSavedWallet";
    private static final String makePaymentFromNotSavedWalletUrl="http://203.199.49.154:3000/makePaymentFromNotSavedWallet";
    RequestQueue requestQueue;
    boolean isPaymentSuccessFull;



    Payments(Context c){
        this.c=c;
    }
    JSONObject getWalletResponse =null,savedWalletsResponse=null,notSavedWalletsResponse=null;
    JSONObject makePaymentResponse=null;

    public  JSONObject getNotSavedWallets(String merchant_code, String username){
        if(requestQueue==null)
            requestQueue=Volley.newRequestQueue(c);

        JSONObject json= new JSONObject();

        try {
            json.put("merchant_code", merchant_code );
            json.put("username",username);
        }
        catch (Exception e){
            Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                getNotSavedWallets,json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    Toast.makeText(c,
                            "coooool"+"response"+ response.getString("savedWallets"),
                            Toast.LENGTH_LONG).show();
                    notSavedWalletsResponse =response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);
        return notSavedWalletsResponse;
    }

    public  JSONObject getSavedWallets1(String merchant_code, String username){
        if(requestQueue==null)
            requestQueue=Volley.newRequestQueue(c);

        JSONObject json= new JSONObject();

        try {
            json.put("merchant_code", merchant_code );
            json.put("username",username);
        }
        catch (Exception e){
            Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                getSavedWallets, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(c,
                            "oooolc"+response.getString("savedWallets"),
                            Toast.LENGTH_LONG).show();
                    savedWalletsResponse =response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);
        return savedWalletsResponse;
    }


    public  JSONObject getSavedWallets(String merchant_code, String username){
       if(requestQueue==null)
           requestQueue=Volley.newRequestQueue(c);

       JSONObject json= new JSONObject();

       try {
           json.put("merchant_code", merchant_code );
           json.put("username",username);
       }
       catch (Exception e){
           Toast.makeText(c,
                   "Error: " + e.getMessage(),
                   Toast.LENGTH_LONG).show();
       }

       JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
               getSavedWallets,json, new Response.Listener<JSONObject>() {

           @Override
           public void onResponse(JSONObject response) {
               try {
                   savedWalletsResponse =response;
               } catch (Exception e) {
                   e.printStackTrace();
                   Toast.makeText(c,
                           "Error: " + e.getMessage(),
                           Toast.LENGTH_LONG).show();
               }

           }
       }, new Response.ErrorListener() {

           @Override
           public void onErrorResponse(VolleyError error) {
               // VolleyLog.d(TAG, "Error: " + error.getMessage());
               Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                       error.getCause()+
                       error.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });

       requestQueue.add(jsonObjReq);
       return savedWalletsResponse;
   }



    public  JSONObject getWallets(String merchant_code, String username){
        if(requestQueue==null)
            requestQueue=Volley.newRequestQueue(c);

        JSONObject json= new JSONObject();

        try {
            json.put("merchant_code", merchant_code );
            json.put("username",username);
        }
        catch (Exception e){
         Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                getWalletsUrl,json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    getWalletResponse =response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);
        return getWalletResponse;
    }


    public JSONObject makePaymentFromSavedWallet(String merchant_code,String customer_username,
                                                 String walletId,int amount){
        JSONObject json= new JSONObject();

        try {
            json.put("merchant_code", merchant_code );
            json.put("customer_username",customer_username);
            json.put("walletId",walletId);
            json.put("amount",amount);

             //conver amount into string or not ?
        }
        catch (Exception e){
            Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                makePaymentFromSavedWalletUrl,json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    makePaymentResponse =response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);
        return makePaymentResponse;
    }


    public JSONObject makePaymentFromNotSavedWallet(String merchant_code,String customer_username,
                                                 String walletId,int amount,String walletDetailUsername,
                                                    String walletDetailPassword,boolean isChecked){
        JSONObject json= new JSONObject();

        try {
            json.put("merchant_code", merchant_code );
            json.put("customer_username",customer_username);
            json.put("walletId",walletId);
            json.put("amount",amount);
            json.put("isChecked",isChecked);
            json.put("walletDetailUsername",walletDetailUsername);
            json.put("walletDetailPassword",walletDetailPassword);

            //conver amount into string or not ?
        }
        catch (Exception e){
            Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                makePaymentFromNotSavedWalletUrl,json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    makePaymentResponse =response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);
        return makePaymentResponse;
    }

    /*public void makePostRequest(){

    *//*    Toast.makeText(getApplicationContext(),
                "validzzzzzzzzzzzzzzzzzzzzzz" +
                        "zz",
                Toast.LENGTH_LONG).show();
     *//*

        RequestQueue queue = Volley.newRequestQueue(c);

        JSONObject json= new JSONObject();

        try {
            json.put("user", "hi");
            json.put("pass","co");
        }
        catch (Exception e){
      *//*      Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();*//*
        }



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                makePaymentFromNotSavedWalletUrl,json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                makePaymentResponse=response;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(c,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,"erooorrr"+error.networkResponse.toString()+" "+
                        error.getCause()+
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                // hidepDialog();
            }
        });

        queue.add(jsonObjReq);
    }*/
}
