package com.example.rohit.simpleui2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ROHIT on 29-11-2015.
 */
public class CustomAdapter extends BaseAdapter {
    ArrayList<String>nameOfWallets;
    Context context;
    ArrayList<Integer> imageIds;
    ArrayList<String>discountsOnEachWallet;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context activity, ArrayList<Integer> images, ArrayList<String> collectionOfWallet, ArrayList<String> discounts) {
        nameOfWallets = collectionOfWallet;
        imageIds = images;
        discountsOnEachWallet = discounts;
        context=activity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nameOfWallets.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView name;
        ImageView logo;
        TextView discount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.payment_method_row, null);
        holder.name=(TextView) rowView.findViewById(R.id.nameOfWallet);
        holder.logo=(ImageView) rowView.findViewById(R.id.imageOfWallet);
        holder.discount=(TextView) rowView.findViewById(R.id.discountPerWallet);
        holder.name.setText(nameOfWallets.get(position));
        holder.logo.setImageResource(imageIds.get(position));
        holder.discount.setText(discountsOnEachWallet.get(position)+"%");

        return rowView;
    }
}
