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
import android.widget.ImageView;
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
import com.example.nanjing.ws_java.etc.bean.GaoBean;
import com.example.nanjing.ws_java.etc.util.MyText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ETC_Main extends Fragment implements View.OnClickListener {
    private MyText float_text;
    private ImageView etc_chongzhi;
    private ImageView etc_yue;
    private ImageView etc_jilu;
    private TextView tv_title;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.etc_main, container, false);
        context = getContext();
        initView(view);
        tv_title.setText("高速ETC");
        getGao();
        return view;
    }

    public void getGao() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_notice";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUser(context));
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Gson gson = new Gson();
                        List<GaoBean.ROWSDETAILBean> rows_detail = gson.fromJson(jsonObject.toString(), GaoBean.class).getROWS_DETAIL();
                        StringBuilder builder = new StringBuilder("");
                        for (int i = 0; i < rows_detail.size(); i++) {
                            builder.append(rows_detail.get(i).getTitle() + "    ");
                        }
                        float_text.setText(builder);
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
        float_text = (MyText) view.findViewById(R.id.float_text);
        etc_chongzhi = (ImageView) view.findViewById(R.id.etc_chongzhi);
        etc_yue = (ImageView) view.findViewById(R.id.etc_yue);
        etc_jilu = (ImageView) view.findViewById(R.id.etc_jilu);

        etc_yue.setOnClickListener(this);
        etc_jilu.setOnClickListener(this);
        etc_chongzhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etc_chongzhi:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new ETC_Chong()).commit();
                tv_title.setText("ETC充值");
                break;
            case R.id.etc_yue:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new ETC_Yue()).commit();
                tv_title.setText("ETC查询");
                break;
            case R.id.etc_jilu:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new ETC_Jilu()).commit();
                tv_title.setText("充值记录");
                break;
        }
    }
}
