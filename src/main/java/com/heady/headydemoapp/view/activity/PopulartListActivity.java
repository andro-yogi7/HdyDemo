package com.heady.headydemoapp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.heady.headydemoapp.R;
import com.heady.headydemoapp.adapter.ProductAdapter;
import com.heady.headydemoapp.dao.Dao;
import com.heady.headydemoapp.dao.DatabaseHelper;

/**
 * Created by YOGESH on 07-12-2018.
 */

public class PopulartListActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Cursor c;
    Dao dao;
    ListView listView;
    Button btn_orders, btn_shares, btn_views;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        initUI();

        populateList();

        initListeners();
    }

    private void initUI() {
        listView = findViewById(R.id.list_pop);
        btn_orders = findViewById(R.id.btn_orders);
        btn_shares = findViewById(R.id.btn_shares);
        btn_views = findViewById(R.id.btn_views);
    }

    private void populateList() {
        dao = new Dao(this);
        c = dao.getMostOrderedItems();
        productAdapter = new ProductAdapter(this, c, false);
        listView.setAdapter(productAdapter);
    }

    private void initListeners() {
        listView.setOnItemClickListener(this);
        btn_orders.setOnClickListener(this);
        btn_shares.setOnClickListener(this);
        btn_views.setOnClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        c.moveToPosition(i);
        int item_prod = c.getInt(c.getColumnIndex(DatabaseHelper.COLOUMN_PID));
        Log.e("Clicked", "" + item_prod);
        Log.e("Count", "" + c.getInt(c.getColumnIndex(DatabaseHelper.COLOUMN_SCOUNT)));
        startActivity(new Intent(PopulartListActivity.this, ProductDetailActivity.class).putExtra("prod", item_prod).putExtra("prod_name", c.getString(c.getColumnIndex(DatabaseHelper.COLOUMN_PNAME))));
    }

    @Override
    public void onClick(View view) {
        Log.e("Onclick", "called");
        int id = view.getId();
        switch (id) {
            case R.id.btn_orders: {
                c = dao.getMostOrderedItems();
                productAdapter.swapCursor(c);
                productAdapter.notifyDataSetChanged();
            }
            break;
            case R.id.btn_views: {
                c = dao.getMostViewedItems();
                productAdapter.swapCursor(c);
                productAdapter.notifyDataSetChanged();
                //listView.setAdapter(new ProductAdapter(this, dao.getMostViewedItems(), true));

            }
            break;
            case R.id.btn_shares: {
                c = dao.getMostSharedItems();
                productAdapter.swapCursor(c);
                productAdapter.notifyDataSetChanged();
                //listView.setAdapter(new ProductAdapter(this, dao.getMostSharedItems(), true));
            }
            break;
        }//switch close
    }
}
