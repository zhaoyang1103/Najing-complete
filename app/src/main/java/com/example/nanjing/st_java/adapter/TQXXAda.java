package com.example.nanjing.st_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;
import com.example.nanjing.st_java.bean.Get_weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TQXXAda extends BaseAdapter {
    private Get_weather get_weather;
    private Context context;

    public TQXXAda(Get_weather get_weather, Context context) {
        this.get_weather = get_weather;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = View.inflate(context, R.layout.ada_tqxx1, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Get_weather.ROWSDETAILBean rowsdetailBean = get_weather.getROWS_DETAIL().get(position);
        if (position == 0) {
            viewHolder.WData.setText(rowsdetailBean.getWData().substring(8, 10) + "日(昨天)");
        } else if (position == 1) {
            viewHolder.WData.setText(rowsdetailBean.getWData().substring(8, 10) + "日(今天)");
        } else if (position == 2) {
            viewHolder.WData.setText(rowsdetailBean.getWData().substring(8, 10) + "日(明天)");
        } else if (position == 3) {
            viewHolder.WData.setText(rowsdetailBean.getWData().substring(8, 10) + "日(后天)");
        } else {
            try {
                viewHolder.WData.setText(rowsdetailBean.getWData().substring(8, 10) + "日(" + getWeek(simpleDateFormat.parse(rowsdetailBean.getWData())) + ")");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        viewHolder.weather.setText(rowsdetailBean.getWeather());
        String[] split = rowsdetailBean.getTemperature().split("~");
        viewHolder.temperature.setText(split[0] + "/" + split[1] + "℃");
        return inflate;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView WData;
        public ImageView image;
        public TextView weather;
        public TextView temperature;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.WData = (TextView) rootView.findViewById(R.id.WData);
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.weather = (TextView) rootView.findViewById(R.id.weather);
            this.temperature = (TextView) rootView.findViewById(R.id.temperature);
        }

    }

    private String getWeek(Date parse) {
        String weeks[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        int i = instance.get(Calendar.DAY_OF_WEEK) - 1;
        if (i < 0) {
            i = 0;
        }
        return weeks[i];
    }
}
