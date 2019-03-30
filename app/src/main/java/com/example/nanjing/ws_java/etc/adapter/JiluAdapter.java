package com.example.nanjing.ws_java.etc.adapter;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.ws_java.etc.bean.JiluBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class JiluAdapter extends BaseAdapter {
    private Context context;
    private List<JiluBean> list;

    public JiluAdapter(Context context, List<JiluBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public JiluBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.jilu_list_item, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.j_date.setText(list.get(position).getTime().substring(0,10)+DayToWeek(list.get(position).getTime()));
        viewHolder.j_user.setText("充值人："+list.get(position).getUser());
        viewHolder.j_chepai.setText("车牌号："+list.get(position).getChepai());
        viewHolder.j_money.setText("充值："+list.get(position).getBalance());
        viewHolder.j_time.setText(list.get(position).getTime());
        return convertView;
    }

    public String DayToWeek(String date) {
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = instance.get(Calendar.DAY_OF_WEEK) - 1;
        if (i<0){
            i = 0;
        }
        return weeks[i];
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView j_date;
        public TextView j_user;
        public TextView j_chepai;
        public TextView j_money;
        public TextView j_time;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.j_date = (TextView) rootView.findViewById(R.id.j_date);
            this.j_user = (TextView) rootView.findViewById(R.id.j_user);
            this.j_chepai = (TextView) rootView.findViewById(R.id.j_chepai);
            this.j_money = (TextView) rootView.findViewById(R.id.j_money);
            this.j_time = (TextView) rootView.findViewById(R.id.j_time);
        }

    }
}
