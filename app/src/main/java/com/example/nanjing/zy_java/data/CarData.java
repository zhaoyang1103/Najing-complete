package com.example.nanjing.zy_java.data;

import android.content.Context;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.i;

/**
 * Created by 昭阳 on 2019/4/29.
 */
public class CarData implements CarBean_abs {
    private Context context;
    private static List<AllCarBean.ROWSDETAILBean> allcarBean_list = new ArrayList<>();
    private static List<AllPeccBean.ROWSDETAILBean> allpeeeancyBean_list = new ArrayList<>();
    private static List<AllPersonBean.ROWSDETAILBean> allpersonBean_list = new ArrayList<>();
    private static List<AllTypeBean.ROWSDETAILBean> alltypeBean_list = new ArrayList<>();
    private static List<AllPeccBean.ROWSDETAILBean> allpecc_single = new ArrayList<>();
    private static int flag = 0;
    private static Map map = new HashMap();
    private final RequestQueue requestQueue;
    private String api_1 = "";
    private String api_2 = "";
    private String api_3 = "";
    private String api_4 = "";
    private final JSONObject object;
    private Map<String, Integer> peccmap = new HashMap<>();

    public CarData(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
        requestQueue = Volley.newRequestQueue(context);
        object = new JSONObject();
        try {
            object.put("UserName", Util.getUser(context));
            api_1 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_info";
            api_2 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_car_peccancy";
            api_3 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_user_info";
            api_4 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_peccancy_type";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getb1();
        getb2();
        getb3();
        getb4();

    }

    public static List<AllCarBean.ROWSDETAILBean> getAllcar_list() {
        return allcarBean_list;
    }

    public static void setAllcarBean_list(List<AllCarBean.ROWSDETAILBean> allcarBean_list) {
        CarData.allcarBean_list = allcarBean_list;
    }

    public static List<AllPeccBean.ROWSDETAILBean> getAllpeeeancyBean_list() {
        return allpeeeancyBean_list;
    }

    public static void setAllpeeeancy_list(List<AllPeccBean.ROWSDETAILBean> allpeeeancyBean_list) {
        CarData.allpeeeancyBean_list = allpeeeancyBean_list;
    }

    public static List<AllPersonBean.ROWSDETAILBean> getAllperson_list() {
        return allpersonBean_list;
    }

    public static void setAllpersonBean_list(List<AllPersonBean.ROWSDETAILBean> allpersonBean_list) {
        CarData.allpersonBean_list = allpersonBean_list;
    }

    public static List<AllTypeBean.ROWSDETAILBean> getAlltypeBean_list() {
        return alltypeBean_list;
    }

    public static void setAlltypeBean_list(List<AllTypeBean.ROWSDETAILBean> alltypeBean_list) {
        CarData.alltypeBean_list = alltypeBean_list;
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        CarData.flag = flag;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        CarData.map = map;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getdata(String data) {
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

    @Override
    public void getb1() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_1, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllCarBean allCarBean = gson.fromJson(jsonObject.toString(), AllCarBean.class);
                allcarBean_list = allCarBean.getROWS_DETAIL();
                i("CarData+1231", "" + allcarBean_list.size());
                flag++;
                EventBus.getDefault().post("线程完成");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                i("CarBean1", "" + volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void getb2() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_2, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPeccBean allCarBean = gson.fromJson(jsonObject.toString(), AllPeccBean.class);
                allpeeeancyBean_list = allCarBean.getROWS_DETAIL();
                flag++;
                EventBus.getDefault().post("线程完成");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                i("CarBean2", "" + volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void getb3() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_3, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPersonBean allCarBean = gson.fromJson(jsonObject.toString(), AllPersonBean.class);
                allpersonBean_list = allCarBean.getROWS_DETAIL();
                for (int i = 0; i < allpersonBean_list.size(); i++) {

                }
                flag++;
                EventBus.getDefault().post("线程完成");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                i("CarBean3", "" + volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void getb4() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_4, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllTypeBean allCarBean = gson.fromJson(jsonObject.toString(), AllTypeBean.class);
                alltypeBean_list = allCarBean.getROWS_DETAIL();
                flag++;
                EventBus.getDefault().post("线程完成");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                i("CarBean1", "" + volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void dealData() {

        for (int i = 0; i < allcarBean_list.size(); i++) {
            for (int j = 0; j < allpeeeancyBean_list.size(); j++) {
                if (allcarBean_list.get(i).getCarnumber().equals(allpeeeancyBean_list.get(j).getCarnumber())) {
                    int count = allcarBean_list.get(i).getCount();
                    count++;
                    allcarBean_list.get(i).setCount(count);
                }


            }
        }

        for (int i = 0; i < allpeeeancyBean_list.size(); i++) {
            for (int j = 0; j < allpeeeancyBean_list.size(); j++) {
                if (allpeeeancyBean_list.get(i).getCarnumber().equals(allpeeeancyBean_list.get(j).getCarnumber())) {
                    int count = allpeeeancyBean_list.get(i).getCount();
                    count++;
                    allpeeeancyBean_list.get(i).setCount(count);
                }
            }
        }
        for (int i = 0; i < allpeeeancyBean_list.size(); i++) {
            peccmap.put(allpeeeancyBean_list.get(i).getCarnumber(), allpeeeancyBean_list.get(i).getCount());
        }

        for (String key : peccmap.keySet()) {
            allpecc_single.add(new AllPeccBean.ROWSDETAILBean(key, peccmap.get(key)));
        }
        i("CarData", "违章车处理完毕");


        for (int i = 0; i < allpersonBean_list.size(); i++) {
            for (int j = 0; j < allcarBean_list.size(); j++) {
                if (allpersonBean_list.get(i).getPcardid().equals(allcarBean_list.get(j).getPcardid()) && allcarBean_list.get(j).getCount() > 0) {
                    int count = allpersonBean_list.get(i).getCount();
                    count++;
                    allpersonBean_list.get(i).setCount(count);
                }
            }
        }


        for (int i = 0; i < alltypeBean_list.size(); i++) {
            for (int j = 0; j < allpeeeancyBean_list.size(); j++) {
                if (alltypeBean_list.get(i).getPcode().equals(allpeeeancyBean_list.get(j).getPcode())) {
                    int count = alltypeBean_list.get(i).getCount();
                    count++;
                    alltypeBean_list.get(i).setCount(count);
                }


            }
        }

    }

    @Override
    public void getTu1() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[2];
        floats[0] = peccmap.size();
        floats[1] = allcarBean_list.size() - peccmap.size();

        x.add("有违章");
        x.add("无违章");
        y.add(floats[0]);
        y.add(floats[1]);
        i("图1", "" + Arrays.asList(y));
        map.put("x1", x);
        map.put("y1", y);
        i("getT1", "" + Arrays.asList(y));

    }

    @Override
    public void getTu2() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[2];
        for (int i = 0; i < allpecc_single.size(); i++) {
            if (allpecc_single.get(i).getCount() == 1) {
                floats[0]++;
            } else if (allpecc_single.get(i).getCount() > 1) {
                floats[1]++;
            }
        }
        x.add("无重复违章");
        x.add("有重复违章");
        y.add(floats[0]);
        y.add(floats[1]);
        map.put("x2", x);
        map.put("y2", y);
        i("getT2", "" + Arrays.asList(y));


    }

    @Override
    public void getTu3() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[3];
        for (int i = 0; i < allpecc_single.size(); i++) {
            if (allpecc_single.get(i).getCount() > 5) {
                floats[2]++;
            } else if (allpecc_single.get(i).getCount() > 2) {
                floats[1]++;
            } else if (allpecc_single.get(i).getCount() > 0) {
                floats[0]++;
            }
        }
        x.add("1-2条违章");
        x.add("3-5条违章");
        x.add("5条违章以上");
        y.add(floats[0] * 100 / allpecc_single.size());
        y.add(floats[1] * 100 / allpecc_single.size());
        y.add(floats[2] * 100 / allpecc_single.size());
        map.put("x3", x);
        map.put("y3", y);

    }

    @Override
    public void getTu4() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats = new float[2];
        float[] float1 = new float[2];
        float[] float2 = new float[2];
        float[] float3 = new float[2];
        float[] float4 = new float[2];
        y.add(floats);
        y.add(float1);
        y.add(float2);
        y.add(float3);
        y.add(float4);
        for (int i = 0; i < allcarBean_list.size(); i++) {
            int year = Integer.parseInt(allcarBean_list.get(i).getPcardid().substring(6, 10));
            if (allcarBean_list.get(i).getCount() > 0) {
                if (year >= 1990) {
                    y.get(0)[1]++;
                } else if (year >= 1980) {
                    y.get(1)[1]++;
                } else if (year >= 1970) {
                    y.get(2)[1]++;
                } else if (year >= 1960) {
                    y.get(3)[1]++;
                } else if (year >= 1950) {
                    y.get(4)[1]++;
                }

            } else {
                if (year >= 1990) {
                    y.get(0)[0]++;
                } else if (year >= 1980) {
                    y.get(1)[0]++;
                } else if (year >= 1970) {
                    y.get(2)[0]++;
                } else if (year >= 1960) {
                    y.get(3)[0]++;
                } else if (year >= 1950) {
                    y.get(4)[0]++;
                }

            }
        }

        float[] floats1 = new float[5];
        for (int i = 0; i < y.size(); i++) {
            floats1[i] = y.get(i)[0] + y.get(i)[1];
        }


        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < float1.length; j++) {
                y.get(i)[j] = y.get(i)[j] * 100 / floats1[i];
            }
        }
        x.add("90后");
        x.add("80后");
        x.add("70后");
        x.add("60后");
        x.add("50后");

        map.put("x4", x);
        map.put("y4", y);

    }

    @Override
    public void getTu5() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats = new float[2];
        float[] float1 = new float[2];

        y.add(floats);
        y.add(float1);

        for (int i = 0; i < allcarBean_list.size(); i++) {
            int a = allcarBean_list.get(i).getPcardid().charAt(17);

            if (allcarBean_list.get(i).getCount() > 0) {
                if (a % 2 == 0) {
                    y.get(1)[1]++;

                } else {
                    y.get(0)[1]++;
                }
            } else {
                if (a % 2 == 0) {
                    y.get(1)[0]++;

                } else {
                    y.get(0)[0]++;
                }
            }


        }
        float[] floats1 = new float[5];
        for (int i = 0; i < y.size(); i++) {
            floats1[i] = y.get(i)[0] + y.get(i)[1];
        }

        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < float1.length; j++) {
                y.get(i)[j] = y.get(i)[j] * 100 / floats1[i];
            }
        }
        x.add("女性");
        x.add("男性");

        map.put("x5", x);
        map.put("y5", y);

    }

    @Override
    public void getTu6() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[12];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        for (int i = 0; i < allpeeeancyBean_list.size(); i++) {
            try {
                int hour = Integer.parseInt(allpeeeancyBean_list.get(i).getDatetime().substring(11, 13));
                Date parse = simpleDateFormat.parse(allpeeeancyBean_list.get(i).getDatetime());
//                int hour = parse.getHours();
                if (hour >= 22) {
                    floats[11]++;
                } else if (hour >= 20) {
                    floats[10]++;

                } else if (hour >= 18) {
                    floats[9]++;

                } else if (hour >= 16) {
                    floats[8]++;

                } else if (hour >= 14) {
                    floats[7]++;

                } else if (hour >= 12) {
                    floats[6]++;

                } else if (hour >= 10) {
                    floats[5]++;

                } else if (hour >= 8) {
                    floats[4]++;

                } else if (hour >= 6) {
                    floats[3]++;

                } else if (hour >= 4) {
                    floats[2]++;
                } else if (hour >= 2) {
                    floats[1]++;
                } else if (hour >= 0) {
                    floats[0]++;

                }


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        i("零点", "" + floats[0]);
        i("中午12dian", "" + floats[6]);
        for (int i = 0; i < floats.length; i++) {
            floats[i] = floats[i] * 100 / allpeeeancyBean_list.size();
            y.add(floats[i]);
            x.add(i * 2 + ":00:01" + (i * 2 + 1) + ":00:00");
        }
        map.put("x6", x);
        map.put("y6", y);

    }

    @Override
    public void getTu7() {
        Collections.sort(alltypeBean_list, new Comparator<AllTypeBean.ROWSDETAILBean>() {
            @Override
            public int compare(AllTypeBean.ROWSDETAILBean o1, AllTypeBean.ROWSDETAILBean o2) {
                return o2.getCount() - o1.getCount();
            }
        });
        float all = 0;
        for (int i = 0; i < 10; i++) {
            all += alltypeBean_list.get(i).getCount();
        }
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        for (int i = 10; i > -1; i--) {
            x.add(alltypeBean_list.get(i).getPremarks());
            y.add(alltypeBean_list.get(i).getCount() * 100 / all);
        }
        map.put("x7", x);
        map.put("y7", y);
    }


}
