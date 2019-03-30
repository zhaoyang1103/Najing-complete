package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanjing.R;
import com.example.nanjing.zy_java.util.Threshold;

public class Yuzhi_set extends Fragment {
    private TextView textview;
    private Switch sw;
    private EditText temperature;
    private EditText humidity;
    private EditText light;
    private EditText co;
    private EditText pm;
    private EditText road;
    private Context context;
    private ImageView imageBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setyuzhi, container, false);
        context = getContext();
        initView(view);
        sw.setChecked(Threshold.getSwitch(context));
        if (Threshold.getSwitch(context)) {
            temperature.setEnabled(false);
            humidity.setEnabled(false);
            light.setEnabled(false);
            co.setEnabled(false);
            pm.setEnabled(false);
            road.setEnabled(false);
        } else {
            temperature.setEnabled(true);
            humidity.setEnabled(true);
            light.setEnabled(true);
            co.setEnabled(true);
            pm.setEnabled(true);
            road.setEnabled(true);
        }

        temperature.setText(Threshold.getYuZhi(context, "wendu") + "");
        humidity.setText(Threshold.getYuZhi(context, "shidu") + "");
        light.setText(Threshold.getYuZhi(context, "guangzhao") + "");
        co.setText(Threshold.getYuZhi(context, "co2") + "");
        pm.setText(Threshold.getYuZhi(context, "pm25") + "");
        road.setText(Threshold.getYuZhi(context, "status") + "");
        return view;
    }

    private void initView(View view) {
        sw = (Switch) view.findViewById(R.id.sw);
        temperature = (EditText) view.findViewById(R.id.temperature);
        humidity = (EditText) view.findViewById(R.id.humidity);
        light = (EditText) view.findViewById(R.id.light);
        co = (EditText) view.findViewById(R.id.co);
        pm = (EditText) view.findViewById(R.id.pm);
        road = (EditText) view.findViewById(R.id.road);
        imageBack = (ImageView) view.findViewById(R.id.imageView_Back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        sw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temperatureString = temperature.getText().toString().trim();
                String humidityString = humidity.getText().toString().trim();
                String lightString = light.getText().toString().trim();
                String coString = co.getText().toString().trim();
                String pmString = pm.getText().toString().trim();
                String roadString = road.getText().toString().trim();

                if (TextUtils.isEmpty(temperatureString)) {
                    Toast.makeText(getContext(), "温度阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else if (TextUtils.isEmpty(humidityString)) {
                    Toast.makeText(getContext(), "湿度阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else if (TextUtils.isEmpty(lightString)) {
                    Toast.makeText(getContext(), "光照阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else if (TextUtils.isEmpty(coString)) {
                    Toast.makeText(getContext(), "Co2阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else if (TextUtils.isEmpty(pmString)) {
                    Toast.makeText(getContext(), "PM2.5阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else if (TextUtils.isEmpty(roadString)) {
                    Toast.makeText(getContext(), "道路状况阈值不能为空", Toast.LENGTH_SHORT).show();
                    sw.setChecked(false);
                    return;
                } else {
                    Threshold.saveSwitch(context, sw.isChecked());
                    Threshold.saveYuZhi(context,
                            Integer.parseInt(temperatureString),
                            Integer.parseInt(humidityString),
                            Integer.parseInt(lightString),
                            Integer.parseInt(coString),
                            Integer.parseInt(pmString),
                            Integer.parseInt(roadString));
                    if (sw.isChecked()) {
                        temperature.setEnabled(false);
                        humidity.setEnabled(false);
                        light.setEnabled(false);
                        co.setEnabled(false);
                        pm.setEnabled(false);
                        road.setEnabled(false);
                    } else {
                        temperature.setEnabled(true);
                        humidity.setEnabled(true);
                        light.setEnabled(true);
                        co.setEnabled(true);
                        pm.setEnabled(true);
                        road.setEnabled(true);
                    }
                }
            }
        });
    }

}
