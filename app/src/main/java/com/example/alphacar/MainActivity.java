package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    TextView tvWarning, tvSpeed;
    VideoView vidMain;
    ImageView imgBtnMenu, imgWarning, imgBtnBack;
    Animation anime;
    Button btn111;
    LinearLayout lin_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 선언부
        tvWarning = findViewById(R.id.tvWarning);
        tvSpeed = findViewById(R.id.tvSpeed);
        vidMain = findViewById(R.id.vidMain);
        imgWarning = findViewById(R.id.imgWarning);
        imgBtnMenu = findViewById(R.id.imgBtnHome);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        // 애니메이션 확인용 버튼
        //btn111 = findViewById(R.id.btn111);
        //lin_test = findViewById(R.id.lin_test);

        // 애니메이션 효과부분
        anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);

//        btn111.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clMain.startAnimation(anime);
//                clMain.setBackgroundColor(Color.parseColor("#66f08080"));
//                vidMain.startAnimation(anime);
//                lin_test.startAnimation(anime);
//                lin_test.setBackgroundColor(Color.parseColor("#66f08080"));

//                btn111.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        clMain.setBackgroundColor(Color.WHITE);
//                        clMain.clearAnimation();
//                        vidMain.clearAnimation();
//                        lin_test.setBackgroundColor(Color.parseColor("#00000000"));
//                        lin_test.clearAnimation();

//                    }
//                });

//            }
//        });

        // 메뉴 버튼
        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
//        뒤로가기 버튼
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 비디오 출력부분
        // raw라는 리소스 폴더 만들어서 거기에 영상을 저장
        // 영상 자체는 안드로이드 스튜디오에서 확인 불가능
        // 모듈을 켰을 때 확인 가능
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
//        vidMain.setMediaController(new MediaController(this));
        // 보여줄 동영상 경로 설정
        vidMain.setVideoURI(videoUri);

        // 비디오 리스너 설정
        vidMain.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                vidMain.start();

            }
        });




    }

    // 뷰 끝났을때 / 화면이 안보일때
    @Override
    protected void onPause() {
        super.onPause();
        // 비디오 일시 정지
        if (vidMain != null && vidMain.isPlaying()) vidMain.pause();
    }

    // 액티비티가 메모리에서 사라질 떄
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vidMain != null) vidMain.stopPlayback();
    }

    //뒤로가기 버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }

}