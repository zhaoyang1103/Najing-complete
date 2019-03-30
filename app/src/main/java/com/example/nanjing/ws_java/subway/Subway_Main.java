package com.example.nanjing.ws_java.subway;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.MainActivity;
import com.example.nanjing.R;
import com.example.nanjing.util.Util;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Subway_Main extends Fragment {
    private ListView listview1;
    private TextView tv_title;

    private List<SubwayBean.ROWSDETAILBean> list;
    private Context context;
    private SubwayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subway_main, container, false);
        context = getContext();
        list = new ArrayList<>();
        initView(view);
        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
        tv_title.setText("地铁查询");
        getData();
        return view;
    }

    public void getData(){
        String URL = "http://"+ Util.loadSetting(context).getUrl() +":"+Util.loadSetting(context).getPort()+"/api/v2/get_metro";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName",Util.getUser(context));
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")){
                        Gson gson = new Gson();
                        list = gson.fromJson(jsonObject.toString(), SubwayBean.class).getROWS_DETAIL();
                        adapter = new SubwayAdapter(context, list, Subway_Main.this);
                        listview1.setAdapter(adapter);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, volleyError.toString(), Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) context;
        tv_title = (TextView) mainActivity.findViewById(R.id.tv_title);
    }

    private void initView(View view) {
        listview1 = (ListView) view.findViewById(R.id.listview1);
    }
}
