package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class StartActivity extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.homelogo);

        videoView.setVideoURI(videoUri);

        // 비디오 리스너 설정
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                videoView.start();

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1200);

    }
    // 뷰 끝났을때 / 화면이 안보일때
    @Override
    protected void onPause() {
        super.onPause();
        // 비디오 일시 정지
        if (videoView != null && videoView.isPlaying()) videoView.pause();
    }

    // 액티비티가 메모리에서 사라질 떄
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) videoView.stopPlayback();
    }
}