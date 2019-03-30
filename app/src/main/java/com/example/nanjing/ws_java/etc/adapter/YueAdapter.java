package com.example.nanjing.ws_java.etc.adapter;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.ws_java.etc.bean.YueBean;

import java.lang.reflect.Field;
import java.util.List;

public class YueAdapter extends BaseAdapter {
    private Context context;
    private List<YueBean> list;

    public YueAdapter(Context context, List<YueBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public YueBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.yue_list_item, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.y_id.setText(list.get(position).getId()+"");
        viewHolder.y_chebiao.setImageResource(getImg(list.get(position).getChebiao()));
        viewHolder.y_chepai.setText(list.get(position).getChepai()+"");
        viewHolder.y_chezhu.setText("车主" + list.get(position).getChezhu()+"");
        viewHolder.y_balance.setText("余额" + list.get(position).getBalance()+"元");
        return convertView;
    }

    public int getImg(String chebiao){
        try {
            Field field = R.drawable.class.getField(chebiao);
            return field.getInt(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView y_id;
        public ImageView y_chebiao;
        public TextView y_chepai;
        public TextView y_chezhu;
        public TextView y_balance;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.y_id = (TextView) rootView.findViewById(R.id.y_id);
            this.y_chebiao = (ImageView) rootView.findViewById(R.id.y_chebiao);
            this.y_chepai = (TextView) rootView.findViewById(R.id.y_chepai);
            this.y_chezhu = (TextView) rootView.findViewById(R.id.y_chezhu);
            this.y_balance = (TextView) rootView.findViewById(R.id.y_balance);
        }

    }
}
