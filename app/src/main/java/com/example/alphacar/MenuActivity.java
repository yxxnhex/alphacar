package com.example.alphacar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    ImageView imgBtnBack;
    TextView btnMy, btnAccList, btnWarnCall, btnLocation;
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        preferences = getSharedPreferences("login_session", MODE_PRIVATE); // 'login_session'이라는 폴더를 불러옴
        imgBtnBack = findViewById(R.id.imgBtnBack);
        btnAccList = findViewById(R.id.btnAccList);
        btnMy = findViewById(R.id.btnMy);
        btnWarnCall = findViewById(R.id.btnWarnCall);
        btnLocation = findViewById(R.id.btnLocation);
        Log.e("id", "onCreate: "+preferences.getString("userid","")  ); // preferences.getString("userid","") -> 폴더에 저장되어있는 값을 불러옴(userid라는 이름으로 'test_id'가져옴)
        Log.e("pw", "onCreate: "+preferences.getString("userpw","")  );


        btnAccList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnWarnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UrgCallActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyLocActivity.class);
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
    }
    //뒤로가기 버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }
}
