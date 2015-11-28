package com.example.rohit.simpleui2;

import android.app.Fragment;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import org.json.JSONException;
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
    ArrayList<String>values = new ArrayList<>();
    private static final String getNotSavedWallets="http://192.168.43.20:3000/getNotSavedWallets";

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

                    Toast.makeText(getActivity(),
                            "coooool" + "response" + response.getString("notSavedWallets"),
                            Toast.LENGTH_LONG).show();
                    notSavedWalletsResponse = response;


                    array = response.getJSONArray("notSavedWallets");

                    for(int i=0;i<array.length();i++) {
                        innerObj = array.getJSONObject(i);
                        String walletName = innerObj.getString("kgkjg");
                        values.add(walletName);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    listViewNewCards.setAdapter(adapter);

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


        View v = inflater.inflate(R.layout.new_cards, container, false);
        //creditCard = (TextView) v.findViewById(R.id.textview);
        payment= new Payments(getActivity());


        heading = (TextView) v.findViewById(R.id.newCardHeader);
        listViewNewCards = (ListView)v.findViewById(R.id.listNewCard);

        String merchant_code = getResources().getString(R.string.merchant_code);
        String username = getResources().getString(R.string.username);
        //String[] values = new String[]{"1","2"};

        getNotSavedWallets(merchant_code, username);


        heading.setBackgroundColor(Color.rgb(0, 0, 150));
        listViewNewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listViewNewCards.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), WalletGatewayDetailsPayU.class);
                startActivity(i);
            }
        });
        return v;
    }
}
