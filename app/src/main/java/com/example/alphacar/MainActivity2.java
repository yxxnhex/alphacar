package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    Button btnAccCall, btn119Call, btnMyLoc;
    ImageView imgAcc, imgBtnMenu, imgBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        // 선언부
        btnAccCall = findViewById(R.id.btnAccCall);
        btn119Call = findViewById(R.id.btn119Call);
        imgAcc = findViewById(R.id.imgAcc);
        imgBtnMenu = findViewById(R.id.imgBtnMenu);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        btnMyLoc = findViewById(R.id.btnMyLoc);

        // 메뉴 버튼
        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        // 뒤로가기 버튼
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnMyLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent new Intent(getApplicationContext(), )
            }
        });

        // 사고 접수 버튼부분
        btnAccCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UrgCallActivity.class);
                startActivity(intent);
            }
        });
        // 119 전화 하기 버튼
        btn119Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(intent);
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