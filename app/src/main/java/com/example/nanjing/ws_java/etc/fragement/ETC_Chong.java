package com.example.nanjing.ws_java.etc.fragement;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.nanjing.ws_java.etc.bean.JiluBean;
import com.example.nanjing.ws_java.etc.dao.JiluDao;
import com.example.nanjing.zy_java.data.CarData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ETC_Chong extends Fragment implements View.OnClickListener {
    private EditText c_bianhao;
    private TextView c_chepai;
    private Button jine_10;
    private Button jine_50;
    private Button jine_100;
    private EditText c_jine;
    private Button c_chongzhi;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.etc_chongzhi, container, false);
        context = getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        c_bianhao = (EditText) view.findViewById(R.id.c_bianhao);
        c_chepai = (TextView) view.findViewById(R.id.c_chepai);
        jine_10 = (Button) view.findViewById(R.id.jine_10);
        jine_50 = (Button) view.findViewById(R.id.jine_50);
        jine_100 = (Button) view.findViewById(R.id.jine_100);
        c_jine = (EditText) view.findViewById(R.id.c_jine);
        c_chongzhi = (Button) view.findViewById(R.id.c_chongzhi);

        jine_10.setOnClickListener(this);
        jine_50.setOnClickListener(this);
        jine_100.setOnClickListener(this);
        c_chongzhi.setOnClickListener(this);

        c_bianhao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (!string.equals("")) {
                    if (Integer.parseInt(string) < 1 || Integer.parseInt(string) > 3) {
                        s.clear();
                    } else {
                        for (int i = 0; i < CarData.getAllcar_list().size(); i++) {
                            if (Integer.parseInt(string) == CarData.getAllcar_list().get(i).getNumber()) {
                                c_chepai.setText(CarData.getAllcar_list().get(i).getCarnumber());
                            }
                        }
                    }
                }
            }
        });

        c_jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (!string.equals("")) {
                    if (Integer.parseInt(string) < 1 || Integer.parseInt(string) > 999) {
                        s.clear();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jine_10:
                c_jine.setText("10");
                break;
            case R.id.jine_50:
                c_jine.setText("50");
                break;
            case R.id.jine_100:
                c_jine.setText("100");
                break;
            case R.id.c_chongzhi:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String bianhao = c_bianhao.getText().toString().trim();
        if (TextUtils.isEmpty(bianhao)) {
            Toast.makeText(getContext(), "小车编号不可空哦！", Toast.LENGTH_SHORT).show();
            return;
        }

        String jine = c_jine.getText().toString().trim();
        if (TextUtils.isEmpty(jine)) {
            Toast.makeText(getContext(), "充值金额不可空哦！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Charge(Integer.parseInt(bianhao),Integer.parseInt(jine));
        }
    }

    public void Charge(Integer carid, final Integer money){
        String URL = "http://"+ Util.loadSetting(context).getUrl() +":"+Util.loadSetting(context).getPort()+"/api/v2/set_car_account_recharge";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CarId",carid);
            jsonObject.put("Money",money);
            jsonObject.put("UserName",Util.getUser(context));
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")){
                        Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                        c_bianhao.setText("");
                        c_jine.setText("");
                        JiluBean jiluBean = new JiluBean(Util.getUser(context),
                                c_chepai.getText().toString(),
                                money,
                                new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()));
                        Log.i("ETC_Chong", "记录" + ":" + jiluBean.toString());
                        new JiluDao(context).add(jiluBean);
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
}
