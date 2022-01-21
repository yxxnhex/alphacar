package com.example.alphacar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DanActivity extends AppCompatActivity {

    ImageView imgBtnHome, imgBtnBack;
    ListView danList;
    danAdapter adapter;
    ArrayList<danVO> dan = new ArrayList<>();
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dan);

        danList = findViewById(R.id.danList);
        imgBtnHome = findViewById(R.id.imgBtnMenu);
        imgBtnBack = findViewById(R.id.imgBtnBack);

        preferences = getSharedPreferences("login_session", MODE_PRIVATE); // 'login_session'이라는 폴더를 불러옴
        final String userId = preferences.getString("userid", "");
        String url = "http://172.30.1.60:5000/select_t_event";

        // home 버튼
        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RequestBody formbody = new FormBody.Builder()
                .add("userId", userId)
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formbody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String result = response.body().string();

                try {
                    JSONObject jObject = new JSONObject(result);
                    JSONArray jArray = jObject.getJSONArray("t_event");

                    for (int i =0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        String event_time = jsonObject.getString("event_time");
                        String latitude = jsonObject.getString("latitude");
                        String longitude = jsonObject.getString("longitude");
                        String event_type = jsonObject.getString("event_type");

                        dan.add(new danVO(event_time, latitude, longitude, event_type));
                        Log.e("for문 끝",event_time);
                    }

                    adapter = new danAdapter(dan, getApplicationContext(), R.layout.dan_list);

                    runOnUiThread(new Runnable(){ @Override public void run() { danList.setAdapter(adapter);} });




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });














    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }
}
