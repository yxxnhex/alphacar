package com.example.alphacar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)
    Button btnLogin;
    ImageView imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        preferences = getSharedPreferences("login_session", MODE_PRIVATE); // 'login_session' 이라는 이름의 폴더를 불러옴

        btnLogin = findViewById(R.id.btnLogin);
        imgBtnBack = findViewById(R.id.imgBtnBack);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                SharedPreferences.Editor editor = preferences.edit(); // 폴더를 수정하기 위해서는 editor를 불러와야 함
                editor.putString("userid","test_id"); // editor를 통해 폴더에 'userid'라는 이름으로 'test_id'를 넣음(나중에 userid로 불러옴)
                editor.putString("userpw","test_pw");
                editor.commit(); // 수정 후에는 반드시 commit을 해주어야 저장됨
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
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }

}
