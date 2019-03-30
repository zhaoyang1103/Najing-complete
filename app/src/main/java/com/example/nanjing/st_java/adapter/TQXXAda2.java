package com.example.nanjing.st_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.st_java.bean.TQXXBean;

import java.util.ArrayList;

public class TQXXAda2 extends BaseAdapter {
    private ArrayList<TQXXBean> tqxxBeans;
    private Context context;

    public TQXXAda2(ArrayList<TQXXBean> tqxxBeans, Context context) {
        this.tqxxBeans = tqxxBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tqxxBeans != null ? tqxxBeans.size() : 0;
    }

    @Override
    public TQXXBean getItem(int position) {
        return tqxxBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.ada_tqxx12, null);
        ViewHolder holder = new ViewHolder(view);
        TQXXBean item = getItem(position);
        holder.icon.setBackgroundResource(item.getIcon());
        holder.name.setText(item.getName() + "");
        holder.value1.setText(item.getValue1() + "");
        holder.value2.setText(item.getValue2() + "");
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView icon;
        public TextView name;
        public TextView value1;
        public TextView value2;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.icon = (ImageView) rootView.findViewById(R.id.icon);
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.value1 = (TextView) rootView.findViewById(R.id.value1);
            this.value2 = (TextView) rootView.findViewById(R.id.value2);
        }

    }
}
