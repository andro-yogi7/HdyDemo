package com.heady.headydemoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.heady.headydemoapp.model.Category;
import com.heady.headydemoapp.model.Product;
import com.heady.headydemoapp.model.Product_;
import com.heady.headydemoapp.model.Ranking;
import com.heady.headydemoapp.model.Tax;
import com.heady.headydemoapp.model.Variant;

import java.util.List;

/**
 * Created by YOGESH on 06-12-2018.
 */

public class Dao {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public Dao(Context context) {
        databaseHelper = DatabaseHelper.getDatabaseHelper(context);
    }

    public SQLiteDatabase getReadableDatabase() {
        return databaseHelper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return databaseHelper.getWritableDatabase();
    }

    //insert operations

    public void insertCat(List<Category> catergories) {
        for (Category category : catergories) {
            insertCategory(category);
        }
        Log.e("Dao", "" + getReadableDatabase().rawQuery("select PNAME from " + DatabaseHelper.TABLE_PROD, null).getCount());
    }


    public void insertCategory(Category category) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.COLOUMN_CID, category.getId());
        contentValue.put(DatabaseHelper.COLOUMN_CNAME, category.getName());

        getWritableDatabase().insertWithOnConflict(DatabaseHelper.TABLE_CAT, null, contentValue, SQLiteDatabase.CONFLICT_REPLACE);
        insertProduct(category.getProducts(), category.getId());
    }


    private void insertProduct(List<Product> products, Integer id) {
        ContentValues contentValue = new ContentValues();
        for (Product product : products) {
            Integer pid = product.getId();
            contentValue.put(DatabaseHelper.COLOUMN_PID, pid);
            contentValue.put(DatabaseHelper.COLOUMN_PCID, id);
            ;
            contentValue.put(DatabaseHelper.COLOUMN_PNAME, product.getName());
            ;
            contentValue.put(DatabaseHelper.COLOUMN_PDATE, product.getDateAdded());

            insertVariant(product.getVariants(), pid);
            insertTax(product.getTax(), pid);

            getWritableDatabase().insert(DatabaseHelper.TABLE_PROD, null, contentValue);
            getWritableDatabase().insert(DatabaseHelper.TABLE_PROD, null, contentValue);
        }
    }

    private void insertTax(Tax tax, Integer id) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLOUMN_TNAME, tax.getName());
        contentValues.put(DatabaseHelper.COLOUMN_TAX, tax.getValue());
        contentValues.put(DatabaseHelper.COLOUMN_TPID, id);

        getWritableDatabase().insert(DatabaseHelper.TABLE_TAX, null, contentValues);
    }

    private void insertVariant(List<Variant> variants, Integer id) {

        ContentValues contentValues = new ContentValues();
        for (Variant variant : variants) {
            contentValues.put(DatabaseHelper.COLOUMN_VID, variant.getId());
            contentValues.put(DatabaseHelper.COLOUMN_VPID, id);
            contentValues.put(DatabaseHelper.COLOUMN_VCOL, variant.getColor());
            contentValues.put(DatabaseHelper.COLOUMN_VPRICE, variant.getPrice());
            contentValues.put(DatabaseHelper.COLOUMN_VSIZE, variant.getSize());

            getWritableDatabase().insert(DatabaseHelper.TABLE_VAR, null, contentValues);
        }
    }

    public void insertRanking(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            String rank = ranking.getRanking();
            List<Product_> products = ranking.getProducts();

            insertProduct_(rank, products);
        }
    }


    private void insertProduct_(String ranking, List<Product_> products) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Product_ product : products) {
            if (ranking.equalsIgnoreCase("Most Viewed Products")) {
                //view
                contentValues.put(DatabaseHelper.COLOUMN_VCOUNT, product.getViewCount());
            } else if (ranking.equalsIgnoreCase("Most Ordered Products")) {
                //order
                contentValues.put(DatabaseHelper.COLOUMN_OCOUNT, product.getOrderCount());
            } else if (ranking.equalsIgnoreCase("Most Shared Products")) {
                //order
                contentValues.put(DatabaseHelper.COLOUMN_SCOUNT, product.getShares());
            }
            db.update(DatabaseHelper.TABLE_PROD, contentValues, DatabaseHelper.COLOUMN_PID + "=" + product.getId(), null);
        }
    }

   //Query opersations

    public Cursor getListCursor() {
        return getReadableDatabase().rawQuery("select *  from " + DatabaseHelper.TABLE_CAT, null);
    }

    public Cursor getListProductCursor(int id) {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_PROD + " where " + DatabaseHelper.COLOUMN_PCID + " = " + id, null);
    }

    public Cursor getListVariantCursor(int id) {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_VAR + " where " + DatabaseHelper.COLOUMN_VPID + " = " + id, null);
    }

    public Cursor getListTaxCursor(int id) {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_TAX + " where " + DatabaseHelper.COLOUMN_TPID + " = " + id, null);
    }

    public Cursor getMostSharedItems() {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_PROD + " order by " + DatabaseHelper.COLOUMN_SCOUNT + " DESC ", null);
    }

    public Cursor getMostViewedItems() {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_PROD + " order by " + DatabaseHelper.COLOUMN_VCOUNT + " DESC ", null);
    }

    public Cursor getMostOrderedItems() {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_PROD + " order by " + DatabaseHelper.COLOUMN_OCOUNT + " DESC ", null);
    }

    public Cursor getListCountCursor(int id) {
        return getReadableDatabase().rawQuery("select * from " + DatabaseHelper.TABLE_PROD + " where " + DatabaseHelper.COLOUMN_PID + " = " + id, null);
    }

}
