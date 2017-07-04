package com.example.wind.paybook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CostBean> mList = new ArrayList<CostBean>();
    private CostListAdapter mAdapter;
    private DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBaseHelper = new DataBaseHelper(this);

        initCostData();
        ListView costList = (ListView) findViewById(R.id.lv_main);

        mAdapter = new CostListAdapter(this, mList);
        costList.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View viewDialog = inflater.inflate(R.layout.new_cost_data, null);
                final EditText title = (EditText) viewDialog.findViewById(R.id.et_cost_title);
                final EditText money = (EditText) viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date = (DatePicker) viewDialog.findViewById(R.id.dp_cost_date);
                builder.setView(viewDialog);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean = new CostBean();
                        costBean.setCostTitle(title.getText().toString());
                        costBean.setCostMoney(money.getText().toString());
                        costBean.setCostDate(date.getYear() + "-" + (date.getMonth() + 1) + "-"
                                + date.getDayOfMonth());
                        mDataBaseHelper.insertCost(costBean);
                        mList.add(costBean);//mList改变了,通知之后才会发生刷新
                        mAdapter.notifyDataSetChanged();//更新数据源
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();//显示dialog
            }
        });
    }

    private void initCostData() {
        //先清空数据库,用于测试
//        mDataBaseHelper.deleteAllData();
//        //alt+shift+z
//        for (int i = 0; i < 5; i++) {
//            CostBean costBean = new CostBean();
//            costBean.setCostTitle(i + "mock");
//            costBean.setCostDate("11-11");
//            costBean.setCostMoney("20");
////假数据     mList.add(costBean);
//            mDataBaseHelper.insertCost(costBean);
//        }

        Cursor cursor = mDataBaseHelper.getAllCostData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                CostBean costBean = new CostBean();
                costBean.setCostTitle(cursor.getString(cursor.getColumnIndex("cost_title")));
                costBean.setCostDate(cursor.getString(cursor.getColumnIndex("cost_date")));
                costBean.setCostMoney(cursor.getString(cursor.getColumnIndex("cost_money")));
                mList.add(costBean);
            }
            cursor.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_chart:
                Intent intent = new Intent(MainActivity.this, ChartsActivity.class);
                intent.putExtra("cost_list", (Serializable) mList);
                startActivity(intent);
                break;
            case R.id.clear:
                mDataBaseHelper.deleteAllData();
                mList.clear();
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
