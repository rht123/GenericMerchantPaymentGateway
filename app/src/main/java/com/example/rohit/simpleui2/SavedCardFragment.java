package com.example.rohit.simpleui2;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROHIT on 28-11-2015.
 */
public class SavedCardFragment  extends Fragment {

    TextView heading,message;
    ListView listViewSavedCards;
    Payments payment;
    RequestQueue requestQueue;
    JSONObject savedWalletsResponse = null;
    JSONObject  jsonObj;
    JSONArray array;
    JSONObject innerObj;
    ArrayList<String> collectionOfWallet = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<String> discounts = new ArrayList<>();

    ArrayList<String>values = new ArrayList<>();
    private static  String getSavedWallets ;

    public  void getSavedWallets(String merchant_code, String username) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getActivity());

        JSONObject json = new JSONObject();

        try {
            json.put("merchant_code", merchant_code);
            json.put("username", username);
        } catch (Exception e) {
            Toast.makeText(getActivity(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                getSavedWallets, json, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                try {

                    savedWalletsResponse = response;


                    array = response.getJSONArray("savedWallets");

                    for(int i=0;i<array.length();i++) {
                        innerObj = array.getJSONObject(i);
                        String walletName = innerObj.getString("wallet");
                        values.add(walletName);
                        String individualDiscount = innerObj.getString("discount");
                        collectionOfWallet.add(walletName);
                        discounts.add(individualDiscount);
                        images.add(getActivity().getResources().getIdentifier(walletName.replaceAll("\\s+","").toLowerCase(), "drawable", getActivity().getPackageName()));

                    }

                    if(values.size()==0){
                        message.setText("You dont have any saved cards");
                        message.setVisibility(View.VISIBLE);
                    }
                    else {
                        listViewSavedCards.setAdapter(new CustomAdapter(getActivity(), images, collectionOfWallet, discounts));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "erooorrr" + error.networkResponse.toString() + " " +
                        error.getCause() +
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjReq);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.saved_cards, container, false);
        payment = new Payments(getActivity());
        getSavedWallets=getActivity().getResources().getString(R.string.localhost)+"/getSavedWallets";
        heading = (TextView) v.findViewById(R.id.savedCardHeader);
        message = (TextView) v.findViewById(R.id.message);
        listViewSavedCards = (ListView)v.findViewById(R.id.listSavedCards);
        heading.setBackgroundColor(Color.rgb(0,200,0));

        String merchant_code = getResources().getString(R.string.merchant_code);
        String username = getResources().getString(R.string.username);
        getSavedWallets(merchant_code,username);

        try {
            JSONObject  jsonObj =new JSONObject();
           // String apple = payment.getSavedWallets1(merchant_code, username).getString("savedWallets");
            JSONArray array = jsonObj.getJSONArray("savedWallets");

            for(int i=0;i<array.length();i++) {
                JSONObject innerObj = array.getJSONObject(i);
                String walletName = innerObj.getString("kgkjg");
                collectionOfWallet.add(walletName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        listViewSavedCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),FinalPaymentStepForSavedCard.class);
                i.putExtra("walletname",collectionOfWallet.get(position));
                startActivity(i);
            }
        });
        return v;
    }
}
