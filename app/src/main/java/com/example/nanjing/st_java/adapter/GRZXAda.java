package com.example.nanjing.st_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.st_java.bean.GRZXBean;

import java.util.ArrayList;

public class GRZXAda extends BaseAdapter {
    private ArrayList<GRZXBean> grzxBeans;
    private Context context;

    public GRZXAda(ArrayList<GRZXBean> grzxBeans, Context context) {
        this.grzxBeans = grzxBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return grzxBeans != null ? grzxBeans.size() : 0;
    }

    @Override
    public GRZXBean getItem(int position) {
        return grzxBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.ada_grzx, null);
        ViewHolder viewHolder = new ViewHolder(view);
        GRZXBean item = getItem(position);
        viewHolder.image.setBackgroundResource(item.getIcon());
        viewHolder.carnumber.setText(item.getCarnumber());
        viewHolder.value.setText("余额：" + item.getValue() + "元");
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView image;
        public TextView carnumber;
        public TextView value;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.carnumber = (TextView) rootView.findViewById(R.id.carnumber);
            this.value = (TextView) rootView.findViewById(R.id.value);
        }

    }
}
