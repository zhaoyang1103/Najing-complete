package com.example.nanjing.st_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.st_java.adapter.TQXXAda;
import com.example.nanjing.st_java.adapter.TQXXAda2;
import com.example.nanjing.st_java.bean.Get_all_sense;
import com.example.nanjing.st_java.bean.Get_weather;
import com.example.nanjing.st_java.bean.TQXXBean;
import com.example.nanjing.util.UrlBean;
import com.example.nanjing.util.Util;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TQXXFra extends Fragment {
    private ImageView back;
    private ImageView refresh;
    private ImageView image;
    private TextView WData;
    private TextView city;
    private TextView WCurrent;
    private GridView gridView1;
    private GridView gridView2;
    private Context mContext;
    private UrlBean urlBean;
    private String user;
    private RequestQueue requestQueue;
    private Timer timer;
    private TQXXAda2 tqxxAda2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_tqxx, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonRequest = new JSONObject();
                try {
                    jsonRequest.put("UserName", "user1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_weather", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        Get_weather get_weather = gson.fromJson(jsonObject.toString(), Get_weather.class);
                        List<Get_weather.ROWSDETAILBean> rows_detail = get_weather.getROWS_DETAIL();
                        Get_weather.ROWSDETAILBean rowsdetailBean = rows_detail.get(1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
                        try {
                            String format = simpleDateFormat1.format(simpleDateFormat.parse(rowsdetailBean.getWData()));
                            WData.setText(format + "    " + getWeek(simpleDateFormat.parse(rowsdetailBean.getWData())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        WCurrent.setText(get_weather.getWCurrent() + "度");
                        TQXXAda tqxxAda = new TQXXAda(get_weather, mContext);
                        gridView1.setAdapter(tqxxAda);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                Volley.newRequestQueue(mContext).add(jsonObjectRequest);
            }
        });
    }

    private void initData() {
        mContext = getContext();
        urlBean = Util.loadSetting(mContext);
        user = Util.getUser(mContext);
        requestQueue = Volley.newRequestQueue(mContext);
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_weather", jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                Get_weather get_weather = gson.fromJson(jsonObject.toString(), Get_weather.class);
                List<Get_weather.ROWSDETAILBean> rows_detail = get_weather.getROWS_DETAIL();
                Get_weather.ROWSDETAILBean rowsdetailBean = rows_detail.get(1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    String format = simpleDateFormat1.format(simpleDateFormat.parse(rowsdetailBean.getWData()));
                    WData.setText(format + "    " + getWeek(simpleDateFormat.parse(rowsdetailBean.getWData())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                WCurrent.setText(get_weather.getWCurrent() + "度");


                TQXXAda tqxxAda = new TQXXAda(get_weather, mContext);
                gridView1.setAdapter(tqxxAda);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        final ArrayList<TQXXBean> tqxxBeans = new ArrayList<>();
        tqxxBeans.add(new TQXXBean(R.drawable.icon101, "紫外线指数", "", ""));
        tqxxBeans.add(new TQXXBean(R.drawable.icon102, "感冒指数", "", ""));
        tqxxBeans.add(new TQXXBean(R.drawable.icon103, "穿衣指数", "", ""));
        tqxxBeans.add(new TQXXBean(R.drawable.icon104, "运动指数", "", ""));
        tqxxBeans.add(new TQXXBean(R.drawable.icon105, "空气污染扩散指数", "", ""));
        tqxxAda2 = new TQXXAda2(tqxxBeans, mContext);
        gridView2.setAdapter(tqxxAda2);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonRequest1 = new JSONObject();
                try {
                    jsonRequest1.put("UserName", "user1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_sense", jsonRequest1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        Get_all_sense get_all_sense = gson.fromJson(jsonObject.toString(), Get_all_sense.class);
                        TQXXBean tqxxBean = tqxxBeans.get(0);
                        int i = get_all_sense.getLightIntensity();
                        if (i < 1000) {
                            tqxxBean.setValue1("弱(" + i + ")");
                            tqxxBean.setValue2("辐射较弱，涂擦" +
                                    "SPF12~15、PA+护肤品\n");
                        } else if (i <= 3000) {
                            tqxxBean.setValue1("中等(" + i + ")");
                            tqxxBean.setValue2("涂擦 SPF 大于 15、" +
                                    "PA+防晒护肤品\n");
                        } else {
                            tqxxBean.setValue1("强(" + i + ")");
                            tqxxBean.setValue2("尽量减少外出，需要涂" +
                                    "抹高倍数防晒霜");
                        }
                        tqxxBean = tqxxBeans.get(1);
                        i = get_all_sense.getTemperature();
                        if (i < 8) {
                            tqxxBean.setValue1("较易发(" + i + ")");
                            tqxxBean.setValue2("温度低，风较大，较易发生" +
                                    "感冒，注意防护\n");
                        } else {
                            tqxxBean.setValue1("少发(" + i + ")");
                            tqxxBean.setValue2("无明显降温，感冒机率\n" +
                                    "较低");
                        }
                        tqxxBean = tqxxBeans.get(2);
                        if (i < 12) {
                            tqxxBean.setValue1("冷(" + i + ")");
                            tqxxBean.setValue2("建议穿长袖衬" +
                                    "衫、单裤等服装\n");
                        } else if (i <= 21) {
                            tqxxBean.setValue1("舒适(" + i + ")");
                            tqxxBean.setValue2("建议穿短袖衬衫、单裤等" +
                                    "服装");
                        } else {
                            tqxxBean.setValue1("热(" + i + ")");
                            tqxxBean.setValue2("适合穿 T 恤、短薄外套" +
                                    "等夏季服装\n");
                        }
                        tqxxBean = tqxxBeans.get(3);
                        i = get_all_sense.getCo2();
                        if (i < 3000) {
                            tqxxBean.setValue1("适宜(" + i + ")");
                            tqxxBean.setValue2("气候适宜，推荐您进" +
                                    "行户外运动\n");
                        } else if (i <= 6000) {
                            tqxxBean.setValue1("中(" + i + ")");
                            tqxxBean.setValue2("易感人群应适当减少" +
                                    "室外活动\n");
                        } else {
                            tqxxBean.setValue1("较不宜(" + i + ")");
                            tqxxBean.setValue2("空气氧气含量低，请在" +
                                    "室内进行休闲运动");
                        }
                        tqxxBean = tqxxBeans.get(4);
                        i = get_all_sense.get_$Pm2536();
                        if (i < 30) {
                            tqxxBean.setValue1("优(" + i + ")");
                            tqxxBean.setValue2("空气质量非常好，非常" +
                                    "适合户外活动，趁机出" +
                                    "去多呼吸新鲜空气");
                        } else if (i <= 100) {
                            tqxxBean.setValue1("良(" + i + ")");
                            tqxxBean.setValue2("易感人群应" +
                                    "适当减少室" +
                                    "外活动");
                        } else {
                            tqxxBean.setValue1("污染(" + i + ")");
                            tqxxBean.setValue2("空气质量差，不适合户" +
                                    "外活动\n");
                        }
                        tqxxAda2.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                requestQueue.add(jsonObjectRequest1);
            }
        }, 0, 3000);
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

    private void initView(View view) {
        back = (ImageView) view.findViewById(R.id.back);
        refresh = (ImageView) view.findViewById(R.id.refresh);
        image = (ImageView) view.findViewById(R.id.image);
        WData = (TextView) view.findViewById(R.id.WData);
        city = (TextView) view.findViewById(R.id.city);
        WCurrent = (TextView) view.findViewById(R.id.WCurrent);
        gridView1 = (GridView) view.findViewById(R.id.gridView1);
        gridView2 = (GridView) view.findViewById(R.id.gridView2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
