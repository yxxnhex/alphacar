package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class PanelActivity extends AppCompatActivity {

    LinearLayout lin_test1, lin_test2;
    Animation anime;

    //    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel);

        // 애니메이션용
        lin_test1 = findViewById(R.id.lin_test1);
        lin_test2 = findViewById(R.id.lin_test2);
        Button button9 = findViewById(R.id.btn999);
        Button btn888 = findViewById(R.id.btn888);
//        i = 0;
        anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lin_test1.startAnimation(anime);
                lin_test1.setBackgroundColor(Color.parseColor("#b22222"));
                button9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lin_test1.setBackgroundColor(Color.parseColor("#00000000"));
                        lin_test1.clearAnimation();
                    }
                });
            }
        });
        btn888.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lin_test2.startAnimation(anime);
                lin_test2.setBackgroundColor(Color.parseColor("#b22222"));
                btn888.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lin_test2.setBackgroundColor(Color.parseColor("#00000000"));
                        lin_test2.clearAnimation();
                    }
                });
            }
        });
    }

}