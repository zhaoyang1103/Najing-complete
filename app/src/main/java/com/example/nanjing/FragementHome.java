package com.example.nanjing;
/*
 * Created by 王森 on WangSen.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nanjing.fragment.Fragment_1;
import com.example.nanjing.fragment.Fragment_2;
import com.example.nanjing.fragment.Fragment_3;
import com.example.nanjing.fragment.home.HomeAdapter;
import com.example.nanjing.fragment.home.HomeBean;
import com.example.nanjing.st_java.fragment.GRZXFra;
import com.example.nanjing.st_java.fragment.TQXXFra;
import com.example.nanjing.ws_java.etc.fragement.ETC_Main;
import com.example.nanjing.zy_java.fragment.Design;
import com.example.nanjing.zy_java.fragment.Viewmain;
import com.example.nanjing.ws_java.subway.Subway_Main;
import com.example.nanjing.zy_java.fragment.Yuzhi_change;

import java.util.ArrayList;
import java.util.List;

public class FragementHome extends Fragment {
    private GridView home_grid;
    private TextView tv_title;

    private Context context;
    private HomeAdapter home_adapter;

    private List<HomeBean> home_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        home_list = new ArrayList<>();
        initView(view);
        setData();
        if (getActivity().findViewById(R.id.pain).getVisibility() == View.VISIBLE) {
            getActivity().findViewById(R.id.pain).setVisibility(View.GONE);
        }
        home_adapter = new HomeAdapter(context, home_list);
        home_grid.setAdapter(home_adapter);
        showData();
        return view;
    }

    private void setData() {
        home_list.add(new HomeBean(R.drawable.touxiang_2, "个人中心"));
        home_list.add(new HomeBean(R.drawable.icon_busstop, "数据分析"));
        home_list.add(new HomeBean(R.drawable.icon101, "天气信息"));
        home_list.add(new HomeBean(R.drawable.icon_busstop, "环境指标"));
        home_list.add(new HomeBean(R.drawable.icon_etc, "高速ETC"));
        home_list.add(new HomeBean(R.drawable.icon_light, "地铁查询"));
        home_list.add(new HomeBean(R.drawable.icon_busstop, "创意设计"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) context;
        tv_title = (TextView) mainActivity.findViewById(R.id.tv_title);
    }

    private void showData() {
        home_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new GRZXFra()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new Viewmain()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new TQXXFra()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.GONE);
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new Yuzhi_change()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.GONE);
                        break;
                    case 4:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new ETC_Main()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new Subway_Main()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, new Design()).commit();
                        tv_title.setText(home_list.get(position).getHome_text());
                        getActivity().findViewById(R.id.pain).setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void initView(View view) {
        home_grid = (GridView) view.findViewById(R.id.home_grid);
    }
}
