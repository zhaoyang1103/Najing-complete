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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import static android.util.Log.i;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class CarData {
    private static List<AllCarBean.ROWSDETAILBean> allcar_list = new ArrayList<>();
    private static List<AllPeccBean.ROWSDETAILBean> allpecc_list = new ArrayList<>();
    private static List<AllPersonBean.ROWSDETAILBean> allperson_list = new ArrayList<>();
    private static List<AllTypeBean.ROWSDETAILBean> alltype_list = new ArrayList<>();
    private static Map map = new HashMap();
    private static int flag = 0;
    private Context context;
    private final RequestQueue requestQueue;
    private String api_1 = "";
    private String api_2 = "";
    private String api_3 = "";
    private String api_4 = "";

    public static int getFlag() {
        return flag;
    }

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
                    getTu2();
                    getTu3();
                    getTu4();
                    getTu5();
                    getTu6();
                    getTu7();
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
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[3];
        for (int i = 0; i < allcar_list.size(); i++) {
            if (allcar_list.get(i).getCount() > 5) {
                floats[2]++;
            } else if (allcar_list.get(i).getCount() > 2) {
                floats[1]++;
            } else if (allcar_list.get(i).getCount() > 0) {
                floats[0]++;
            }
        }

        x.add("1-2条违章");
        x.add("3-5条违章");
        x.add("5条违章以上");
        y.add(floats[0]);
        y.add(floats[1]);
        y.add(floats[2]);
        map.put("x3", x);
        map.put("y3", y);
    }

    private void getTu4() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats1 = new float[2];
        float[] floats2 = new float[2];
        float[] floats3 = new float[2];
        float[] floats4 = new float[2];
        float[] floats5 = new float[2];
        for (int i = 0; i < allperson_list.size(); i++) {
            int year = Integer.parseInt(allperson_list.get(i).getPcardid().substring(6, 10));

            if (allperson_list.get(i).getCount() == 0) {
                if (year > 1990) {
                    floats1[0]++;
                } else if (year > 1980) {
                    floats2[0]++;
                } else if (year > 1970) {
                    floats3[0]++;
                } else if (year > 1960) {
                    floats4[0]++;
                } else if (year > 1950) {
                    floats5[0]++;
                }


            } else {
                if (year > 1990) {
                    floats1[1]++;
                } else if (year > 1980) {
                    floats2[1]++;
                } else if (year > 1970) {
                    floats3[1]++;
                } else if (year > 1960) {
                    floats4[1]++;
                } else if (year > 1950) {
                    floats5[1]++;
                }
            }


        }
        for (int i = 0; i < floats1.length; i++) {
            floats1[i] = floats1[i] * 100 / allperson_list.size();
            floats2[i] = floats2[i] * 100 / allperson_list.size();
            floats3[i] = floats3[i] * 100 / allperson_list.size();
            floats4[i] = floats4[i] * 100 / allperson_list.size();
            floats5[i] = floats5[i] * 100 / allperson_list.size();
        }


        x.add("90后");
        x.add("80后");
        x.add("70后");
        x.add("60后");
        x.add("50后");
        y.add(floats1);
        y.add(floats2);
        y.add(floats3);
        y.add(floats4);
        y.add(floats5);
        map.put("x4", x);
        map.put("y4", y);


    }

    private void getTu5() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats1 = new float[2];
        float[] floats2 = new float[2];

        for (int i = 0; i < allperson_list.size(); i++) {
            String year = allperson_list.get(i).getPsex();

            if (allperson_list.get(i).getCount() == 0) {
                if (year.equals("女")) {
                    floats1[0]++;
                } else if (year.equals("男")) {
                    floats2[0]++;
                }


            } else {
                if (year.equals("女")) {
                    floats1[1]++;
                } else if (year.equals("男")) {
                    floats2[1]++;
                }
            }


        }
        for (int i = 0; i < floats1.length; i++) {
            floats1[i] = floats1[i] * 100 / allperson_list.size();
            floats2[i] = floats2[i] * 100 / allperson_list.size();
        }
        x.add("女性");
        x.add("男性");

        y.add(floats1);
        y.add(floats2);

        map.put("x5", x);
        map.put("y5", y);
    }

    private void getTu6() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats2 = new float[12];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < allpecc_list.size(); i++) {
            i("CarData", ""+allpecc_list.get(i).getDatetime());
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(allpecc_list.get(i).getDatetime());
                int hour = parse.getHours();
                i("CarData", ""+hour);
                if (hour >= 22) {
                    floats2[11]++;
                } else if (hour >= 20) {
                    floats2[10]++;
                } else if (hour >= 18) {
                    floats2[9]++;
                } else if (hour >= 16) {
                    floats2[8]++;
                } else if (hour >= 14) {
                    floats2[7]++;
                } else if (hour >= 12) {
                    floats2[6]++;
                } else if (hour >= 10) {
                    floats2[5]++;
                } else if (hour >= 8) {
                    floats2[4]++;
                } else if (hour >= 6) {
                    floats2[3]++;
                } else if (hour >= 4) {
                    floats2[2]++;
                } else if (hour >= 2) {
                    floats2[1]++;
                } else if (hour >= 0) {
                    floats2[0]++;
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < floats2.length; i++) {
            x.add(i * 2 + ":00:01-" + (i * 2 + 1) + ":00:00");
            floats2[i] = floats2[i] * 100 / allpecc_list.size();
            y.add(floats2[i]);
        }
        map.put("x6", x);
        map.put("y6", y);

    }

    private void getTu7() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        Collections.sort(alltype_list, new Comparator<AllTypeBean.ROWSDETAILBean>() {
            @Override
            public int compare(AllTypeBean.ROWSDETAILBean o1, AllTypeBean.ROWSDETAILBean o2) {
                return o2.getCount() - o1.getCount();
            }
        });
        float all = 0;
        for (int i = 0; i < 10; i++) {
            all += alltype_list.get(i).getCount();
        }
        for (int i = 10; i > -1; i--) {
            x.add(alltype_list.get(i).getPremarks());
            y.add(alltype_list.get(i).getCount() * 100 / all);
        }
        map.put("x7", x);
        map.put("y7", y);
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
            for (int j = 0; j < allcar_list.size(); j++) {
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
