package com.example.rohit.simpleui2;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ROHIT on 28-11-2015.
 */
public class NewCardFragment extends Fragment {

    TextView creditCard, heading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.new_cards, container, false);
        creditCard = (TextView) v.findViewById(R.id.textview);
        heading = (TextView) v.findViewById(R.id.newCardHeader);
        heading.setBackgroundColor(Color.rgb(0,0,200));
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CreditCardDetails.class);
                getActivity().startActivity(i);
            }
        });
        return v;
    }
}
