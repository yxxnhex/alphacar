package com.example.alphacar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences; // SharedPreferences 불러옴 (초기화)
    Button btnLogin;
    ImageView imgBtnBack;
    EditText edtId, edtPw;
    ArrayList<String> ArrUserId = new ArrayList<>();
    ArrayList<String> ArrUserPw = new ArrayList<>();
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityCompat.requestPermissions(this,INITIAL_PERMS, 1000);
        Log.e("permission", "coast: "+ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)+
                ",fine: "+ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)+
                ", granted: "+PackageManager.PERMISSION_GRANTED);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=  PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,INITIAL_PERMS, 1000);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        preferences = getSharedPreferences("login_session", MODE_PRIVATE); // 'login_session' 이라는 이름의 폴더를 불러옴

        btnLogin = findViewById(R.id.btnLogin);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);

        String url = "http://172.30.1.3:5000/select_t_userinfo_login";

        RequestBody formbody = new FormBody.Builder()
//                .add("userId",userId).add("userPw",userPw)
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
                    JSONArray jArray = jObject.getJSONArray("t_userinfo_login");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        String dbUserId = jsonObject.getString("user_phone");
                        String dbUserPw = jsonObject.getString("user_pw");
                        ArrUserId.add(dbUserId);
                        ArrUserPw.add(dbUserPw);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // 로그인 버튼
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                SharedPreferences.Editor editor = preferences.edit(); // 폴더를 수정하기 위해서는 editor를 불러와야 함
                boolean loginCheck = false;

                String userId = edtId.getText().toString();
                String userPw = edtPw.getText().toString();

                for (int i = 0; i < ArrUserId.size(); i++) {
                    if (userId.equals(ArrUserId.get(i)) && userPw.equals(ArrUserPw.get(i))) {
                        editor.putString("userid",userId); // editor를 통해 폴더에 'userid'라는 이름으로 'test_id'를 넣음(나중에 userid로 불러옴)
                        editor.putString("userpw",userPw);
                        editor.commit(); // 수정 후에는 반드시 commit을 해주어야 저장됨
                        loginCheck = true;
                    }
                }
                if (loginCheck == true) {
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }


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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}
