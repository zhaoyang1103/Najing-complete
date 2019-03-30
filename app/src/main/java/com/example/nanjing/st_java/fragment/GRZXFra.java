package com.example.nanjing.st_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.st_java.adapter.GRZXAda;
import com.example.nanjing.st_java.bean.GRZXBean;
import com.example.nanjing.st_java.bean.Get_all_user_info;
import com.example.nanjing.st_java.bean.Get_car_info;
import com.example.nanjing.util.UrlBean;
import com.example.nanjing.util.Util;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GRZXFra extends Fragment {
    private ImageView image;
    private TextView username;
    private TextView pname;
    private TextView psex;
    private TextView ptel;
    private TextView pcardid;
    private TextView pregistdate;
    private ListView listView;
    private Context mContext;
    private UrlBean urlBean;
    private RequestQueue requestQueue;
    private String user;
    private String trim1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_grzx, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mContext = getContext();
        urlBean = Util.loadSetting(mContext);
        user = Util.getUser(mContext);
        requestQueue = Volley.newRequestQueue(mContext);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_user_info", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                Get_all_user_info get_all_user_info = gson.fromJson(jsonObject.toString(), Get_all_user_info.class);
                List<Get_all_user_info.ROWSDETAILBean> rows_detail = get_all_user_info.getROWS_DETAIL();
                for (int i = 0; i < rows_detail.size(); i++) {
                    Get_all_user_info.ROWSDETAILBean anObject = rows_detail.get(i);
                    if (user.equals(anObject.getUsername())) {
                        username.setText("用户名:" + user);
                        pname.setText("姓名:" + anObject.getPname().trim());
                        String trim = anObject.getPsex().trim();
                        if (trim.equals("男")) {
                            image.setBackgroundResource(R.drawable.touxiang_2);
                        } else {
                            image.setBackgroundResource(R.drawable.touxiang_1);
                        }
                        psex.setText("性别:" + trim);
                        ptel.setText("手机:" + anObject.getPtel().trim());
                        trim1 = anObject.getPcardid().trim();
                        pcardid.setText("身份证号:" + trim1.substring(0, 6) + "*********" + trim1.substring(15));
                        String pregistdate = anObject.getPregistdate().trim();
                        GRZXFra.this.pregistdate.setText("注册时间:" + pregistdate.substring(0, 4) + "." + pregistdate.substring(5, 7) + "." + pregistdate.substring(8, 10));
                        break;
                    }
                }

                JSONObject jsonRequest = new JSONObject();
                try {
                    jsonRequest.put("UserName", "user1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_car_info", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson1 = new Gson();
                        Get_car_info get_car_info = gson1.fromJson(jsonObject.toString(), Get_car_info.class);
                        List<Get_car_info.ROWSDETAILBean> rows_detail1 = get_car_info.getROWS_DETAIL();
                        ArrayList<GRZXBean> grzxBeans = new ArrayList<>();
                        for (int i = 0; i < rows_detail1.size(); i++) {
                            Get_car_info.ROWSDETAILBean rowsdetailBean = rows_detail1.get(i);
                            if (trim1.equals(rowsdetailBean.getPcardid())) {
                                grzxBeans.add(new GRZXBean(getIcon(rowsdetailBean.getCarbrand()), rowsdetailBean.getCarnumber(), rowsdetailBean.getNumber() + ""));
                            }
                        }
                        GRZXAda grzxAda = new GRZXAda(grzxBeans, mContext);
                        listView.setAdapter(grzxAda);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                requestQueue.add(jsonObjectRequest1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private int getIcon(String carbrand) {
        try {
            Field field = R.drawable.class.getField(carbrand);
            return field.getInt(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void initView(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        username = (TextView) view.findViewById(R.id.username);
        pname = (TextView) view.findViewById(R.id.pname);
        psex = (TextView) view.findViewById(R.id.psex);
        ptel = (TextView) view.findViewById(R.id.ptel);
        pcardid = (TextView) view.findViewById(R.id.pcardid);
        pregistdate = (TextView) view.findViewById(R.id.pregistdate);
        listView = (ListView) view.findViewById(R.id.listView);
    }
}
