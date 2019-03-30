package com.example.nanjing.ws_java.subway;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nanjing.R;

import java.util.List;

public class SubwayAdapter extends BaseAdapter {
    private Context context;
    private List<SubwayBean.ROWSDETAILBean> list;
    private Subway_Main subway_main;

    public SubwayAdapter(Context context, List<SubwayBean.ROWSDETAILBean> list,Subway_Main subway_main) {
        this.context = context;
        this.list = list;
        this.subway_main = subway_main;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SubwayBean.ROWSDETAILBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.subway_list_item, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.ls_id.setText(list.get(position).getMetro_code());
        viewHolder.start_place.setText(list.get(position).getStart_place());
        viewHolder.end_place.setText(list.get(position).getEnd_place());
        viewHolder.start_time.setText("🕓" + list.get(position).getStart_place_start_time()+" - "+list.get(position).getStart_place_end_time());
        viewHolder.end_time.setText("🕓" + list.get(position).getEnd_place_start_time()+" - "+list.get(position).getEnd_place_end_time());

        viewHolder.ls_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("title",list.get(position).getStart_place()+"-"+list.get(position).getEnd_place());
                args.putString("map",list.get(position).getImg());
                subway_main.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,SubwayDetails.newInstance(args)).commit();
            }
        });
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView ls_id;
        public TextView start_place;
        public TextView end_place;
        public TextView start_time;
        public TextView end_time;
        public ImageView ls_details;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ls_id = (TextView) rootView.findViewById(R.id.ls_id);
            this.start_place = (TextView) rootView.findViewById(R.id.start_place);
            this.end_place = (TextView) rootView.findViewById(R.id.end_place);
            this.start_time = (TextView) rootView.findViewById(R.id.start_time);
            this.end_time = (TextView) rootView.findViewById(R.id.end_time);
            this.ls_details = (ImageView) rootView.findViewById(R.id.ls_details);
        }

    }
}
