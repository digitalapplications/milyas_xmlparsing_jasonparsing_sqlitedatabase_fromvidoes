package com.example.l400.xmlparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by l400 on 10/5/2016.
 */
public class TourListAdapter extends ArrayAdapter<Flower> {
    Context context ;
    List<Flower> flower;


    public TourListAdapter(Context context, List<Flower> flower) {
        super(context,android.R.id.content, flower);
        this.flower = flower;

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutinflater.inflate(R.layout.listitemtour,null);
        Flower flowr= flower.get(position);
        TextView text = (TextView) view.findViewById(R.id.nametext);
        text.setText(flowr.getName());
        TextView id= (TextView) view.findViewById(R.id.nametext);
        id.setText(flowr.getType());
        ImageView imag =(ImageView)view.findViewById(R.id.image);
        int re = context.getResources().getIdentifier(flowr.getName(),"drawable",context.getPackageName());
        imag.setImageResource(re);


        return view;
    }
}



