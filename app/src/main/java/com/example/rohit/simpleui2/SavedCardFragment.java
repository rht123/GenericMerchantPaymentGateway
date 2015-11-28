package com.example.rohit.simpleui2;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ROHIT on 28-11-2015.
 */
public class SavedCardFragment  extends Fragment {

    TextView heading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.saved_cards, container, false);
        heading = (TextView) v.findViewById(R.id.savedCardHeader);
        heading.setBackgroundColor(Color.rgb(0,200,0));
        return v;
    }
}
