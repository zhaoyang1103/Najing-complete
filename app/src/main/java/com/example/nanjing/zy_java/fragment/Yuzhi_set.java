package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanjing.R;

public class Yuzhi_set extends Fragment {
    private TextView textview;
    private Switch sw;
    private EditText temperature;
    private EditText humidity;
    private EditText light;
    private EditText co;
    private EditText pm;
    private EditText road;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setyuzhi, container, false);

        initView(view);
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
    }

    private void submit() {
        // validate
        String temperatureString = temperature.getText().toString().trim();
        if (TextUtils.isEmpty(temperatureString)) {
            Toast.makeText(getContext(), "temperatureString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String humidityString = humidity.getText().toString().trim();
        if (TextUtils.isEmpty(humidityString)) {
            Toast.makeText(getContext(), "humidityString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String lightString = light.getText().toString().trim();
        if (TextUtils.isEmpty(lightString)) {
            Toast.makeText(getContext(), "lightString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String coString = co.getText().toString().trim();
        if (TextUtils.isEmpty(coString)) {
            Toast.makeText(getContext(), "coString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pmString = pm.getText().toString().trim();
        if (TextUtils.isEmpty(pmString)) {
            Toast.makeText(getContext(), "pmString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String roadString = road.getText().toString().trim();
        if (TextUtils.isEmpty(roadString)) {
            Toast.makeText(getContext(), "roadString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
