package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.util.Util;
import com.example.nanjing.zy_java.bean.Gv_bean;
import com.example.nanjing.zy_java.bean.ThresholdBean;
import com.example.nanjing.zy_java.util.Threshold;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Yuzhi_change extends Fragment implements View.OnClickListener {

    private GridView gv_yulist;
    private ImageView imageView_Back;
    private TextView tv_title;
    private TextView yuzhi_set;
    private RelativeLayout pain;
    private List<Gv_bean> list;
    private ThresholdBean thresholdBean;
    private Context context;
    private GvThreAdapter adapter;
    private Timer timer;
    private RequestQueue requestQueue;
    private String api = "";
    private String api1 = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yuzhi, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        gv_yulist = (GridView) view.findViewById(R.id.gv_yulist);
        imageView_Back = (ImageView) view.findViewById(R.id.imageView_Back);
        imageView_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(this);
        yuzhi_set = (TextView) view.findViewById(R.id.yuzhi_set);
        yuzhi_set.setOnClickListener(this);
        pain = (RelativeLayout) view.findViewById(R.id.pain);
        pain.setOnClickListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new Gv_bean("正在加载", 10, 50));
        }
        thresholdBean = new ThresholdBean();
        adapter = new GvThreAdapter();
        gv_yulist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        timer = new Timer();
        requestQueue = Volley.newRequestQueue(context);
        api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_sense";
        api1 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_road_status";
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getIndex();
            }
        }, 0, 3000);

    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void getIndex() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", Util.getUser(context));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    ThresholdBean thresholdBean = gson.fromJson(jsonObject.toString(), ThresholdBean.class);
                    list.set(0, new Gv_bean("温度", thresholdBean.getTemperature(), Threshold.getYuZhi(context, "wendu")));
                    list.set(1, new Gv_bean("湿度", thresholdBean.getHumidity(), Threshold.getYuZhi(context, "shidu")));
                    list.set(2, new Gv_bean("光照", thresholdBean.getLightIntensity(), Threshold.getYuZhi(context, "guangzhao")));
                    list.set(3, new Gv_bean("CO2", thresholdBean.getCo2(), Threshold.getYuZhi(context, "co2")));
                    list.set(4, new Gv_bean("PM2.5", thresholdBean.get_$Pm2526(), Threshold.getYuZhi(context, "pm25")));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    getIndex();
                    Toast.makeText(context, "" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject2.put("UserName", Util.getUser(context));
            jsonObject2.put("RoadId", 1);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api1, jsonObject2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        int status = jsonObject.getInt("Status");
                        list.set(5, new Gv_bean("道路状况", status, Threshold.getYuZhi(context, "status")));
                        handler.sendEmptyMessage(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    Toast.makeText(context, "" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yuzhi_set:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new Yuzhi_set()).commit();
                break;
        }
    }

    class GvThreAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_yuzhi, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.yuzhi_name.setText(list.get(position).getName());
            viewHolder.tx_index.setText(list.get(position).getIndex() + "");
            if (Threshold.getSwitch(context)) {
                if (list.get(position).getIndex() > list.get(position).getThreshold()) {
                    viewHolder.item_back.setBackgroundColor(Color.RED);
                    //发送与之告警
                    int res = R.drawable.icon101;
                    switch (position){
                        case 0:
                            res = R.drawable.icon101;
                            break;
                        case 1:
                            res = R.drawable.icon102;
                            break;
                        case 2:
                            res = R.drawable.icon103;
                            break;
                        case 3:
                            res = R.drawable.icon104;
                            break;
                        case 4:
                            res = R.drawable.icon105;
                            break;
                        case 5:
                            res = R.drawable.icon_4;
                            break;
                    }
                    showNofitication(res,position, "【" + list.get(position).getName() + "】告警", "当前值 " + list.get(position).getIndex() + "/" + list.get(position).getThreshold() + "(阈值)");
                } else {
                    viewHolder.item_back.setBackgroundColor(Color.GREEN);
                }
            } else {
                viewHolder.item_back.setBackgroundColor(Color.YELLOW);
            }

            return convertView;
        }

        public void showNofitication(int res,int id, String title, String content) {
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle(title);
            builder.setContentText(content);
            builder.setSmallIcon(res);
            builder.setTicker("阈值告警");
            builder.setWhen(System.currentTimeMillis());
            manager.notify(id, builder.build());
        }

        public class ViewHolder {
            public View rootView;
            public TextView yuzhi_name;
            public TextView tx_index;
            public RelativeLayout item_back;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.yuzhi_name = (TextView) rootView.findViewById(R.id.yuzhi_name);
                this.tx_index = (TextView) rootView.findViewById(R.id.tx_index);
                this.item_back = (RelativeLayout) rootView.findViewById(R.id.item_back);
            }

        }
    }


}
