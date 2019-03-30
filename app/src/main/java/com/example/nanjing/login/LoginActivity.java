package com.example.nanjing.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nanjing.GuideActivity;
import com.example.nanjing.MainActivity;
import com.example.nanjing.R;
import com.example.nanjing.util.LoadingDialog;
import com.example.nanjing.util.UrlBean;
import com.example.nanjing.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity implements OnClickListener {

    private UrlBean urlBean;
    private String urlHttp;
    private String urlPort = "8080";

    EditText etUserName, etUserPwd;
    Button btLogin, btReg, btNetSetting;
    private SharedPreferences st_sp;
    private Button bt_net_setting;
    private EditText et_user_name;
    private ImageView imageView1;
    private EditText et_user_pwd;
    private CheckBox jzpwdCB;
    private CheckBox autologCB;
    private Button bt_login;
    private Button bt_reg;
    private RelativeLayout RelativeLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        st_sp = getSharedPreferences("st_sp", MODE_PRIVATE);
        boolean isFirst = st_sp.getBoolean("isFirst", true);
        if (isFirst) {
            st_sp.edit().putBoolean("isFirst", false).commit();
            Intent intent = new Intent(LoginActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login);
        initView();
        boolean jzpwdCB = st_sp.getBoolean("jzpwdCB", false);
        this.jzpwdCB.setChecked(jzpwdCB);
        if (jzpwdCB) {
            etUserName.setText(st_sp.getString("etUserName", ""));
            etUserPwd.setText(st_sp.getString("etUserPwd", ""));
        }
        boolean autologCB = st_sp.getBoolean("autologCB", false);
        this.autologCB.setChecked(autologCB);
        if (autologCB) {
            final String userName = etUserName.getText().toString();
            final String userPwd = etUserPwd.getText().toString();

            LoadingDialog.showDialog(LoginActivity.this);
            JSONObject params = new JSONObject();
            try {
                params.put("UserName", userName);
                params.put("UserPwd", userPwd);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.d("TAG", params.toString());

            //fujian
            String strUrl = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/user_login";


            Log.d("TAG", strUrl);

            RequestQueue mQueue = Volley.newRequestQueue(LoginActivity.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // TODO Auto-generated method stu
                    Log.d("TAG", response.toString());
                    LoadingDialog.disDialog();
                    if (response.optString("RESULT").equals("S")) {
                        Util.saveUSer(userName, LoginActivity.this);
                        st_sp.edit().putString("etUserName",userName).putString("etUserPwd",userPwd).commit();
                        Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.optString("RESULT").equals("F")) {
                        Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    LoadingDialog.disDialog();
                    Log.d("TAG volley error", error.toString());
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                }
            });
            mQueue.add(jsonObjectRequest);
        }
        initLiserter();
    }

    private void initView() {
        // TODO Auto-generated method stub
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserPwd = (EditText) findViewById(R.id.et_user_pwd);
        btLogin = (Button) findViewById(R.id.bt_login);
        btReg = (Button) findViewById(R.id.bt_reg);
        btNetSetting = (Button) findViewById(R.id.bt_net_setting);

        urlBean = Util.loadSetting(LoginActivity.this);

        bt_net_setting = (Button) findViewById(R.id.bt_net_setting);
        bt_net_setting.setOnClickListener(this);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_name.setOnClickListener(this);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(this);
        et_user_pwd = (EditText) findViewById(R.id.et_user_pwd);
        et_user_pwd.setOnClickListener(this);
        jzpwdCB = (CheckBox) findViewById(R.id.jzpwdCB);
        jzpwdCB.setOnClickListener(this);
        autologCB = (CheckBox) findViewById(R.id.autologCB);
        autologCB.setOnClickListener(this);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        bt_reg = (Button) findViewById(R.id.bt_reg);
        bt_reg.setOnClickListener(this);
        RelativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        RelativeLayout1.setOnClickListener(this);
    }

    private void initLiserter() {
        // TODO Auto-generated method stub
        btReg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(LoginActivity.this,
                        RegActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                final String userName = etUserName.getText().toString();
                final String userPwd = etUserPwd.getText().toString();

                LoadingDialog.showDialog(LoginActivity.this);
                JSONObject params = new JSONObject();
                try {
                    params.put("UserName", userName);
                    params.put("UserPwd", userPwd);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.d("TAG", params.toString());

                //fujian
                String strUrl = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/user_login";


                Log.d("TAG", strUrl);

                RequestQueue mQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO Auto-generated method stu
                        Log.d("TAG", response.toString());
                        LoadingDialog.disDialog();
                        if (response.optString("RESULT").equals("S")) {
                            Util.saveUSer(userName, LoginActivity.this);
                            st_sp.edit().putString("etUserName",userName).putString("etUserPwd",userPwd).commit();
                            Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (response.optString("RESULT").equals("F")) {
                            Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        LoadingDialog.disDialog();
                        Log.d("TAG volley error", error.toString());
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                });
                mQueue.add(jsonObjectRequest);
            }
        });

        btNetSetting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
                urlSettingDialog.show();
                urlSettingDialog.setTitle("Setting");
                urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
                final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
                final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

                edit_urlHttp.setText(urlBean.getUrl());
                edit_urlPort.setText(urlBean.getPort());
                Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
                Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
                save.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlHttp = edit_urlHttp.getText().toString();
                        urlPort = edit_urlPort.getText().toString();

                        if (urlHttp == null || urlHttp.equals("")) {
                            Toast.makeText(LoginActivity.this, R.string.error_ip, Toast.LENGTH_LONG).show();
                        } else {
                            Util.saveSetting(urlHttp, urlPort, LoginActivity.this);
                            urlBean = Util.loadSetting(LoginActivity.this);
                            urlSettingDialog.dismiss();
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlSettingDialog.dismiss();
                    }
                });
                urlSettingDialog.show();
            }
        });


        jzpwdCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                st_sp.edit().putBoolean("jzpwdCB", isChecked).commit();
            }
        });

        autologCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                st_sp.edit().putBoolean("autologCB", isChecked).commit();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_net_setting:

                break;
            case R.id.bt_login:

                break;
            case R.id.bt_reg:

                break;
        }
    }

    private void submit() {
        // validate
        String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_user_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
