package com.heady.headydemoapp.view.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.heady.headydemoapp.adapter.CountAdapter;
import com.heady.headydemoapp.dao.Dao;
import com.heady.headydemoapp.R;
import com.heady.headydemoapp.adapter.VariantAdapter;

/**
 * Created by YOGESH on 07-12-2018.
 */

public class ProductDetailActivity extends Activity {
    Cursor c1, c2;
    ListView list_count, list_variant;
    TextView textProdName, textProdPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prod_details);

        list_variant = findViewById(R.id.listView2);
        list_count = findViewById(R.id.listView3);
        textProdName = findViewById(R.id.textView2);
        textProdPrice = findViewById(R.id.textView2);

        getDetails();
    }

    private void getDetails() {
        int id = getIntent().getIntExtra("prod", 0);
        String prod_name = getIntent().getStringExtra("prod_name");
        textProdName.setText(prod_name);

        Dao dao  = new Dao(this);

        c1 = dao.getListVariantCursor(id);

        list_variant.setAdapter(new VariantAdapter(this, c1, true));

        c2 = dao.getListCountCursor(id);
        list_count.setAdapter(new CountAdapter(this, c2, true));

    }
}
