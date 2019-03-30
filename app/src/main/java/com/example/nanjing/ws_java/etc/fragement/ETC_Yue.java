package com.example.nanjing.ws_java.etc.fragement;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.util.Util;
import com.example.nanjing.ws_java.etc.adapter.YueAdapter;
import com.example.nanjing.ws_java.etc.bean.YueBean;
import com.example.nanjing.zy_java.data.CarData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ETC_Yue extends Fragment {
    private ListView listview2;
    private Context context;
    private List<YueBean> list;
    private YueAdapter adapter;
    private int a = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.etc_yue, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        getBalance();
        return view;
    }

    private void initView(View view) {
        listview2 = (ListView) view.findViewById(R.id.listview2);
    }

    public void getBalance() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_account_balance";
        a = 0;
        try {
            for (int i = 0; i < 3; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CarId", i + 1);
                jsonObject.put("UserName", Util.getUser(context));
                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int balance = jsonObject.optInt("Balance");
                            for (int i = 0; i < CarData.getAllcar_list().size(); i++) {
                                for (int j = 0; j < CarData.getAllperson_list().size(); j++) {
                                    if (CarData.getAllcar_list().get(i).getPcardid().equals(CarData.getAllperson_list().get(j).getPcardid())) {
                                        if (finalI + 1 == CarData.getAllcar_list().get(i).getNumber()) {
                                            list.add(new YueBean(finalI + 1,
                                                    CarData.getAllcar_list().get(i).getCarbrand(),
                                                    CarData.getAllcar_list().get(i).getCarnumber(),
                                                    CarData.getAllperson_list().get(j).getPname(),
                                                    balance));
                                            a++;
                                            if (a == 3) {
                                                Collections.sort(list, new Comparator<YueBean>() {
                                                    @Override
                                                    public int compare(YueBean o1, YueBean o2) {
                                                        return o1.getId() - o2.getId();
                                                    }
                                                });
                                                adapter = new YueAdapter(context,list);
                                                listview2.setAdapter(adapter);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
