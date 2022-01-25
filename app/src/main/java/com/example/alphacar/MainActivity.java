package com.example.alphacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvWarning, tvSpeed;
    VideoView vidMain;
    ImageView imgBtnMenu, imgWarning, imgBtnBack;
    Animation anime;
    FrameLayout lin_test;
    private boolean condition = true;
    int streamId;
    int soundId;
    SoundPool sound;


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
        imgBtnBack = findViewById(R.id.imgBtnBack);
//        clMain = findViewById(R.id.clMain);
        MyThread myThread2 = new MyThread(imgWarning);
        myThread2.start();
        // 애니메이션용
        lin_test = findViewById(R.id.lin_test);

        // 애니메이션 효과부분
        anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);

        // 메뉴 버튼
        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // 사운드 부분
        sound = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        soundId = sound.load(this, R.raw.bibip, 1);


        // 비디오 출력부분
        // raw라는 리소스 폴더 만들어서 거기에 영상을 저장
        // 영상 자체는 안드로이드 스튜디오에서 확인 불가능
        // 모듈을 켰을 때 확인 가능
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test3);
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
        condition = false;

        if (vidMain != null) vidMain.stopPlayback();
    }

    // 애니메이션 구간 효과 넣어보기
    // 1일때 위험, -1일때, 사고, 0일때 안전
    public void warning(int i, int j) {
        if (i == 1) {
            lin_test.startAnimation(anime);
            lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
            imgWarning.setImageResource(R.drawable.cn3);
            tvWarning.setText("좌측전방\n추돌주의");
            tvSpeed.setText(String.valueOf(j));
            streamId = sound.play(soundId, 1.0F, 1.0F, 1, -1, 1.0F);

        } else if (i == 0) {
            lin_test.setBackgroundColor(Color.parseColor("#00000000"));
            lin_test.clearAnimation();
            imgWarning.setImageResource(R.drawable.cn6);
            tvWarning.setText("안전");
            tvSpeed.setText(String.valueOf(j));
            sound.stop(streamId);


        }
        else if (i == 2) {
            lin_test.startAnimation(anime);
            lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
            imgWarning.setImageResource(R.drawable.bike);
            tvWarning.setText("후방\n자전거주의");
            tvSpeed.setText(String.valueOf(j));
            streamId = sound.play(soundId, 1.0F, 1.0F, 1, -1, 1.0F);

        }else if (i == 3) {
            lin_test.startAnimation(anime);
            lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
            imgWarning.setImageResource(R.drawable.bike);
            tvWarning.setText("우측\n자전거주의");
            tvSpeed.setText(String.valueOf(j));
            streamId = sound.play(soundId, 1.0F, 1.0F, 1, -1, 1.0F);

        }else if (i == 4) {
            lin_test.startAnimation(anime);
            lin_test.setBackgroundColor(Color.parseColor("#88b22222"));
            imgWarning.setImageResource(R.drawable.bike);
            tvWarning.setText("전방\n자전거주의");
            tvSpeed.setText(String.valueOf(j));
            streamId = sound.play(soundId, 1.0F, 1.0F, 1, -1, 1.0F);

        }
        else if (i == -1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
            finish();

        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (condition) {
                int num = msg.arg1;
                int num2 = msg.arg2;
                ImageView iv = (ImageView) msg.obj;
                warning(num,num2);
            }

        }
    };

    class MyThread extends Thread {
        ImageView iv;

        public MyThread(ImageView iv) {
            this.iv = iv;
        }

        @Override
        public void run() {

            int min_num_value = 57;
            int max_num_value = 63;

            Random ran = new Random();

            int ran_num;
            for (int i = 0; i < 49; i++) {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();

                    if (8 <= i && i < 12) {

                        message.arg1 = 1;
                        // 0일 경우 안전
                        message.obj = iv;
                        ran_num =ran.nextInt(max_num_value - min_num_value + 1) + min_num_value;
                        message.arg2 = ran_num;
                    }
                    else if (i >= 18 && i<=20) {
                        message.arg1 = 2;
                        // 0일 경우 안전
                        message.arg2 = 0;
                        message.obj = iv;
                    }else if (i >= 21 && i<=25) {
                        message.arg1 = 3;
                        // 0일 경우 안전
                        message.arg2 = 0;
                        message.obj = iv;
                    }else if (i >= 26 && i<=29) {
                        message.arg1 = 4;
                        // 0일 경우 안전
                        message.arg2 = 0;
                        message.obj = iv;
                    }else if (i == 48) {
                        message.arg1 = -1;
                        // 0일 경우 안전
                        ran_num =ran.nextInt(max_num_value - min_num_value + 1) + min_num_value;
                        message.arg2 = ran_num;
                        message.obj = iv;
                    }
                    else {
                        message.arg1 = 0;
                        // 0일 경우 안전
                        ran_num =ran.nextInt(max_num_value - min_num_value + 1) + min_num_value;
                        message.arg2 = ran_num;
                        message.obj = iv;
                        if (i>=14 && i<=39){
                            message.arg2 = 0;
                        }
                    }
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }

}