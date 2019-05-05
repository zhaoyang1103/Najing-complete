package com.example.nanjing.st_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.st_java.adapter.GRZXAda;
import com.example.nanjing.st_java.bean.GRZXBean;
import com.example.nanjing.st_java.bean.Get_car_info;
import com.example.nanjing.util.UrlBean;
import com.example.nanjing.util.Util;
import com.example.nanjing.ws_java.etc.adapter.YueAdapter;
import com.example.nanjing.ws_java.etc.bean.YueBean;
import com.example.nanjing.zy_java.bean.AllCarBean;
import com.example.nanjing.zy_java.bean.AllPersonBean;
import com.example.nanjing.zy_java.data.CarData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private ArrayList<GRZXBean> grzxBeans;
    private GRZXAda grzxAda;

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
        grzxBeans = new ArrayList<>();
        grzxAda = new GRZXAda(grzxBeans, mContext);
        listView.setAdapter(grzxAda);
        requestQueue = Volley.newRequestQueue(mContext);

        List<AllPersonBean.ROWSDETAILBean> rows_detail = CarData.getAllperson_list();
        for (int i = 0; i < rows_detail.size(); i++) {
            AllPersonBean.ROWSDETAILBean anObject = rows_detail.get(i);
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
        getBalance();

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

    public void getBalance() {
        String URL = "http://" + Util.loadSetting(mContext).getUrl() + ":" + Util.loadSetting(mContext).getPort() + "/api/v2/get_car_account_balance";
        try {
            final List<AllCarBean.ROWSDETAILBean> allcar_list = CarData.getAllcar_list();
            for (int i = 0; i < allcar_list.size(); i++) {
                if (trim1.equals(allcar_list.get(i).getPcardid())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CarId", allcar_list.get(i).getNumber());
                    jsonObject.put("UserName", Util.getUser(mContext));

                    final int finalI = i;
                    requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            if (jsonObject.optString("RESULT").equals("S")) {
                                synchronized (this) {
                                    int balance = jsonObject.optInt("Balance");
                                    grzxBeans.add(new GRZXBean(finalI, getIcon(allcar_list.get(finalI).getCarbrand()),
                                            allcar_list.get(finalI).getCarnumber()
                                            , balance + ""));
                                    Collections.sort(grzxBeans, new Comparator<GRZXBean>() {
                                        @Override
                                        public int compare(GRZXBean o1, GRZXBean o2) {
                                            return o1.getId() - o2.getId();
                                        }
                                    });
                                    grzxAda.notifyDataSetChanged();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
