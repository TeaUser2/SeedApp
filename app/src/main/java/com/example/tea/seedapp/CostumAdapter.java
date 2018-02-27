package com.example.tea.seedapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tea on 23.11.2017.
 */

public class CostumAdapter extends ArrayAdapter<DataItem> {

    Context context;
    int layoutResourceId;
    List<DataItem> data=null;




    public CostumAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DataItem> objects) {
        super(context, resource, objects);

        this.layoutResourceId=resource;
        this.context=context;
        this.data=objects;

    }
    static class DataHolder{
        ImageView IVPlant;


    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder holder =null;
        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView=inflater.inflate(layoutResourceId,null);
            holder=new DataHolder();
            holder.IVPlant=(ImageView) convertView.findViewById(R.id.IVplant);


            convertView.setTag(holder);
        }
        else{
            holder=(DataHolder)convertView.getTag();
        }

        DataItem dataItem=data.get(position);

        holder.IVPlant.setImageResource(dataItem.idItem);

        return convertView;
    }
}
