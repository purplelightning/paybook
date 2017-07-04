package com.example.wind.paybook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wind on 17-7-3.
 */

public class CostListAdapter extends BaseAdapter {

    private List<CostBean> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public CostListAdapter(Context context, List<CostBean> list) {
        mList = list;
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //相当于onCreateView和onBindView结合
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            viewHolder.mTvCostTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.mTvCostDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.mTvCostMoney = (TextView) convertView.findViewById(R.id.tv_cost);

            convertView.setTag(viewHolder);//标记新的convertView
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CostBean bean = mList.get(position);
        viewHolder.mTvCostTitle.setText(bean.getCostTitle());
        viewHolder.mTvCostDate.setText(bean.getCostDate());
        viewHolder.mTvCostMoney.setText(bean.getCostMoney());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTvCostTitle;
        TextView mTvCostDate;
        TextView mTvCostMoney;

    }
}







