package com.example.alphacar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfoActivity extends AppCompatActivity {

    TextView tvPhoneNum, tvCarNum, tvCarName, tvCarGas;
    ImageView imgBtnHome, imgBtnBack;
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        imgBtnHome = findViewById(R.id.imgBtnMenu);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvCarNum = findViewById(R.id.tvCarNum);
        tvCarName = findViewById(R.id.tvCarName);
        tvCarGas = findViewById(R.id.tvCarGas);

        preferences = getSharedPreferences("login_session", MODE_PRIVATE);
        String userId = preferences.getString("userid", "");
        String url = "http://172.30.1.3:5000/select_t_userinfo";

        // 홈 버튼
        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        뒤로가기 버튼
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RequestBody formbody = new FormBody.Builder()
                .add("userId",userId) // 요청값 보내기
                .build();

//        요청
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

                ArrayList<infoVO> info = new ArrayList<>();
                infoVO vo = null;
                Log.e("result",result);

                try {
                    Log.e("try", "try");
                    JSONObject jObject = new JSONObject(result);
                    JSONArray jArray = jObject.getJSONArray("t_userinfo");
                    Log.e("jobject",jObject.getString("t_userinfo")+"");

                    vo = new infoVO();
                    JSONObject jsonObject = jArray.getJSONObject(0);
                    String user_phone = jsonObject.getString("user_phone");
                    String ve_number = jsonObject.getString("ve_number");
                    String ve_type = jsonObject.getString("ve_type");
                    String energy_type = jsonObject.getString("energy_type");

                        Log.e("check" , user_phone);
                        Log.e("check" , ve_number);
                        Log.e("check" , ve_type);
                        Log.e("check" , energy_type);

                    vo.setUser_phone(user_phone);
                    vo.setVe_number(ve_number);
                    vo.setVe_type(ve_type);
                    vo.setEnergy_type(energy_type);

                    info.add(vo);

                    tvPhoneNum.setText(vo.getUser_phone());
                    tvCarNum.setText(vo.getVe_number());
                    tvCarName.setText(vo.getVe_type());
                    tvCarGas.setText(vo.getEnergy_type());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
