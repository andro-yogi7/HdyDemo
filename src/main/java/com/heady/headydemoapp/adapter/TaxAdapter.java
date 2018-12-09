package com.heady.headydemoapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.heady.headydemoapp.dao.DatabaseHelper;
import com.heady.headydemoapp.R;

/**
 * Created by YOGESH on 07-12-2018.
 */

public class TaxAdapter extends CursorAdapter{

    Cursor c;
    Context context;
    boolean requery;

    public TaxAdapter(Context context, Cursor c, boolean requery) {
        super(context, c, requery);
        this.context = context;
        this.c = c;
        this.requery = requery;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.single_category, parent, false);
        TextView lay_single = rowView.findViewById(R.id.textView);
        if(cursor.getPosition()%2==0) {
            lay_single.setBackgroundColor(Color.parseColor("#ffffff"));
         }
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String str ="";
        TaxAdapter.ViewHolder viewHolder = new TaxAdapter.ViewHolder();
        viewHolder.textDate =  view.findViewById(R.id.textView);

        viewHolder.textDate.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLOUMN_CNAME)).toUpperCase());
    }
    private class ViewHolder{
        TextView textDate;
    }
}
