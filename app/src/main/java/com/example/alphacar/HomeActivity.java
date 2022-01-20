package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.String;

public class HomeActivity extends AppCompatActivity {

    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        imgIcon = findViewById(R.id.imgAcar);


//        ## 알파카 아이콘(어플) 클릭 시 시작 애니메이션 화면으로 전환
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });
//        ##




    }

    //  ## 시간
//    private String getTime(){
//        long now = System.currentTimeMillis();
//        Date date = new Date(now);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
//        String Time = dateFormat.format(date);
//
//        return Time;
//    }
//  ##
}