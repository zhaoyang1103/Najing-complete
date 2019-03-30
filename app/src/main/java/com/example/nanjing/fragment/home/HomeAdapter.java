package com.example.nanjing.fragment.home;
/*
 * Created by 王森 on WangSen.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;

import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBean> list;

    public HomeAdapter(Context context, List<HomeBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.home_grid_list, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.home_list_img.setImageResource(list.get(position).getHome_img());
        viewHolder.home_list_text.setText(list.get(position).getHome_text());
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView home_list_img;
        public TextView home_list_text;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.home_list_img = (ImageView) rootView.findViewById(R.id.home_list_img);
            this.home_list_text = (TextView) rootView.findViewById(R.id.home_list_text);
        }

    }
}
