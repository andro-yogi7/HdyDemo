package com.heady.headydemoapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.heady.headydemoapp.R;
import com.heady.headydemoapp.dao.DatabaseHelper;
import com.heady.headydemoapp.view.activity.ProductDetailActivity;

/**
 * Created by YOGESH on 09-12-2018.
 */

public class CountAdapter extends CursorAdapter {
    Cursor c;
    Context context;
    boolean requery;

    public CountAdapter(Context context, Cursor c, boolean requery) {
        super(context, c, requery);
        this.context = context;
        this.c = c;
        this.requery = requery;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.single_item_details, parent, false);
        LinearLayout lay_single = rowView.findViewById(R.id.lay_single);
        if(cursor.getPosition()%2==0) {
            lay_single.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CountAdapter.ViewHolder viewHolder = new CountAdapter.ViewHolder();
        viewHolder.textColor =  view.findViewById(R.id.textColor);
        viewHolder.textSize =  view.findViewById(R.id.textSize);
        viewHolder.textPrice =  view.findViewById(R.id.textPrice);

        viewHolder.textColor.setText("Views: \n" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLOUMN_VCOUNT)));
        viewHolder.textSize.setText("Shares: \n" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLOUMN_SCOUNT)));
        viewHolder.textPrice.setText("Orders: \n" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLOUMN_OCOUNT)));

    }
    private class ViewHolder{
        TextView textColor, textSize, textPrice;
    }
}
