package com.example.nanjing.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nanjing.R;

public class Fragment_1 extends Fragment {
    private TextView textview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout01, container, false);
        initView(view);
        textview.setText("this is fragment 1");
        return view;
    }

    private void initView(View view) {
        textview = (TextView) view.findViewById(R.id.textview);
    }
}
