package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanjing.FragementHome;
import com.example.nanjing.R;
import com.example.nanjing.zy_java.data.CarData;
import com.example.nanjing.zy_java.view.GlideView;

import java.util.List;

public class Viewmain extends Fragment {
    private TextView textview;
    private TextView tx_show;
    private ViewPager viewpager;
    private GlideView glideview;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewmain, container, false);
        if(CarData.getFlag()!=4)
        {
            getFragmentManager().beginTransaction().replace(R.id.maincontent,new FragementHome()).commit();
            Toast.makeText(getContext(), "数据还没加载完成", Toast.LENGTH_SHORT).show();
        }
        initView(view);
        return view;
    }


    private void initView(View view) {
        tx_show = (TextView) view.findViewById(R.id.tx_show);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        glideview = (GlideView) view.findViewById(R.id.glideview);
        addData();
        viewpager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                glideview.setIndex(i,getContext());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void addData() {
        list.add(new Chart_1());
        list.add(new Chart_2());
        list.add(new Chart_3());
        if (list.size() > 0) {
            glideview.setCount(list.size());
            glideview.setIndex(0, getContext());
        }
    }
}
