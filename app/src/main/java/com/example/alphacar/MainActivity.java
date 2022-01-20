package com.example.alphacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    ImageView imgBtnMenu, imgWarning;
    Animation anime, anime2;
    Button btn111;
    ConstraintLayout clMain;
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
        imgBtnMenu = findViewById(R.id.imgBtnMenu);
//        clMain = findViewById(R.id.clMain);
        MyThread myThread2 = new MyThread(imgWarning);
        myThread2.start();
        // 애니메이션 확인용 버튼
//        btn111 = findViewById(R.id.btn111);
        lin_test = findViewById(R.id.lin_test);

        // 애니메이션 효과부분
        anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);

//        btn111.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                clMain.startAnimation(anime);
////                clMain.setBackgroundColor(Color.parseColor("#66f08080"));
////                vidMain.startAnimation(anime);
//                lin_test.startAnimation(anime);
//                lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
//                imgWarning.setImageResource(R.drawable.hamburger);
//
//                btn111.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        clMain.setBackgroundColor(Color.WHITE);
////                        clMain.clearAnimation();
////                        vidMain.clearAnimation();
//                        lin_test.setBackgroundColor(Color.parseColor("#00000000"));
//                        lin_test.clearAnimation();
//                        imgWarning.setImageResource(R.drawable.near);
//
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
    public void warning(int i){
        if(i == 1 ){
            lin_test.startAnimation(anime);
            lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
            imgWarning.setImageResource(R.drawable.back);
        }
        else if(i == -1 ){
            lin_test.setBackgroundColor(Color.parseColor("#00000000"));
            lin_test.clearAnimation();
            imgWarning.setImageResource(R.drawable.back2);

        }else if(i== 2) {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
        }

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int num = msg.arg1;
            ImageView iv = (ImageView) msg.obj;
            warning(num);




        }
    };

    class MyThread extends Thread {
        ImageView iv;
        public MyThread(ImageView iv){
            this.iv = iv;
        }

        @Override
        public void run() {
            for (int i = 30; i > 0; i--){
                try {
                    Thread.sleep(1000);
                    Message message = new Message();

                    if( i == 20 ) {

                        message.arg1 = 1;
                        // 0일 경우 안전
                        message.obj = iv;
                    }else if(i == 15){
                        message.arg1 = -1;
                        // 0일 경우 안전
                        message.obj = iv;
                    }
                    else if(i == 10){
                        message.arg1 = 2;
                        // 0일 경우 안전
                        message.obj = iv;
                    }
                    else{
                        message.arg1 = 0;
                        // 0일 경우 안전
                        message.obj = iv;
                    }



                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}