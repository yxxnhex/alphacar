package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    Button btnAccCall, btn119Call;
    ImageView imgAcc, imgBtnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        // 선언부
        btnAccCall = findViewById(R.id.btnAccCall);
        btn119Call = findViewById(R.id.btn119Call);
        imgAcc = findViewById(R.id.imgAcc);
        imgBtnMenu = findViewById(R.id.imgBtnHome);

        // 메뉴 버튼
        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
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
}