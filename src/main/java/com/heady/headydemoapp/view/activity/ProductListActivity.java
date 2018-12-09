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
import android.widget.ListView;

import com.heady.headydemoapp.dao.Dao;
import com.heady.headydemoapp.dao.DatabaseHelper;
import com.heady.headydemoapp.R;
import com.heady.headydemoapp.adapter.ProductAdapter;

/**
 * Created by YOGESH on 07-12-2018.
 */

public class ProductListActivity extends Activity implements AdapterView.OnItemClickListener {
    Cursor c;
    Dao dao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_cat);

        dao = new Dao(this);
        c  = dao.getListProductCursor(getIntent().getIntExtra("prod", 0));
        listView.setAdapter(new ProductAdapter(this, c, false ));
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        c.moveToPosition(i);
        int item_prod = c.getInt(c.getColumnIndex(DatabaseHelper.COLOUMN_PID));
        Log.e("Clicked","" + item_prod);
        Log.e("Count","" + c.getInt(c.getColumnIndex(DatabaseHelper.COLOUMN_SCOUNT)));
        startActivity(new Intent(ProductListActivity.this, ProductDetailActivity.class).putExtra("prod", item_prod).putExtra("prod_name", c.getString(c.getColumnIndex(DatabaseHelper.COLOUMN_PNAME))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()){
            case R.id.popular:{
               startActivity(new Intent(this,PopulartListActivity.class));
            }
            break;
        }//switch close
        return true;
    }
}
