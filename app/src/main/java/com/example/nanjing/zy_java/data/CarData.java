package com.example.nanjing.zy_java.data;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.util.Util;
import com.example.nanjing.zy_java.bean.AllCarBean;
import com.example.nanjing.zy_java.bean.AllPeccBean;
import com.example.nanjing.zy_java.bean.AllPersonBean;
import com.example.nanjing.zy_java.bean.AllTypeBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class CarData {
    private static List<AllCarBean.ROWSDETAILBean> allcar_list = new ArrayList<>();
    private static List<AllPeccBean.ROWSDETAILBean> allpecc_list = new ArrayList<>();
    private static List<AllPersonBean.ROWSDETAILBean> allperson_list = new ArrayList<>();
    private static List<AllTypeBean.ROWSDETAILBean> alltype_list = new ArrayList<>();
    private static Map map = new HashMap();
    private int flag = 0;
    private Context context;
    private final RequestQueue requestQueue;
    private String api_1 = "";
    private String api_2 = "";
    private String api_3 = "";
    private String api_4 = "";

    public static List<AllCarBean.ROWSDETAILBean> getAllcar_list() {
        return allcar_list;
    }

    public static List<AllPeccBean.ROWSDETAILBean> getAllpecc_list() {
        return allpecc_list;
    }

    public static List<AllPersonBean.ROWSDETAILBean> getAllperson_list() {
        return allperson_list;
    }

    public static List<AllTypeBean.ROWSDETAILBean> getAlltype_list() {
        return alltype_list;
    }

    public static Map getMap() {
        return map;
    }

    public CarData(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
        requestQueue = Volley.newRequestQueue(context);
        api_1 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_info";
        api_2 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_car_peccancy";
        api_3 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_user_info";
        api_4 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_peccancy_type";
        getB1();
        getB2();
        getB3();
        getB4();
    }

    private void getB1() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", Util.getUser(context));
            JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_1, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    AllCarBean bean = gson.fromJson(jsonObject.toString(), AllCarBean.class);
                    allcar_list = bean.getROWS_DETAIL();
                    flag++;
                    EventBus.getDefault().post("线程完成");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    getB1();
                    Toast.makeText(context, "car" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getB2() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", Util.getUser(context));
            JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_2, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    AllPeccBean bean = gson.fromJson(jsonObject.toString(), AllPeccBean.class);
                    allpecc_list = bean.getROWS_DETAIL();
                    flag++;
                    EventBus.getDefault().post("线程完成");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    getB2();
                    Toast.makeText(context, "car2" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getB3() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", Util.getUser(context));
            JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_3, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    AllPersonBean bean = gson.fromJson(jsonObject.toString(), AllPersonBean.class);
                    allperson_list = bean.getROWS_DETAIL();
                    flag++;
                    EventBus.getDefault().post("线程完成");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    getB3();
                    Toast.makeText(context, "car3" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getB4() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", Util.getUser(context));
            JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_4, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    AllTypeBean bean = gson.fromJson(jsonObject.toString(), AllTypeBean.class);
                    alltype_list = bean.getROWS_DETAIL();
                    flag++;
                    EventBus.getDefault().post("线程完成");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    getB4();
                    Toast.makeText(context, "car4" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String data) {
        switch (data) {
            case "线程完成":
                if (flag == 4) {
                    Toast.makeText(context, "数据读取完成", Toast.LENGTH_SHORT).show();
                    dealData();
                    getTu1();
                }
                break;
        }
    }

    private void getTu1() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[2];
        for (int i = 0; i < allcar_list.size(); i++) {
            if (allcar_list.get(i).getCount() > 0) {
                floats[0]++;
            } else if (allcar_list.get(i).getCount() == 0) {
                floats[1]++;
            }
        }

        x.add("有违章");
        x.add("无违章");
        y.add(floats[0]);
        y.add(floats[1]);
        map.put("x1", x);
        map.put("y1", y);

    }

    private void getTu2() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[2];
        for (int i = 0; i < allcar_list.size(); i++) {
            if (allcar_list.get(i).getCount() == 1) {
                floats[0]++;
            } else if (allcar_list.get(i).getCount() > 1) {
                floats[1]++;
            }
        }

        x.add("有违章");
        x.add("无违章");
        y.add(floats[0]);
        y.add(floats[1]);
        map.put("x2", x);
        map.put("y2", y);
    }

    private void getTu3() {
    }

    private void getTu4() {
    }

    private void getTu5() {
    }

    private void getTu6() {
    }

    private void getTu7() {
    }

    private void dealData() {
        for (int i = 0; i < allcar_list.size(); i++) {
            for (int j = 0; j < allpecc_list.size(); j++) {
                if (allcar_list.get(i).getCarnumber().equals(allpecc_list.get(j).getCarnumber())) {
                    int count = allcar_list.get(i).getCount();
                    count++;
                    allcar_list.get(i).setCount(count);
                }
            }
        }


        for (int i = 0; i < allperson_list.size(); i++) {
            for (int j = 0; j < allpecc_list.size(); j++) {
                if (allperson_list.get(i).getPcardid().equals(allcar_list.get(j).getPcardid()) && allcar_list.get(j).getCount() > 0) {
                    int count = allperson_list.get(i).getCount();
                    count++;
                    allperson_list.get(i).setCount(count);
                }
            }
        }


        for (int i = 0; i < alltype_list.size(); i++) {
            for (int j = 0; j < allpecc_list.size(); j++) {
                if (alltype_list.get(i).getPcode().equals(allpecc_list.get(j).getPcode())) {
                    int count = alltype_list.get(i).getCount();
                    count++;
                    alltype_list.get(i).setCount(count);
                }
            }
        }

    }
}
