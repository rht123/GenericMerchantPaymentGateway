package com.example.rohit.simpleui2;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ROHIT on 28-11-2015.
 */
public class NewCardFragment extends Fragment {

    TextView creditCard, heading;
    ListView listViewNewCards;
    Payments payment;
    RequestQueue requestQueue;
    JSONObject notSavedWalletsResponse = null;
    JSONObject  jsonObj;
    JSONArray array;
    JSONObject innerObj;
    ArrayList<String> collectionOfWallet = new ArrayList<String>();
    ArrayList<Integer> images = new ArrayList<Integer>();
    ArrayList<String> discounts = new ArrayList<String>();
    private static  String getNotSavedWallets;
    public  void getNotSavedWallets(String merchant_code, String username) {
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
                getNotSavedWallets, json, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                try {


                    notSavedWalletsResponse = response;


                    array = response.getJSONArray("notSavedWallets");


                    for(int i=0;i<array.length();i++) {
                        innerObj = array.getJSONObject(i);
                        String walletName = innerObj.getString("wallet");
                        String individualDiscount = innerObj.getString("discount");
                        collectionOfWallet.add(walletName);
                        discounts.add(individualDiscount);
                        images.add(getActivity().getResources().getIdentifier(walletName.replaceAll("\\s+","").toLowerCase(), "drawable", getActivity().getPackageName()));

                    }

                    listViewNewCards.setAdapter(new CustomAdapter(getActivity(),images,collectionOfWallet,discounts));

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
        getNotSavedWallets=getActivity().getResources().getString(R.string.localhost)+"/getNotSavedWallets";


        View v = inflater.inflate(R.layout.new_cards, container, false);
        //creditCard = (TextView) v.findViewById(R.id.textview);
        payment= new Payments(getActivity());


        heading = (TextView) v.findViewById(R.id.newCardHeader);
        listViewNewCards = (ListView)v.findViewById(R.id.listNewCard);

        String merchant_code = getResources().getString(R.string.merchant_code);
        String username = getResources().getString(R.string.username);
        //String[] collectionOfWallet = new String[]{"1","2"};

        getNotSavedWallets(merchant_code, username);


        heading.setBackgroundColor(Color.rgb(150, 150, 150));
        listViewNewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), WalletGatewayDetailsPayU.class);
                i.putExtra("walletname",collectionOfWallet.get(position).toString());
                startActivity(i);
            }
        });
        return v;
    }
}
