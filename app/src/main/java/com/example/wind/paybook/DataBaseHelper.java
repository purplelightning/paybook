package com.example.wind.paybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wind on 17-7-3.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String PAY_COST = "pay_cost";

    public DataBaseHelper(Context context) {
        super(context, "pay_daily", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists pay_cost(" +
                "id integer primary key, " +
                "cost_title varchar, " +
                "cost_date varchar, " +
                "cost_money varchar)");
    }

    public void insertCost(CostBean costBean) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COST_TITLE, costBean.getCostTitle());
        cv.put(COST_DATE, costBean.getCostDate());
        cv.put(COST_MONEY, costBean.getCostMoney());
        database.insert(PAY_COST, null, cv);
    }

    public Cursor getAllCostData() {
        SQLiteDatabase database = getWritableDatabase();//cost_date之后有空格
        return database.query(PAY_COST, null, null, null, null, null, COST_DATE
                + " ASC");
    }

    public void deleteAllData(){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(PAY_COST,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}






