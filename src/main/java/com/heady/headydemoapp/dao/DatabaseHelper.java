package com.heady.headydemoapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YOGESH on 27-11-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //db name and versions
    private final static String DATABASE_NAME = "APPDB";

    //table names
    public static final String TABLE_CAT = "CATEGORY";
    public static final String TABLE_RANK = "RANKING";
    public static final String TABLE_PROD = "PRODUCT";
    public static final String TABLE_VAR = "VARIANT";
    public static final String TABLE_TAX = "TAX";

    //table CATEGORY
    public static final String COLOUMN_CID = "CID";
    public static final String COLOUMN_CNAME = "CNAME";

    //table PRODUCT
    public static final String COLOUMN_PCID = "PCID";
    public static final String COLOUMN_PID = "PID";
    ;
    public static final String COLOUMN_PNAME = "PNAME";
    public static final String COLOUMN_PDATE = "PDATE";
    public static final String COLOUMN_SCOUNT = "SCOUNT";
    public static final String COLOUMN_OCOUNT = "OCOUNT";
    public static final String COLOUMN_VCOUNT = "VCOUNT";

    //table RANKING
    public static final String COLOUMN_RANKING = "RANKING";
    public static final String COLOUMN_RID = "RID";

    //table VARIANT
    public static final String COLOUMN_VPID = "VPID";
    public static final String COLOUMN_VID = "VID";
    public static final String COLOUMN_VCOL = "VCOL";
    public static final String COLOUMN_VSIZE = "VSIZE";
    public static final String COLOUMN_VPRICE = "VPRICE";

    //table TAX
    public static final String COLOUMN_TPID = "TPID";
    public static final String COLOUMN_TNAME = "TNAME";
    public static final String COLOUMN_TAX = "TAX";

    private static DatabaseHelper databaseHelper;

    //CREATE TABLES STATEMENTS
    private final String CREATE_CAT = "create table " + TABLE_CAT + "( _id INTEGER,"
            + COLOUMN_CID + " INTEGER PRIMARY KEY, "
            + COLOUMN_CNAME + " TEXT "
            +
            " )";

    private final String CREATE_PROD = "create table " + TABLE_PROD + "( _id INTEGER,"
            + COLOUMN_PID + " INTEGER PRIMARY KEY, "
            + COLOUMN_PNAME + " TEXT, "
            + COLOUMN_PCID + " INTEGER, "
            + COLOUMN_PDATE + " TEXT, "
            + COLOUMN_VCOUNT + " INTEGER, "
            + COLOUMN_OCOUNT + " INTEGER, "
            + COLOUMN_SCOUNT + " INTEGER, "
            + "FOREIGN KEY (" + COLOUMN_PCID + ") "
            + "REFERENCES " + TABLE_CAT + "(" + COLOUMN_CID + ")"
            +
            " )";

    private final String CREATE_RANK = "create table " + TABLE_RANK + "( _id INTEGER  AUTOINCREMENT,"
            + COLOUMN_RID + " INTEGER , "
            + COLOUMN_RANKING + " TEXT "
            +
            " )";
    private final String CREATE_VAR = "create table " + TABLE_VAR + "( _id INTEGER,"
            + COLOUMN_VID + " INTEGER PRIMARY KEY, "
            + COLOUMN_VPID + " INTEGER, "
            + COLOUMN_VCOL + " TEXT, "
            + COLOUMN_VSIZE + " INTEGER, "
            + COLOUMN_VPRICE + " INTEGER, "
            + "FOREIGN KEY (" + COLOUMN_VPID + ") "
            + "REFERENCES " + TABLE_PROD + "(" + COLOUMN_PID + ")"
            +
            " )";

    private final String CREATE_TAX = "create table " + TABLE_TAX + "( _id INTEGER,"
            + COLOUMN_TPID + " INTEGER, "
            + COLOUMN_TNAME + " TEXT, "
            + COLOUMN_TAX + " INTEGER, "
            + "FOREIGN KEY (" + COLOUMN_TPID + ") "
            + "REFERENCES " + TABLE_PROD + "(" + COLOUMN_PID + ")"
            +
            " )";


    public DatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, 1);
    }

    public static DatabaseHelper getDatabaseHelper(Context ctx) {
        if (databaseHelper != null) {
            return databaseHelper;
        } else
            databaseHelper = new DatabaseHelper(ctx);
        return databaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAT);
        db.execSQL(CREATE_PROD);
        db.execSQL(CREATE_VAR);
        db.execSQL(CREATE_TAX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table  if exists " + TABLE_CAT);
        db.execSQL("drop table  if exists " + TABLE_PROD);
        db.execSQL("drop table  if exists " + TABLE_RANK);
        db.execSQL("drop table  if exists " + TABLE_VAR);
        db.execSQL("drop table  if exists " + TABLE_TAX);

        onCreate(db);
    }
}
