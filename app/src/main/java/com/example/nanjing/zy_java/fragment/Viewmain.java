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

import java.util.ArrayList;
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
        if (CarData.getFlag() != 4) {
            getFragmentManager().beginTransaction().replace(R.id.maincontent, new FragementHome()).commit();
            Toast.makeText(getContext(), "数据还没加载完成", Toast.LENGTH_SHORT).show();
        }
        initView(view);
        tx_show.setText("有违章车辆和无违章车辆的占比统计");
        return view;
    }


    private void initView(View view) {
        tx_show = (TextView) view.findViewById(R.id.tx_show);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        glideview = (GlideView) view.findViewById(R.id.glideview);
        list = new ArrayList<>();
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
                glideview.setIndex(i, getContext());
                switch (i) {
                    case 0:
                        tx_show.setText("有违章车辆和无违章车辆的占比统计");
                        break;
                    case 1:
                        tx_show.setText(" 有无“重复违章记录的车辆”的占比统计");
                        break;
                    case 2:
                        tx_show.setText(" 违章车辆的违章次数占比分布");
                        break;
                    case 3:
                        tx_show.setText(" 年龄群体车辆违章的占比统计");
                        break;
                    case 4:
                        tx_show.setText("男性和女性有无车辆违章的占比统计");
                        break;
                    case 5:
                        tx_show.setText(" 每日时段内车辆违章的占比统计");
                        break;
                    case 6:
                        tx_show.setText(" 排名前十位的交通违法行为的占比统计");
                        break;


                }

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
        list.add(new Chart_4());
        list.add(new Chart_5());
        list.add(new Chart_6());
        list.add(new Chart_7());
        if (list.size() > 0) {
            glideview.setCount(list.size());
            glideview.setIndex(0, getContext());
        }
    }
}
