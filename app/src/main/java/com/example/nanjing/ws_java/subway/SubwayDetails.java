package com.example.nanjing.ws_java.subway;
/*
 * Created by 王森 on 2019/3/30.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.R;
import com.example.nanjing.util.Util;

import org.json.JSONObject;

public class SubwayDetails extends Fragment {
    private ImageView imageView_Back;
    private TextView tv_title;
    private LinearLayout pain;
    private ImageView details_img;
    private static SubwayDetails fragment;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subway_details, container, false);
        context = getContext();
        initView(view);
        getActivity().findViewById(R.id.pain).setVisibility(View.GONE);
        tv_title.setText(fragment.getArguments().getString("title"));
        setMapimg();
        return view;
    }


    public void setMapimg(){
        String URL = "http://"+Util.loadSetting(context).getUrl() +":"+Util.loadSetting(context).getPort()+"/api/image/"+fragment.getArguments().getString("map");
        Log.i("SubwayAdapter", "map" + ":" + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                details_img.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public static SubwayDetails newInstance(Bundle args) {
        fragment = new SubwayDetails();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        imageView_Back = (ImageView) view.findViewById(R.id.imageView_Back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        pain = (LinearLayout) view.findViewById(R.id.pain);
        details_img = (ImageView) view.findViewById(R.id.details_img);

        imageView_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}
