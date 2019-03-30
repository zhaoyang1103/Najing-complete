package com.example.nanjing.zy_java.fragment;
/*
 * Created by 王森 on WangSen.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanjing.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Design extends Fragment implements View.OnClickListener {
    private TextView textview;
    private Button bt_start;
    private ImageView left;
    private ImageView right;
    private GridView gv_list;
    private List<String> list;
    private int a = 0;
    private boolean flag = true;
    private Context context;
    private Random random;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.design, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        bt_start = (Button) view.findViewById(R.id.bt_start);
        left = (ImageView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        gv_list = (GridView) view.findViewById(R.id.gv_list);
        bt_start.setOnClickListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("鲁88888");
        }
        adapter = new Adapter();
        gv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                if (bt_start.getText().equals("开始")) {
                    Thread thread = new Thread(new Thstart());
                    thread.start();
                    bt_start.setText("停止");
                } else if (bt_start.getText().equals("停止")) {
                    flag = false;
                    bt_start.setText("选择一下车牌号为自己所属");
                    a++;
                }

                break;
        }
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getContext(), R.layout.item_gesign, null);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.carnumber.setText(list.get(position));
            viewHolder.carnumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a == 1) {
                        new AlertDialog.Builder(context).setMessage("确定要选择这个车牌号么:" + list.get(position)).setPositiveButton("确定选择", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "选择成功", Toast.LENGTH_SHORT).show();
                                bt_start.setText("恭喜你获得车牌号" + list.get(position));
                                viewHolder.carnumber.setBackgroundResource(R.drawable.hong);
                                a++;
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                    }
                }
            });

            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView carnumber;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.carnumber = (TextView) rootView.findViewById(R.id.carnumber);
            }

        }
    }

    class Thstart extends Thread {
        private Random random;

        @Override
        public void run() {
            random = new Random();
            while (flag) {
                try {
                    sleep(200);
                    for (int i = 0; i < 20; i++) {
                        int a = random.nextInt(90000) + 10000;
                        list.set(i, "鲁" + a);
                    }
                    handler.sendEmptyMessage(0);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


            super.run();
        }
    }
}
