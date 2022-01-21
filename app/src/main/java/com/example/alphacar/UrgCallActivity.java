package com.example.alphacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UrgCallActivity extends AppCompatActivity {

    // tvWarningCall, tvCallTab 아이디 지정만 해뒀어요
    ImageView imgBtnHome, imgBtnBack, btnCallNum1, btnCallNum2, btnCallNum3, btnCallNum4;
    TextView tvInsurance1, tvInsurance2, tvInsuName1, tvInsuName2;
    TextView tvWarnNum1, tvWarnNum2, tvWNName1, tvWNName2;
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urg_call);
        // 선언부
        tvInsurance1 = findViewById(R.id.tvInsurance1);
        tvInsurance2 = findViewById(R.id.tvInsurance2);
        tvWarnNum1 = findViewById(R.id.tvWarnNum1);
        tvWarnNum2 = findViewById(R.id.tvWarnNum2);
        btnCallNum1 = findViewById(R.id.btnCallNum1);
        btnCallNum2 = findViewById(R.id.btnCallNum2);
        btnCallNum3 = findViewById(R.id.btnCallNum3);
        btnCallNum4 = findViewById(R.id.btnCallNum4);
        imgBtnHome = findViewById(R.id.imgBtnMenu);
        imgBtnBack = findViewById(R.id.imgBtnBack);

        // DB 연결되면 넣을 기능 선언들
        tvInsuName1 = findViewById(R.id.tvInsuName1);
        tvInsuName2 = findViewById(R.id.tvInsuName2);
        tvWNName1 = findViewById(R.id.tvWNName1);
        tvWNName2 = findViewById(R.id.tvWNName2);

        final String[] car_ins_call = {""};
        final String[] life_ins_call = {""};
        final String[] em_call1 = {""};
        final String[] em_call2 = {""};

        preferences = getSharedPreferences("login_session", MODE_PRIVATE); // 'login_session'이라는 폴더를 불러옴
        final String userId = preferences.getString("userid", "");
        String url = "http://172.30.1.60:5000/select_urg_call";

        RequestBody formbody = new FormBody.Builder()
                .add("userId", userId)
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).post(formbody).build();

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
                    JSONArray jArray = jObject.getJSONArray("urg_call");
                    JSONObject jsonObject = jArray.getJSONObject(0);
                    em_call1[0] = jsonObject.getString("em_call1");
                    em_call2[0] = jsonObject.getString("em_call2");
                    car_ins_call[0] = jsonObject.getString("car_ins_call");
                    life_ins_call[0] = jsonObject.getString("life_ins_call");
                    String car_insure_name = jsonObject.getString("car_insure_name");
                    String life_insure_name = jsonObject.getString("life_insure_name");

                    tvInsuName1.setText(car_insure_name);
                    tvInsuName2.setText(life_insure_name);
                    tvWNName1.setText(em_call1[0]);
                    tvWNName2.setText(em_call2[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        // home 버튼
        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 전화걸기 버튼
        btnCallNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (car_ins_call[0] != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ car_ins_call[0]));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCallNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (life_ins_call[0] != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + life_ins_call[0]));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCallNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (em_call1[0] != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + em_call1[0]));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCallNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (em_call2[0] != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + em_call2[0]));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //뒤로가기 버튼
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    //뒤로가기 버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }
}