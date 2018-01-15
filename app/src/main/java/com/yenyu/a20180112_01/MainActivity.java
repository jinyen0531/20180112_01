package com.yenyu.a20180112_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1 (View v) //點擊後 抓取資料Volley
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //建立 Queue
        StringRequest request = new StringRequest( //建立Request
                "http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                //網址
                new Response.Listener<String>() {
                    @Override //成功了，do what?
                    public void onResponse(String response) {
                        Log.d("NET", response);
                        Gson gson= new Gson();
                        Animal[] houses = gson.fromJson(response,Animal[].class);

                        for(Animal a:houses)
                        {
                            Log.d("NET",a.district+","+a.address+","+a.tel+","+a.open_hours);
                        }

                        //解 JSON 檔案 第一種方法
//                        try {
//                            JSONArray array=new JSONArray(response);
//                            for(int i=0;i<array.length();i++) {
//                                //利用迴圈將每筆資料帶出來
//                                JSONObject obj = array.getJSONObject(i);
//                                String str = obj.getString("district");
//                                Log.d("NET", str);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override //失敗，do what?
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request); //把request加到queue
        queue.start();  //叫queue 開始走
    }
}
