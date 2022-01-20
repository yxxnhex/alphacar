package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UrgCallActivity extends AppCompatActivity {

    // tvWarningCall, tvCallTab 아이디 지정만 해뒀어요
    ImageView imgBtnMenu;
    TextView tvInsurance1, tvInsurance2, tvInsuName1, tvInsuName2;
    TextView tvWarnNum1, tvWarnNum2, tvWNName1, tvWNName2;
    Button btnCallNum1, btnCallNum2, btnCallNum3, btnCallNum4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urg_call);
        // 선언부
        tvInsuName1 = findViewById(R.id.tvInsuName1);
        tvInsuName2 = findViewById(R.id.tvInsuName2);
        tvWarnNum1 = findViewById(R.id.tvWarnNum1);
        tvWarnNum2 = findViewById(R.id.tvWarnNum2);
        btnCallNum1 = findViewById(R.id.btnCallNum1);
        btnCallNum2 = findViewById(R.id.btnCallNum2);
        btnCallNum3 = findViewById(R.id.btnCallNum3);
        btnCallNum4 = findViewById(R.id.btnCallNum4);
        imgBtnMenu = findViewById(R.id.imgBtnHome);
        // DB 연결되면 넣을 기능 선언들
        tvInsurance1 = findViewById(R.id.tvInsurance1);
        tvInsurance2 = findViewById(R.id.tvInsurance2);
        tvWNName1 = findViewById(R.id.tvWNName1);
        tvWNName2 = findViewById(R.id.tvWNName2);

        // 메뉴 버튼
        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 전화걸기 버튼
        btnCallNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:02-1212-4444"));
                startActivity(intent);
            }
        });
        btnCallNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:02-1234-5555"));
                startActivity(intent);
            }
        });
        btnCallNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-2314-2902"));
                startActivity(intent);
            }
        });
        btnCallNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0101-2902"));
                startActivity(intent);
            }
        });
    }
}