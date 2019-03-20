package com.example.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class DepenseAdapter extends ArrayAdapter<NewDepense> {

    private ArrayList <NewDepense> arrayList;
    private Context ctx;
    private int item;
    public DepenseAdapter (@NonNull Context context, int ressource, ArrayList<NewDepense> myarrayList) {
        super (context, ressource, myarrayList);
        this.arrayList=myarrayList;
        this.ctx=context;
        this.item=ressource; }
    public boolean isEnabled(int position)
    {return true; }

    public View getView (int position, @Nullable View convertView, ViewGroup parent){
        convertView= LayoutInflater.from(ctx).inflate(item, parent, false);
        TextView achat = convertView.findViewById((R.id.achat));
        TextView prix = convertView.findViewById((R.id.prix));
        TextView date = convertView.findViewById((R.id.dateajout));

        achat.setText(arrayList.get(position).getAchat());
        prix.setText((arrayList.get(position).getPrix()));
        date.setText(arrayList.get(position).getDate());

        return convertView;


    }}

